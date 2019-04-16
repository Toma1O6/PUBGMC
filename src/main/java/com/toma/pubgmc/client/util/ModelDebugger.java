package com.toma.pubgmc.client.util;

import net.minecraft.client.renderer.GlStateManager;

public class ModelDebugger
{
	public static final double MODIFIER = 15f;
	public static final double SMALL_MODIFIER = 1f;
	
	public static ModelDebugger instance;
	public static float X = 0;
	public static float Y = 0;
	public static float Z = 0;
	public static float scale = 1f;
	
	public static void translateModel()
	{
		GlStateManager.translate(X, Y, Z);
	}
	
	public static void scaleModel()
	{
		GlStateManager.scale(scale, scale, scale);
	}
	
	public static void printCode()
	{
		System.out.println("========[ START ]========");
		if(scale != 1f)
		{
			System.out.println("GlStateManager.scale("+scale+", "+scale+", "+scale+");");
		}
		
		if(X != 0 || Y != 0 || Z != 0)
		{
			System.out.println("GlStateManager.translate("+X+", "+Y+", "+Z+");");
		}
		System.out.println("========[ END ]========");
	}
	
	public static void transformAllModifications()
	{
		scaleModel();
		translateModel();
	}
	
	public static void resetAllValues()
	{
		X = 0;
		Y = 0;
		Z = 0;
		scale = 1f;
	}
}
