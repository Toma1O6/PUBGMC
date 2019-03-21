package com.toma.pubgmc.util.vehicle;

import java.util.UUID;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class Wheel
{
	private static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.2d, 0.0d, 0.2d, 0.8d, 1.0d, 0.8d);
	private final UUID UUID;
	private final Vec3d posVec;
	
	public Wheel(UUID uuid, Vec3d pos)
	{
		this.UUID = uuid;
		this.posVec = pos;
	}
	
	public AxisAlignedBB getAABB()
	{
		return DEFAULT_AABB;
	}
}
