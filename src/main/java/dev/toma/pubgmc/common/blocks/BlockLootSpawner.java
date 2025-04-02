package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.AimInfo;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockLootSpawner extends PMCBlock {
    public static final PropertyInteger LOOT = PropertyInteger.create("loot", 0, 2);
    public static final AxisAlignedBB BB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);

    public static final ITextComponent GENERIC_GENERATOR_TITLE = new TextComponentTranslation("label.pubgmc.generator.tooltip");
    private static final ITextComponent LOOT_TIER_TITLE = new TextComponentTranslation("label.pubgmc.generator.tooltip.loot");

    public BlockLootSpawner(String name, Material material, SoundType sound) {
        super(name, material);
        setSoundType(sound);
        this.setBlockUnbreakable();
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOOT, 0));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            data.getAimInfo().setAiming(false, 1.0F);
            playerIn.openGui(Pubgmc.instance, GuiHandler.LOOT_SPAWNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        } else if (playerIn.isSneaking() && playerIn.capabilities.isCreativeMode) {
            this.updateBlockState(worldIn, pos, state);
            Vec3d rgb = LootType.getTypeFromState(worldIn.getBlockState(pos)).getRGB();
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rgb.x, rgb.y, rgb.z);
        }

        return true;
    }

    public void updateBlockState(World world, BlockPos pos, IBlockState state) {
        int i = state.getValue(LOOT) + 1 < 3 ? state.getValue(LOOT) + 1 : 0;
        world.setBlockState(pos, getDefaultState().withProperty(LOOT, i), 3);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLootGenerator();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(LOOT, meta);
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOOT);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOOT);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(GENERIC_GENERATOR_TITLE.getFormattedText());
        tooltip.add(LOOT_TIER_TITLE.getFormattedText());
    }

    public enum LootType {

        COMMON(1, 0, 0),
        RARE(0, 1, 0),
        VERY_RARE(0.2, 1, 0),
        AIRDROP(0, 0, 0);

        private final Vec3d rgb;

        LootType(double r, double g, double b) {
            this.rgb = new Vec3d(r, g, b);
        }

        public static LootType getTypeFromState(IBlockState state) {
            return values()[state.getValue(BlockLootSpawner.LOOT)];
        }

        public Vec3d getRGB() {
            return rgb;
        }
    }
}
