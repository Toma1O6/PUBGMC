package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTestVehicle extends EntityVehicle
{
	public EntityTestVehicle(World world)
	{
		super(world);
		setSize(3f, 2f);
		setHealth(100f);
		setFuelLevel(20f);
		setMaxSpeed(10f);
		setWaterVehicle(false);
	}
	
	public EntityTestVehicle(World world, double x, double y, double z) 
	{
		this(world);
		setPosition(x, y, z);
	}
	
	@Override
	public void onAddedToWorld()
	{
		setHealth(100f);
		setFuelLevel(rand.nextInt(100) + rand.nextFloat());
		setMaxSpeed(5f);
		setWaterVehicle(false);
		super.onAddedToWorld();
	}
}
