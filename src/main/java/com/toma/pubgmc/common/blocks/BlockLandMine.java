package com.toma.pubgmc.common.blocks;

import com.toma.pubgmc.common.tileentity.TileEntityLandMine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLandMine extends PMCBlock
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.15d, 0d, 0.15d, 0.85d, 0.1d, 0.85d);

    public BlockLandMine(String name) {
        super(name, Material.WEB);
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
        return AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLandMine();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        explode(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    private void explode(World world, BlockPos pos) {
        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4f, false);
        world.setBlockToAir(pos);
    }
}
