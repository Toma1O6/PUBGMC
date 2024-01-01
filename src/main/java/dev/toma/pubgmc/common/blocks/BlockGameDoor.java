package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.tileentity.TileEntityGameDoor;
import dev.toma.pubgmc.init.CommonRegistry;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockGameDoor extends BlockDoor {

    public BlockGameDoor(String name, Material materialIn) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setBlockUnbreakable();
        disableStats();
        CommonRegistry.registerItemBlock(this, block -> {
            ItemDoor itemDoor = new ItemDoor(block);
            itemDoor.setUnlocalizedName(name);
            itemDoor.setCreativeTab(PMCTabs.TAB_BLOCKS);
            return itemDoor;
        });
    }

    /*
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = state.getValue(FACING);

        switch (enumfacing) {
            case EAST:
            default:
                return EAST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case NORTH:
                return NORTH_AABB;
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return blockState.getValue(OPEN) ? NULL_AABB : super.getCollisionBoundingBox(blockState, worldIn, pos);
    }
     */

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(HALF) == EnumDoorHalf.LOWER;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return state.getValue(HALF) == EnumDoorHalf.LOWER ? new TileEntityGameDoor() : null;
    }
}
