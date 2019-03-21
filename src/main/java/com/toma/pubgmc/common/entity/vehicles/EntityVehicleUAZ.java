package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicleUAZ extends EntityVehicle
{
	private static final Vec3d[] VECTORS = {new Vec3d(2d, 1.5d, 0), new Vec3d(-1.9d, 0.3, -0.6d)};
	
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
	
	/*@Override
	public void initWheels()
	{
		WheelPart fr = new WheelPart(this, this, "fr", Vec3d.ZERO);
		WheelPart fl = new WheelPart(this, this, "fl", Vec3d.ZERO);
		WheelPart br = new WheelPart(this, this, "br", Vec3d.ZERO);
		WheelPart bl = new WheelPart(this, this, "bl", Vec3d.ZERO);
		parts = new WheelPart[] {fr, fl, br, bl};
		
		for(int i = 0; i < parts.length; i++)
			world.spawnEntity(parts[i]);
		
		System.out.println(parts);
	}*/
	
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
	
	@Override
	public Vec3d getEnginePosition()
	{
		return VECTORS[0];
	}
	
	@Override
	public Vec3d getExhaustPosition() 
	{
		return VECTORS[1];
	}
	
	@Override
	public SoundEvent vehicleSound() 
	{
		return PMCSounds.uaz;
	}
	
	@Override
	public String[] getTextureVariants()
	{
		return null;
	}
}
