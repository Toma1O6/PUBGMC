package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;

public abstract class Animation
{
	public abstract void run(boolean boolParameter);
	
	public static double calculateMovement(double baseMovement)
	{
		double result = baseMovement * 60 / Minecraft.getDebugFPS();
		return result;
	}
}
