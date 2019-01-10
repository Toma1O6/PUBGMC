package com.toma.pubgmc.animation;

public class AnimationPoint 
{
	private double x,y,z;
	
	/**
	 * constructor
	 * @param posX - x position for the model
	 * @param posY - y position for the model
	 * @param posZ - z position for the model
	 */
	public AnimationPoint(double posX, double posY, double posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}
	
	public AnimationPoint getAnimationPoint()
	{
		return new AnimationPoint(x, y, z);
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
}
