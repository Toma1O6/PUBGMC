package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.world.World;

public class EntityTestVehicle extends EntityVehicle
{
	public EntityTestVehicle(World world)
	{
		super(world);
		setSize(2f, 2f);
	}
	
	public EntityTestVehicle(World world, double x, double y, double z) 
	{
		this(world);
		setPosition(x, y, z);
	}
	
	@Override
	public void onSpawned()
	{
		setAllRequiredValues(150f, 150f, 0.7f, 0.01f, 0.6f);
	}
}
