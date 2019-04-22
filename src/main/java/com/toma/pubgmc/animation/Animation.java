package com.toma.pubgmc.animation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public static final Vector3f EMPTYVEC = new Vector3f(0f, 0f, 0f);
	public float movementX,movementY,movementZ;
	public float rotationX,rotationY,rotationZ;
	
	@Nonnull
	public abstract Vector3f getMovementVec();
	
	@Nonnull
	public abstract Vector3f getRotationVector();
	
	public static float calculateMovement(float baseMovement)
	{
		float result = baseMovement * 60 / Minecraft.getDebugFPS();
		return result;
	}
	
	public void calculateMovementVariables(float x, float y, float z)
	{
		movementY = 0.0064f;
		movementX = Math.abs((movementY * x) / y);
		movementZ = Math.abs((movementY * z) / y);
	}
	
	public void calculateRotationVariables(float x, float y, float z)
	{
		rotationY = 0.064f;
		rotationX = Math.abs((rotationY * x) / y);
		rotationZ = Math.abs((rotationY * z) / y);
	}
	
	public void calculateMotionVariables(float x, float y, float z, float rx, float ry, float rz)
	{
		this.calculateMovementVariables(x, y, z);
		this.calculateRotationVariables(rx, ry, rz);
	}
	
	public void onAnimationFinished()
	{
		movementX = 0f;
		movementY = 0f;
	}
}
