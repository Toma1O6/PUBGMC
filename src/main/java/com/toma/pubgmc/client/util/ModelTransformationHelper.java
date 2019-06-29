package com.toma.pubgmc.client.util;

import net.minecraft.client.renderer.GlStateManager;

public class ModelTransformationHelper 
{
	public static ModelTransformationHelper instance;
	private static ModelHelper d = ModelHelper.instance;
	
	public static void defaultPistolTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-5, -10, 6);
		GlStateManager.scale(0.3, 0.3, 0.3);
	}
	
	public static void defaultShotgunTransform()
	{
		defaultPistolTransform();
		GlStateManager.scale(0.5999999, 0.5999999, 0.5999999);
		GlStateManager.translate(2.0, 7.0, 0.0);
	}
	
	public static void defaultSMGTransform()
	{
		defaultPistolTransform();
		GlStateManager.translate(0.0, 5.0, 0.0);
	}
	
	public static void defaultARTransform()
	{
		defaultPistolTransform();
		GlStateManager.scale(0.6999999, 0.6999999, 0.6999999);
		GlStateManager.translate(0.0, 11.0, 0.0);
	}
	
	public static void defaultSRTransform()
	{
		defaultARTransform();
	}
	
	public static void defaultPistolSilencerTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.translate(5.4, -17.2, 1.4);
	}
	
	public static void defaultPistolRedDotTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.15, 0.15, 0.15);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-32.8, -70.5, 32);
	}
	
	public static void defaultHoloTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.17999992, 0.17999992, 0.17999992);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-32.8, -70.5, 32);
		GlStateManager.translate(7.0, 8.600000000000001, -0.6000000000000001);
	}
	
	public static void default2XTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.2, 0.2, 0.2);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-32.8, -70.5, 32);
	}
	
	public static void default4XTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.2, 0.2, 0.2);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-32.8, -70.5, 32);
	}
	
	public static void default8XTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.2, 0.2, 0.2);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.scale(0.9, 0.9, 0.9);
		GlStateManager.translate(-27.7, -63.0, 22.0);
	}
	
	public static void default15XTransform()
	{
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.scale(0.2, 0.2, 0.2);
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.scale(0.9, 0.9, 0.9);
		GlStateManager.translate(-27.8, -64.0, 26.0);
	}
	
	public static void silencerSMGTransform()
	{
		GlStateManager.scale(0.02999994, 0.02999994, 0.02999994);
		GlStateManager.translate(16.6, 1.0, 0.0);
	}
	
	public static void silencerARTransform()
	{
		GlStateManager.scale(0.03, 0.03, 0.03);
		GlStateManager.translate(16.7, 1.0, -22.0);
	}
	
	public static void silencerSRTransform()
	{
		GlStateManager.scale(0.035, 0.035, 0.04);
		GlStateManager.translate(14.4, -2.6, -20.0);
	}
	
	public static void verticalGripTransform()
	{
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.scale(0.01999994, 0.01999994, 0.01999994);
		GlStateManager.translate(-24.0, -43.3, 2.0);
	}
	
	public static void angledGripTransform()
	{
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.scale(0.02999994, 0.02999994, 0.02999994);
		GlStateManager.translate(-24.0, -43.3, 2.0);
		GlStateManager.translate(40.0, 5.0, -1.0);
	}
}
