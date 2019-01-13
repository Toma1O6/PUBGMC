package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class AimAnimation extends Animation
{
	private static final double X_MOVEMENT = 0.0144d;
	private static final double Y_MOVEMENT = 0.0064d;
	private static final double Z_MOVEMENT = 0.0056d;
	private final double x,z;
	private double y;
	private final float animationSpeed;
	private float xModifier = 1f, yModifier = 1f, zModifier = 1f;
	private double mX, mY, mZ;
	private boolean invertX,invertY,invertZ = false;
	
	/**
	 * Creates new Animation 
	 * @param x - the X translation of final animation movement
	 * @param y - the Y translation of final animation movement (change for red dot & holo scopes!)
	 * @param z - the Z translation of final animation movement
	 * @param speedMultiplier - The speed at which the animation will be performed (normal = 1f)
	 */
	public AimAnimation(double x, double y, double z, float speedMultiplier) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.animationSpeed = speedMultiplier;
	}
	
	/**
	 * Starts running animation, must run every tick!
	 * @param scopeIn - if the player is aiming or not
	 */
	@Override
	public void run(boolean scopeIn)
	{
		if(scopeIn)
		{
			if(mX != x)
			{
				if(!invertX)
				{
					if(mX < x) mX += calculateMovement(X_MOVEMENT) * animationSpeed * xModifier;
					if(mX > x) mX = x;
				}
				else
				{
					if(mX > x) mX -= calculateMovement(X_MOVEMENT) * animationSpeed * xModifier;
					if(mX < x) mX = x;
				}
			}
			
			if(mY != y)
			{
				if(!invertY)
				{
					if(mY < y) mY += calculateMovement(Y_MOVEMENT) * animationSpeed * yModifier;
					if(mY > y) mY = y;
				}
				else
				{
					if(mY > y) mY -= calculateMovement(Y_MOVEMENT) * animationSpeed * yModifier;
					if(mY < y) mY = y;
				}
			}
			
			if(mZ != z)
			{
				if(!invertZ)
				{
					if(mZ < z) mZ += calculateMovement(Z_MOVEMENT) * animationSpeed * zModifier;
					if(mZ > z) mZ = z;
				}
				else
				{
					if(mZ > z) mZ -= calculateMovement(Z_MOVEMENT) * animationSpeed * zModifier;
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
					if(mX > 0) mX -= calculateMovement(X_MOVEMENT) * animationSpeed * xModifier;
					if(mX < 0) mX = 0;
				}
				
				else
				{
					if(mX < 0) mX += calculateMovement(X_MOVEMENT) * animationSpeed * xModifier;
					if(mX > 0) mX = 0;
				}
			}
			
			if(mY != 0)
			{
				if(!invertY)
				{
					if(mY > 0) mY -= calculateMovement(Y_MOVEMENT) * animationSpeed * yModifier;
					if(mY < 0) mY = 0;
				}
				
				else
				{
					if(mY < 0) mY += calculateMovement(Y_MOVEMENT) * animationSpeed * yModifier;
					if(mY > 0) mY = 0;
				}
			}
			
			if(mZ != 0)
			{
				if(!invertZ)
				{
					if(mZ > 0) mZ -= calculateMovement(Z_MOVEMENT) * animationSpeed * zModifier;
					if(mZ < 0) mZ = 0;
				}
				
				else
				{
					if(mZ < 0) mZ += calculateMovement(Z_MOVEMENT) * animationSpeed * zModifier;
					if(mZ > 0) mZ = 0;
				}
			}
		}
		
		GlStateManager.translate(mX, mY, mZ);
	}
	
	public void setMovementMultiplier(float multiplierX, float multiplierY, float multiplierZ)
	{
		this.xModifier = multiplierX;
		this.yModifier = multiplierY;
		this.zModifier = multiplierZ;
	}
	
	/**
	 * Use this to adjust Y position for different scopes
	 * @param y
	 */
	public void setYModifier(double y)
	{
		this.y = y;
	}
	
	/**
	 * resets animation process
	 */
	public void reset()
	{
		mX = 0;
		mY = 0;
		mZ = 0;
	}
	
	/**
	 * For inverting animation movement for values < 0
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setInvertedCoords(boolean x, boolean y, boolean z)
	{
		this.invertX = x;
		this.invertY = y;
		this.invertZ = z;
	}
	
	/**
	 * @return the final y value of animation
	 */
	public double getFinalY()
	{
		return y;
	}
	
	/**
	 * @return the current x value of animation model translation
	 */
	public double getX()
	{
		return mX;
	}
	
	/**
	 * @return the current y value of animation model translation
	 */
	public double getY()
	{
		return mY;
	}
	
	/**
	 * @return the current z value of animation model translation
	 */
	public double getZ()
	{
		return mZ;
	}
	
	/**
	 * @return if x is inverted value
	 */
	public boolean isXInverted()
	{
		return invertX;
	}
	
	/**
	 * @return if y is inverted value
	 */
	public boolean isYInverted()
	{
		return invertY;
	}
	
	/**
	 * @return if z is inverted value
	 */
	public boolean isZInverted()
	{
		return invertZ;
	}
}
