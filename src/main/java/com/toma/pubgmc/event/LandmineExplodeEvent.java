package com.toma.pubgmc.event;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

/**
 *  This is called when landmine explodes in world
 *  for both sides
 */
public class LandmineExplodeEvent extends Event
{
	private final BlockPos pos;
	private final World world;
	public final Side side;
	private final List<Entity> affectedEntities;
	
	public LandmineExplodeEvent(Side side, BlockPos pos, World world, List<Entity> affectedEntities)
	{
		this.side = side;
		this.pos = pos;
		this.world = world;
		this.affectedEntities = affectedEntities;
	}
	
	@Override
	public boolean isCancelable()
	{
		return false;
	}
	
	public BlockPos getExplosionPosition()
	{
		return pos;
	}
	
	public List<Entity> getAffectedEntities()
	{
		return affectedEntities;
	}
	
	public World getWorld() 
	{
		return world;
	}
}
