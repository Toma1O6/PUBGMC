package com.toma.pubgmc.animation;

import javax.annotation.Nonnull;
import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public static final Vector3f EMPTYVEC = new Vector3f(0f, 0f, 0f);
	public float movementX,movementY,movementZ;
	
	@Nonnull
	public abstract Vector3f getMovementVec();
	
	@Nonnull
	public abstract Vector3f getRotationVector();
	
	public static final float calculateMovement(float baseMovement)
	{
		float result = baseMovement * 60 / Minecraft.getDebugFPS();
		return result;
	}
	
	public static float getPartialMovement(final float movement, final float finalPos, final float modifier)
	{
		float f = Math.abs(movement - finalPos) < modifier ? finalPos : movement < finalPos ? movement + calculateMovement(modifier) : movement - calculateMovement(modifier);
		return f;
	}
	
	public static float decreasePartialMovement(final float movement, final float finalPos, final float modifier)
	{
		float f = movement > finalPos ? movement - calculateMovement(modifier) : finalPos;
		return f;
	}
	
	public static float increasePartialMovement(final float movement, final float finalPos, final float modifier)
	{
		float f = movement < finalPos ? movement + calculateMovement(modifier) : finalPos;
		return f;
	}
	
	public final void calculateMovementVariables(float x, float y, float z)
	{
		movementY = 0.0064f;
		movementX = Math.abs((movementY * x) / y);
		movementZ = Math.abs((movementY * z) / y);
	}
	
	public void onAnimationFinished()
	{
		movementX = 0f;
		movementY = 0f;
	}
}
