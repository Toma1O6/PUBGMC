package dev.toma.pubgmc.common;

import dev.toma.pubgmc.common.blocks.PMCBlockHorizontal;
import dev.toma.pubgmc.util.IBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class HorizontalBlockBuilder implements IBuilder<PMCBlockHorizontal> {
    private String name;
    private Material material;
    private SoundType soundType;
    private BlockRenderLayer renderLayer;
    private MapColor mapColor;
    private boolean opaque, fullCube;
    private float lightValue;
    private AxisAlignedBB[] boxes = new AxisAlignedBB[]{Block.FULL_BLOCK_AABB};
    private AxisAlignedBB[] coll_boxes = new AxisAlignedBB[]{Block.FULL_BLOCK_AABB};
    private BlockFaceShape faceShape;
    private String[] desc;

    private HorizontalBlockBuilder() {
    }

    public static HorizontalBlockBuilder create(String name, Material material) {
        HorizontalBlockBuilder builder = new HorizontalBlockBuilder();
        builder.name = name;
        builder.material = material;
        builder.initialize();
        return builder;
    }

    private void initialize() {
        renderLayer = BlockRenderLayer.SOLID;
        opaque = true;
        fullCube = true;
        boxes = new AxisAlignedBB[]{Block.FULL_BLOCK_AABB, Block.FULL_BLOCK_AABB};
        faceShape = BlockFaceShape.UNDEFINED;
        lightValue = 0;
    }

    public HorizontalBlockBuilder renderType(BlockRenderLayer layerRender) {
        this.renderLayer = layerRender;
        return this;
    }

    public HorizontalBlockBuilder mapColor(MapColor color) {
        this.mapColor = color;
        return this;
    }

    public HorizontalBlockBuilder transparency(boolean opaque, boolean fullCube) {
        this.opaque = opaque;
        this.fullCube = fullCube;
        return this;
    }

    public HorizontalBlockBuilder light(float light) {
        this.lightValue = light;
        return this;
    }

    public HorizontalBlockBuilder aabb(AxisAlignedBB aabb) {
        this.boxes = new AxisAlignedBB[]{aabb};
        this.coll_boxes = new AxisAlignedBB[]{aabb};
        return this;
    }

    public HorizontalBlockBuilder aabb(AxisAlignedBB bounding, AxisAlignedBB collision) {
        this.boxes = new AxisAlignedBB[]{bounding};
        this.coll_boxes = new AxisAlignedBB[]{collision};
        return this;
    }

    /**
     * 0 - S; 1 - W; 2 - N; 3 - E
     */
    public HorizontalBlockBuilder aabb(AxisAlignedBB... horizontalBoxes) {
        this.boxes = horizontalBoxes;
        this.coll_boxes = horizontalBoxes;
        return this;
    }

    public HorizontalBlockBuilder nullAABB() {
        this.coll_boxes = new AxisAlignedBB[]{Block.NULL_AABB};
        return this;
    }

    public HorizontalBlockBuilder soundType(SoundType type) {
        this.soundType = type;
        return this;
    }

    public HorizontalBlockBuilder faceShape(BlockFaceShape shape) {
        this.faceShape = shape;
        return this;
    }

    public HorizontalBlockBuilder setTransparent() {
        opaque = false;
        fullCube = false;
        return this;
    }

    public HorizontalBlockBuilder setProp() {
        aabb(Block.FULL_BLOCK_AABB, Block.NULL_AABB);
        setTransparent();
        renderLayer = BlockRenderLayer.CUTOUT;

        return this;
    }

    public HorizontalBlockBuilder setPassable() {
        aabb(Block.FULL_BLOCK_AABB, Block.NULL_AABB);
        return this;
    }

    public HorizontalBlockBuilder description(String... strings) {
        this.desc = strings;
        return this;
    }

    @Override
    public PMCBlockHorizontal build() {
        checkNotNull(material);
        checkFloat(lightValue, 0, 1.0F);
        checkNotNull(soundType);

        PMCBlockHorizontal builtBlock = new PMCBlockHorizontal(name, material) {
            @Override
            public boolean isOpaqueCube(IBlockState state) {
                return opaque;
            }

            @Override
            public boolean isFullCube(IBlockState state) {
                return fullCube;
            }

            @Override
            public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
                return mapColor;
            }

            @Override
            public BlockRenderLayer getBlockLayer() {
                return renderLayer;
            }

            @Override
            public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
                return boxes.length == 4 ? boxes[getBoundingBoxFromRotation(state)] : boxes[0];
            }

            @Override
            public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
                return coll_boxes.length == 4 ? coll_boxes[getBoundingBoxFromRotation(state)] : coll_boxes[0];
            }

            @Override
            public SoundType getSoundType(IBlockState state, World world, BlockPos pos, Entity entity) {
                return soundType;
            }

            @Override
            public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
                return faceShape;
            }

            @Override
            public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
                if (desc != null) {
                    Collections.addAll(tooltip, desc);
                }
            }
        };
        builtBlock.setLightLevel(lightValue);
        return builtBlock;
    }
}
