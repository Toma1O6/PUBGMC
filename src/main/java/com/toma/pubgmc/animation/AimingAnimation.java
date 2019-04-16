package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;

public class AimingAnimation extends Animation
{
	final Vector3f values;
	float mx, my, mz;
	float speed;
	boolean invx, invy, invz;
	
	public AimingAnimation(float x, float y, float z)
	{
		this.speed = 3f;
		values = new Vector3f(x, y, z);
		invx = values.x < 0;
		invy = values.y < 0;
		invz = values.z < 0;
	}
	
	public AimingAnimation(float x, float y, float z, float speed)
	{
		values = new Vector3f(x, y, z);
		invx = values.x < 0;
		invy = values.y < 0;
		invz = values.z < 0;
		this.speed = speed;
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(mx, my, mz);
	}
	
	@Override
	public Vector3f getLeft()
	{
		return EMPTYVEC;
	}
	
	@Override
	public Vector3f getRight() 
	{
		return EMPTYVEC;
	}
	
	@Override
	public Vector3f[] getRotationVectors()
	{
		return ROTATION_VEC_EMPTY;
	}
	
	public Vector3f getFinalState()
	{
		return values;
	}
	
	public void processAnimation(boolean aiming)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			if((movementX == 0f || movementZ == 0f) && aiming) this.calculateMovementVariables(values.x, values.y, values.z);
			
			if(mx == 0 && my == 0 && mz == 0 && aiming) this.onAnimationFinished();

			if(aiming && !isFinished())
			{
				handleAiming();
			}
			
			else if(!aiming && !hasReturned())
			{
				handleReturning();
			}
			
			else return;
		}
	}
	
	protected boolean isFinished()
	{
		return mx == values.x && my == values.y && mz == values.z;
	}
	
	protected boolean hasReturned()
	{
		return mx == 0 && my == 0 && mz == 0;
	}
	
	private void handleReturning()
	{
		mx = invx ? mx < 0 ? mx + (calculateMovement(movementX) * speed) : 0 : mx > 0 ? mx - (calculateMovement(movementX) * speed) : 0;
		my = invy ? my < 0 ? my + (calculateMovement(movementY) * speed) : 0 : my > 0 ? my - (calculateMovement(movementY) * speed) : 0;
		mz = invz ? mz < 0 ? mz + (calculateMovement(movementZ) * speed) : 0 : mz > 0 ? mz - (calculateMovement(movementZ) * speed) : 0;
	}
	
	private void handleAiming()
	{
		mx = invx ? mx > values.x ? mx - (calculateMovement(movementX) * speed) : values.x : mx < values.x ? mx + (calculateMovement(movementX) * speed) : values.x;
		my = invy ? my > values.y ? my - (calculateMovement(movementY) * speed) : values.y : my < values.y ? my + (calculateMovement(movementY) * speed) : values.y;
		mz = invz ? mz > values.z ? mz - (calculateMovement(movementZ) * speed) : values.z : mz < values.z ? mz + (calculateMovement(movementZ) * speed) : values.z;
	}
	
	private void reset()
	{
		mx = 0f;
		my = 0f;
		mz = 0f;
	}
	
	@Override
	public String toString()
	{
		return "Final=" + values + ", Current=" + mx + ", " + my + ", " + mz;
	}
}
