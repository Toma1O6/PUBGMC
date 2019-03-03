package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSpawnVehicle;

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
		this.maxHealth = 150f;
		this.health = 150f;
		this.maxSpeed = 1.5f;
		this.acceleration = 0.02f;
		this.turnSpeed = 0.25f;
		this.fuel = 60f + rand.nextInt(40) + rand.nextFloat();
	}
}
