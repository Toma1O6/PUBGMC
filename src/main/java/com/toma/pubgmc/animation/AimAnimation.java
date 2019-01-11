package com.toma.pubgmc.animation;

import net.minecraft.client.renderer.GlStateManager;

public class AimAnimation implements IGunAnimation
{
	private final double x,y,z;
	private final float animationSpeed;
	private double mX, mY, mZ;
	private boolean invertX,invertY,invertZ = false;
	
	public AimAnimation(double x, double y, double z, float speedMultiplier) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.animationSpeed = speedMultiplier;
	}
	
	@Override
	public void processAnimation(boolean scopeIn)
	{
		if(scopeIn)
		{
			if(mX != x)
			{
				if(!invertX)
				{
					if(mX < x) mX += 0.3d * animationSpeed;
					if(mX > x) mX = x;
				}
				else
				{
					if(mX > x) mX -= 0.3d * animationSpeed;
					if(mX < x) mX = x;
				}
			}
			
			if(mY != y)
			{
				if(!invertY)
				{
					if(mY < y) mY += 0.15d * animationSpeed;
					if(mY > y) mY = y;
				}
				else
				{
					if(mY > y) mY -= 0.15d * animationSpeed;
					if(mY < y) mY = y;
				}
			}
			
			if(mZ != z)
			{
				if(!invertZ)
				{
					if(mZ < z) mZ += 0.2d * animationSpeed;
					if(mZ > z) mZ = z;
				}
				else
				{
					if(mZ > z) mZ -= 0.2d * animationSpeed;
					if(mZ < z) mZ = z;
				}
			}
		}
		
		else
		{
			if(mX != 0)
			{
				if(!invertX)
				{
					if(mX > 0) mX -= 0.3d * animationSpeed;
					if(mX < 0) mX = 0;
				}
				
				else
				{
					if(mX < 0) mX += 0.3d * animationSpeed;
					if(mX > 0) mX = 0;
				}
			}
			
			if(mY != 0)
			{
				if(!invertY)
				{
					if(mY > 0) mY -= 0.15d * animationSpeed;
					if(mY < 0) mY = 0;
				}
				
				else
				{
					if(mY < 0) mY += 0.15d * animationSpeed;
					if(mY > 0) mY = 0;
				}
			}
			
			if(mZ != 0)
			{
				if(!invertZ)
				{
					if(mZ > 0) mZ -= 0.2d * animationSpeed;
					if(mZ < 0) mZ = 0;
				}
				
				else
				{
					if(mZ < 0) mZ += 0.2d * animationSpeed;
					if(mZ > 0) mZ = 0;
				}
			}
		}
		
		GlStateManager.translate(mX, mY, mZ);
	}
	
	public void reset()
	{
		mX = 0;
		mY = 0;
		mZ = 0;
	}
	
	public void setInvertedCoords(boolean x, boolean y, boolean z)
	{
		this.invertX = x;
		this.invertY = y;
		this.invertZ = z;
	}
	
	public double getX()
	{
		return mX;
	}
	
	public double getY()
	{
		return mY;
	}
	
	public double getZ()
	{
		return mZ;
	}
	
	public boolean isXInverted()
	{
		return invertX;
	}
	
	public boolean isYInverted()
	{
		return invertY;
	}
	
	public boolean isZInverted()
	{
		return invertZ;
	}
}
