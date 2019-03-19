package com.toma.pubgmc.util.vehicle;

import java.util.UUID;

import com.toma.pubgmc.common.entity.EntityVehicle;

import net.minecraft.util.math.Vec3d;

public interface IWheels 
{
	public EntityVehicle parent(UUID uuid);
	
	public int wheelCount();
	
	public Vec3d wheelPositions();
	
	public boolean canBeFlattened();
}
