package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import net.minecraft.client.renderer.GlStateManager;

public class AimAnimation extends Animation
{
	private final double x,z;
	private double y;
	private float animationSpeed = 1f;
	private double mX, mY, mZ;
	private boolean invertX,invertY,invertZ = false;
	
	/**
	 * Creates new Animation 
	 * @param x - the X translation of final animation movement
	 * @param y - the Y translation of final animation movement (change for red dot & holo scopes!)
	 * @param z - the Z translation of final animation movement
	 * @param speedMultiplier - The speed at which the animation will be performed (normal = 1f)
	 */
	public AimAnimation(double x, double y, double z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Starts running animation, must run every tick!
	 * @param scopeIn - if the player is aiming or not
	 */
	@Override
	public void run(boolean scopeIn)
	{
		if((movementX == 0 || movementZ == 0) && scopeIn) calculateMovementVariables(x, y, z);
		
		if(mX == 0 && mY == 0 && mZ == 0 && scopeIn) this.onAnimationFinished();

		if(scopeIn)
		{
			if(mX != x)
			{
				if(!invertX)
				{
					if(mX < x) mX += calculateMovement(movementX) * animationSpeed;
					if(mX > x) mX = x;
				}
				else
				{
					if(mX > x) mX -= calculateMovement(movementX) * animationSpeed;
					if(mX < x) mX = x;
				}
			}
			
			if(mY != y)
			{
				if(!invertY)
				{
					if(mY < y) mY += calculateMovement(movementY) * animationSpeed;
					if(mY > y) mY = y;
				}
				else
				{
					if(mY > y) mY -= calculateMovement(movementY) * animationSpeed;
					if(mY < y) mY = y;
				}
			}
			
			if(mZ != z)
			{
				if(!invertZ)
				{
					if(mZ < z) mZ += calculateMovement(movementZ) * animationSpeed;
					if(mZ > z) mZ = z;
				}
				else
				{
					if(mZ > z) mZ -= calculateMovement(movementZ) * animationSpeed;
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
					if(mX > 0) mX -= calculateMovement(movementX) * animationSpeed;
					if(mX < 0) mX = 0;
				}
				
				else
				{
					if(mX < 0) mX += calculateMovement(movementX) * animationSpeed;
					if(mX > 0) mX = 0;
				}
			}
			
			if(mY != 0)
			{
				if(!invertY)
				{
					if(mY > 0) mY -= calculateMovement(movementY) * animationSpeed;
					if(mY < 0) mY = 0;
				}
				
				else
				{
					if(mY < 0) mY += calculateMovement(movementY) * animationSpeed;
					if(mY > 0) mY = 0;
				}
			}
			
			if(mZ != 0)
			{
				if(!invertZ)
				{
					if(mZ > 0) mZ -= calculateMovement(movementZ) * animationSpeed;
					if(mZ < 0) mZ = 0;
				}
				
				else
				{
					if(mZ < 0) mZ += calculateMovement(movementZ) * animationSpeed;
					if(mZ > 0) mZ = 0;
				}
			}
		}
		
		GlStateManager.translate(mX, mY, mZ);
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
	public AimAnimation setInvertedCoords(boolean x, boolean y, boolean z)
	{
		this.invertX = x;
		this.invertY = y;
		this.invertZ = z;
		return this;
	}
	
	public Vector3f getVec3f()
	{
		return new Vector3f((float)mX, (float)mY, (float)mZ);
	}
	
	public AimAnimation speed(float speed)
	{
		animationSpeed = speed > 0 ? speed : 0.1f;
		return this;
	}
	
	public void onAnimationFinished()
	{
		movementX = 0;
		movementZ = 0;
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
