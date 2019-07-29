package com.toma.pubgmc.common.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockLight extends PMCBlock {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
        public boolean apply(@Nullable EnumFacing facing) {
            return facing == EnumFacing.DOWN;
        }
    });

    public BlockLight(String name, Material material, SoundType sound, MapColor color) {
        super(name, material);
        setSoundType(sound);
        this.setLightLevel(1.0f);
    }

    @Override
    @javax.annotation.Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0, 0.8, 0.0, 1.0, 1.0, 1.0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        for (EnumFacing facing : FACING.getAllowedValues()) {
            if (facing == EnumFacing.DOWN && !worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()))) {
                return true;
            }
        }

        return false;
    }

}
