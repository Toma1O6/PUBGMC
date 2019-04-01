package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public double movementX,movementY,movementZ;
	
	public abstract void run(boolean boolParameter);
	
	public static double calculateMovement(double baseMovement)
	{
		double result = baseMovement * 60 / Minecraft.getDebugFPS();
		return result;
	}
	
	public void calculateMovementVariables(double x, double y, double z)
	{
		movementY = 0.0064;
		movementX = Math.abs((movementY * x) / y);
		movementZ = Math.abs((movementY * z) / y);
	}
}
