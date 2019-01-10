package com.toma.pubgmc.common.tileentity;

import com.toma.pubgmc.common.blocks.BlockLamp;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityLamp extends TileEntity implements ITickable
{

	@Override
	public void update()
	{
		IBlockState state = world.getBlockState(pos);
		if(!world.provider.isDaytime() && !world.isRemote && state.getValue(BlockLamp.ON) == false)
		{
			world.setBlockState(pos, state.getActualState(world, pos).withProperty(BlockLamp.ON, true), 2);
		}
		
		if(world.provider.isDaytime() && !world.isRemote && state.getValue(BlockLamp.ON) == true)
		{
			world.setBlockState(pos, state.getActualState(world, pos).withProperty(BlockLamp.ON, false), 2);
		}
	}

}
