package com.toma.pubgmc.common;

import com.toma.pubgmc.common.blocks.PMCBlock;
import com.toma.pubgmc.util.IBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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

import java.util.Arrays;
import java.util.List;

public class BlockBuilder implements IBuilder<PMCBlock> {
    protected String name;
    protected Material blockMaterial;
    protected SoundType soundType;
    protected BlockRenderLayer renderLayer;
    protected MapColor mapColor;
    protected boolean opaque, fullCube;
    protected float lightValue = 0;
    protected AxisAlignedBB[] boxes;
    protected boolean isGlass = false;
    protected String[] description = null;

    protected BlockBuilder() {
    }

    public static BlockBuilder create(String name, Material material) {
        BlockBuilder builder = new BlockBuilder();
        builder.name = name;
        builder.blockMaterial = material;
        builder.initialize();
        return builder;
    }

    private void initialize() {
        mapColor = blockMaterial.getMaterialMapColor();
        renderLayer = BlockRenderLayer.SOLID;
        opaque = true;
        fullCube = true;
        boxes = new AxisAlignedBB[]{Block.FULL_BLOCK_AABB, Block.FULL_BLOCK_AABB};
    }

    public BlockBuilder renderType(BlockRenderLayer layerRender) {
        this.renderLayer = layerRender;
        return this;
    }

    public BlockBuilder mapColor(MapColor color) {
        this.mapColor = color;
        return this;
    }

    public BlockBuilder transparency(boolean opaque, boolean fullCube) {
        this.opaque = opaque;
        this.fullCube = fullCube;
        return this;
    }

    public BlockBuilder light(float light) {
        this.lightValue = light;
        return this;
    }

    public BlockBuilder aabb(AxisAlignedBB aabb) {
        this.boxes = new AxisAlignedBB[]{aabb, aabb};
        return this;
    }

    public BlockBuilder aabb(AxisAlignedBB bounding, AxisAlignedBB collision) {
        this.boxes = new AxisAlignedBB[]{bounding, collision};
        return this;
    }

    public BlockBuilder soundType(SoundType type) {
        this.soundType = type;
        return this;
    }

    public BlockBuilder setTransparent() {
        opaque = false;
        fullCube = false;
        return this;
    }

    public BlockBuilder setGlass() {
        setTransparent();
        renderLayer = BlockRenderLayer.TRANSLUCENT;
        isGlass = true;
        return this;
    }

    public BlockBuilder description(String... strings) {
        this.description = strings;
        return this;
    }

    @Override
    public PMCBlock build() {
        checkFloat(lightValue, 0f, 1f);
        checkNotNull(soundType);

        PMCBlock builtBlock = new PMCBlock(name, blockMaterial) {
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
                return boxes[0];
            }

            @Override
            public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
                return boxes[1];
            }

            @Override
            public SoundType getSoundType(IBlockState state, World world, BlockPos pos, Entity entity) {
                return soundType;
            }

            @Override
            public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
                return isGlass ? blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side) : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
            }

            @Override
            public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
                if (description != null) {
                    tooltip.addAll(Arrays.asList(description));
                }
            }
        };
        builtBlock.setLightLevel(lightValue);
        return builtBlock;
    }
}
