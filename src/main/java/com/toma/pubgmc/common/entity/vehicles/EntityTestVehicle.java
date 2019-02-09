package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.world.World;

public class EntityTestVehicle extends EntityVehicle
{
	public EntityTestVehicle(World world)
	{
		super(world);
		setSize(3f, 2f);
	}
	
	public EntityTestVehicle(World world, double x, double y, double z) 
	{
		this(world);
		setPosition(x, y, z);
	}
	
	@Override
	public void onAddedToWorld()
	{
		setMaxHealth(100f);
		setHealth(100f);
		setFuelLevel(10f + rand.nextInt(90) + rand.nextFloat());
		setMaxSpeed(5f);
		setWaterVehicle(false);
		super.onAddedToWorld();
	}
}
