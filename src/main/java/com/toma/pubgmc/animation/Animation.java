package com.toma.pubgmc.animation;

import javax.annotation.Nonnull;
import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.Pair;

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
	
	public static boolean isPartMovementFinished(IPartAnimated part)
	{
		Vector3f transl = (Vector3f)part.animationSteps()[part.currentStep()].getRight();
		Vector3f rot = (Vector3f)part.animationSteps()[part.currentStep()].getLeft();
		Vector3f magTransl = part.getPartMovement();
		Vector3f magRot = part.getPartRotation();
		return magTransl.x == transl.x && magTransl.y == transl.y && magTransl.z == transl.z &&
				magRot.x == rot.x && magRot.y == rot.y && magRot.z == rot.z;
	}
	
	public static boolean isPartReturned(IPartAnimated part)
	{
		Vector3f translation = part.getPartMovement();
		Vector3f rotation = part.getPartRotation();
		return translation.x == 0f && translation.y == 0f && translation.z == 0f 
				&& rotation.x == part.getDefaultRotationAngles()[0] && rotation.y == part.getDefaultRotationAngles()[1] && rotation.z == part.getDefaultRotationAngles()[2];
	}
	
	public static boolean canExecuteNextStep(int current, Pair[] group)
	{
		return current+1 < group.length;
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
