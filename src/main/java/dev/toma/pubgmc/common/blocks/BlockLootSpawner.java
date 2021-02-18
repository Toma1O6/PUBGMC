package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockLootSpawner extends PMCBlock {
    public static final PropertyInteger LOOT = PropertyInteger.create("loot", 0, 2);
    private static final AxisAlignedBB BB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
    private MapColor color;
    private Random rand = new Random();

    public BlockLootSpawner(String name, Material material, SoundType sound, MapColor color) {
        super(name, material);
        setSoundType(sound);
        this.setBlockUnbreakable();
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOOT, 0));

        this.color = color;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
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
        return ConfigPMC.client.other.lootRenderStyle.ordinal() < 2 ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return BB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLootGenerator();
    }

    //We don't want to save anything since it's just for debug and could cause some weird stuff
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(LOOT, meta);
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
        tooltip.add(TextFormatting.BOLD + "Shift-Right Click in Creative mode to change tier:");
        tooltip.add(TextFormatting.RED + "COMMON, multiplier x1");
        tooltip.add(TextFormatting.YELLOW + "RARE, multiplier x1.4");
        tooltip.add(TextFormatting.GREEN + "VERY RARE, multiplier x2");
        tooltip.add(TextFormatting.ITALIC + "Loot can be generated using the " + TextFormatting.UNDERLINE + "/loot generate");
    }

    public enum LootType {

        COMMON(1, 0, 0, 1f, false),
        RARE(0, 1, 0, 1.4f, false),
        VERY_RARE(0.2, 1, 0, 2f, false),
        AIRDROP(0, 0, 0, 1.0F, true);
        private final Vec3d rgb;
        private final float multiplier;

        LootType(final double r, final double g, final double b, final float multiplier, final boolean allowSpecialWeapons) {
            this.rgb = new Vec3d(r, g, b);
            this.multiplier = multiplier;
        }

        public static LootType getTypeFromState(IBlockState state) {
            return values()[state.getValue(BlockLootSpawner.LOOT)];
        }

        public Vec3d getRGB() {
            return rgb;
        }

        public float getLootMultiplier() {
            return multiplier;
        }

        public LootType switchMode() {
            return ordinal() + 1 < 3 ? values()[ordinal() + 1] : values()[0];
        }
    }
}
