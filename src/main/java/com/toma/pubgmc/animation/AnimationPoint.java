package com.toma.pubgmc.animation;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class AnimationPoint 
{
	private Vec3d position, rotation;
	private boolean hasFinished;
	
	public AnimationPoint(final Vec3d position, final Vec3d rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	public AnimationPoint(double x, double y, double z, float rx, float ry, float rz)
	{
		this.position = new Vec3d(x, y, z);
		this.rotation = new Vec3d(rx, ry, rz);
	}
	
	public static NBTTagCompound writeToNBT(final Vec3d position, final Vec3d rotation)
	{
		NBTTagCompound c = new NBTTagCompound();
		c.setDouble("x", position.x);
		c.setDouble("y", position.y);
		c.setDouble("z", position.z);
		c.setFloat("rx", (float)rotation.x);
		c.setFloat("ry", (float)rotation.y);
		c.setFloat("rz", (float)rotation.z);
		return c;
	}
	
	public static AnimationPoint readFromNBT(NBTTagCompound compound)
	{
		final Vec3d pos = new Vec3d(compound.getDouble("x"), compound.getDouble("y"), compound.getDouble("z"));
		final Vec3d rot = new Vec3d(compound.getFloat("rx"), compound.getFloat("ry"), compound.getFloat("rz"));
		return new AnimationPoint(pos, rot);
	}
	
	public void updatePosition(Vec3d newPos)
	{
		this.position = newPos;
	}
	
	public Vec3d getPosition()
	{
		return this.position;
	}
	
	public void updateRotation(Vec3d newRot)
	{
		this.rotation = newRot;
	}
	
	public Vec3d getRotation()
	{
		return this.rotation;
	}
	
	public void setFinished(boolean finished)
	{
		this.hasFinished = finished;
	}
	
	public boolean isFinished()
	{
		return hasFinished;
	}
}
