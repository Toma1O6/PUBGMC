package dev.toma.pubgmc.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSimpleStairs extends PMCBlockHorizontal {

    public static final AxisAlignedBB BASE = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
    public static final AxisAlignedBB[] TOP = {new AxisAlignedBB(0, 0.5, 0, 1, 1, 0.5), new AxisAlignedBB(0.5, 0.5, 0, 1, 1, 1), new AxisAlignedBB(0, 0.5, 0.5, 1, 1, 1), new AxisAlignedBB(0, 0.5, 0, 0.5, 1, 1)};

    public BlockSimpleStairs(String name, Material material) {
        super(name, material);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BASE;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, TOP[state.getValue(FACING).getHorizontalIndex()]);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
