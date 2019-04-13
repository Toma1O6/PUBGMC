package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public float movementX,movementY,movementZ;
	
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
