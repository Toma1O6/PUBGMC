package com.toma.pubgmc.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityLandMine extends TileEntity implements ITickable
{
	@Override
	public void update()
	{
		World world = this.getWorld();
		if(!world.isRemote && !world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX() - 2, pos.getY() - 1, pos.getZ() - 2, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2)).isEmpty())
		{
			explode(world, pos);
		}
	}
	
	public void explode(World world, BlockPos pos)
	{
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7f, false);
		world.setBlockToAir(pos);
	}
}
