package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.world.World;

public class EntityVehicleUAZ extends EntityVehicle
{
	public EntityVehicleUAZ(World world)
	{
		super(world);
		setSize(2f, 2f);
	}
	
	public EntityVehicleUAZ(World world, double x, double y, double z)
	{
		this(world);
		setPosition(x, y, z);
		maxHealth = 250f;
		health = 250f;
		maxSpeed = 1.6f;
		acceleration = 0.005f;
		turnSpeed = 0.3f;
		fuel = 60f + rand.nextInt(40) + rand.nextFloat();
	}
}
