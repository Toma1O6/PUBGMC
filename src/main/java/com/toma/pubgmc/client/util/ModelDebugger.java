package com.toma.pubgmc.client.util;

import net.minecraft.client.renderer.GlStateManager;

public class ModelDebugger
{
	public static final double MODIFIER = 0.1d;
	public static final double SMALL_MODIFIER = 0.001d;
	
	public static ModelDebugger instance;
	public static double X = 0;
	public static double Y = 0;
	public static double Z = 0;
	public static float RotX = 0f;
	public static float RotY = 0f;
	public static float RotZ = 0f;
	public static float scale = 1f;
	
	public static void translateModel()
	{
		GlStateManager.translate(X, Y, Z);
	}
	
	public static void rotateModel()
	{
		GlStateManager.rotate(90, RotX, RotY, RotZ);
	}
	
	public static void scaleModel()
	{
		GlStateManager.scale(scale, scale, scale);
	}
	
	public static void printCode()
	{
		System.out.println("========[ START ]========");
		if(RotX != 0 || RotY != 0 || RotZ != 0)
		{
			System.out.println("GlStateManager.rotate(90, "+RotX+", "+RotY+", "+RotZ+");");
		}
		
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
		
		if(RotX != 0f || RotY != 0f || RotZ != 0f)
		{
			rotateModel();
		}
		
		translateModel();
	}
	
	public static void resetAllValues()
	{
		X = 0;
		Y = 0;
		Z = 0;
		RotX = 0f;
		RotY = 0f;
		RotZ = 0f;
		scale = 1f;
	}
}
