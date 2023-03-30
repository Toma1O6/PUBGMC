package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPlayerCrate extends PMCBlock {

    private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0, 0.0, 0.15, 1, 0.4, 0.85);

    public BlockPlayerCrate(String name, Material material, SoundType sound, MapColor color) {
        super(name, material);
        this.setSoundType(sound);
        this.setHardness(0.2f);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        TileEntityPlayerCrate te = (TileEntityPlayerCrate)worldIn.getTileEntity(pos);
        te.clear();
        worldIn.setBlockToAir(pos);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPlayerCrate();
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
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityPlayerCrate) {
            TileEntityPlayerCrate te = (TileEntityPlayerCrate) worldIn.getTileEntity(pos);
            InventoryHelper.dropInventoryItems(worldIn, pos, te);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BOX;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOX;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.isSneaking() && !worldIn.isRemote) {
            playerIn.openGui(Pubgmc.instance, GuiHandler.GUI_CRATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}
