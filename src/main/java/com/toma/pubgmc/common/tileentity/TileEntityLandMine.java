package com.toma.pubgmc.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityLandMine extends TileEntity implements ITickable
{
	@Override
	public void update()
	{
		World world = this.getWorld();
		if(!world.isRemote && world.getNearestAttackablePlayer(pos, 3, 2) != null)
		{
			explode(world, pos);
		}
	}
	
	private void explode(World world, BlockPos pos)
	{
		if(!world.isRemote)
		{
			world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5f, false);
			world.setBlockToAir(pos);
		}
	}
}
