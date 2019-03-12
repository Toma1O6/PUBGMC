package com.toma.pubgmc.common.entity.vehicles;

import javax.annotation.Nonnull;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicleUAZ extends EntityVehicle
{
	public EntityVehicleUAZ(World world)
	{
		super(world);
		setSize(2f, 1.5f);
	}
	
	public EntityVehicleUAZ(World world, double x, double y, double z)
	{
		this(world);
		setPosition(x, y, z);
		maxHealth = 250f;
		health = 250f;
		maxSpeed = 1.6f;
		acceleration = 0.015f;
		turnSpeed = 0.3f;
		fuel = 60f + rand.nextInt(40) + rand.nextFloat();
	}
	
	@Nonnull
	@Override
	public int getMaximumCapacity()
	{
		return 4;
	}
	
	@Override
	public double getMountedYOffset()
	{
		return 0.75d;
	}
	
	@Nonnull
	@Override
	public Vec3d getEnginePosition()
	{
		return new Vec3d(2d, 1.5d, 0);
	}
	
	@Nonnull
	@Override
	public Vec3d getExhaustPosition() 
	{
		return new Vec3d(-1.9d, 0.3, -0.6d);
	}
	
	@Override
	public SoundEvent vehicleSound() 
	{
		return PMCSounds.uaz;
	}
}
