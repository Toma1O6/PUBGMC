package com.toma.pubgmc.animation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public static final Vector3f EMPTYVEC = new Vector3f(0f, 0f, 0f);
	public static final Vector3f[] ROTATION_VEC_EMPTY = new Vector3f[] {EMPTYVEC, EMPTYVEC};
	public float movementX,movementY,movementZ;
	
	@Nonnull
	public abstract Vector3f getMovementVec();
	
	@Nonnull
	public abstract Vector3f[] getRotationVectors();
	
	@Nonnull
	public abstract Vector3f getLeft();
	
	@Nonnull
	public abstract Vector3f getRight();
	
	public static float calculateMovement(float baseMovement)
	{
		float result = baseMovement * 60 / Minecraft.getDebugFPS();
		return result;
	}
	
	public void calculateMovementVariables(double x, double y, double z)
	{
		movementY = 0.0064f;
		movementX = (float)Math.abs((movementY * x) / y);
		movementZ = (float)Math.abs((movementY * z) / y);
	}
	
	public void onAnimationFinished()
	{
		movementX = 0f;
		movementY = 0f;
	}
}
