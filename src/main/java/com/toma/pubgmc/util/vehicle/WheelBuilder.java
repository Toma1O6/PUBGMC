package com.toma.pubgmc.util.vehicle;

import java.util.UUID;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.util.math.Vec3d;

public class WheelBuilder
{
	private UUID uuid;
	private Vec3d pos;
	
	public static WheelBuilder create()
	{
		return new WheelBuilder();
	}
	
	public WheelBuilder parentUUID(UUID uuid)
	{
		this.uuid = uuid;
		return this;
	}
	
	public WheelBuilder position(Vec3d vec)
	{
		this.pos = vec;
		return this;
	}
	
	public WheelBuilder position(double x, double y, double z)
	{
		this.pos = new Vec3d(x, y, z);
		return this;
	}
	
	public Wheel build()
	{
		checkNotNull(uuid);
		checkNotNull(pos);
		return new Wheel(uuid, pos);
	}
	
	public static <O>O checkNotNull(O obj) throws NullPointerException
	{
		if(obj == null) throw new NullPointerException(obj + " cannot be null!");
		return obj;
	}
}
