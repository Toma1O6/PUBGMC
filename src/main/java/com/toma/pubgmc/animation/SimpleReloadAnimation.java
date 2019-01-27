package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class SimpleReloadAnimation extends Animation
{
	private static final float ROTATION = 1.5f;
	private static final double MOV_X = 0.015D;
	private static final double MOV_Y = 0.010D;
	private static final double MOV_Z = 0.005D;
	
	private double mx,my,mz;
	private float rx,ry,rz;
	private int reloadtime = 0;
	private final int maxReloadTime;
	private boolean shouldStop = false;
	private ReloadStyle style;
	
	public SimpleReloadAnimation(ReloadStyle style, int weaponReloadTime)
	{
		this.maxReloadTime = weaponReloadTime;
		this.style = style;
	}
	
	@Override
	public void run(boolean reloading) 
	{
		if(reloading && !shouldStop)
		{
			handleReloading(maxReloadTime);
			
			if(style == ReloadStyle.NORMAL)
			{
				
			}
			
			else if(style == ReloadStyle.SHOTGUN)
			{
				
			}
		}
		
		else
		{
			if(rx != 0)
			{
				if(rx > 0 && rx - calculateMovement(ROTATION) < 0) rx = 0f;
				
				if(rx > 0) rx -= calculateMovement(ROTATION);
				else rx += calculateMovement(ROTATION);
			}
			
			if(ry != 0)
			{
				if(ry > 0 && ry - calculateMovement(ROTATION) < 0) ry = 0f;
				
				if(ry > 0) ry -= calculateMovement(ROTATION);
				else ry += calculateMovement(ROTATION);
			}
			
			if(rz != 0)
			{
				if(rz > 0 && rz - calculateMovement(ROTATION) < 0) rz = 0f;
				
				if(rz > 0) rz -= calculateMovement(ROTATION);
				else rz += calculateMovement(ROTATION);
			}
			
			if(mx != 0)
			{
				if(mx > 0 && mx - calculateMovement(MOV_X) < 0) mx = 0;
				
				if(mx > 0) mx -= calculateMovement(MOV_X);
				else mx += calculateMovement(MOV_X);
			}
			
			if(my != 0)
			{
				if(my > 0 && my - calculateMovement(MOV_Y) < 0) my = 0;
				
				if(my > 0) my -= calculateMovement(MOV_Y);
				else my += calculateMovement(MOV_Y);
			}
			
			if(mz != 0)
			{
				if(mz > 0 && mz - calculateMovement(MOV_Z) < 0) mz = 0;
				
				if(mz > 0) mz -= calculateMovement(MOV_Z);
				else mz += calculateMovement(MOV_Z);
			}
		}
		
		GlStateManager.rotate(rx, 1, 0, 0);
		GlStateManager.rotate(ry, 0, 1, 0);
		GlStateManager.rotate(rz, 0, 0, 1);
		GlStateManager.translate(mx, my, mz);
	}
	
	private void handleReloading(int maxReloadTime)
	{
		reloadtime++;
		int calculatedReload = maxReloadTime*Minecraft.getMinecraft().getDebugFPS();
		shouldStop = reloadtime >= calculatedReload - calculatedReload*0.9;
	}
	
	public enum ReloadStyle
	{
		NORMAL,
		SHOTGUN;
		
		private double x,y,z;
		
		private double getX(ReloadStyle style)
		{
			return style == NORMAL ? 1.5d : 1d;
		}
		
		private double getY(ReloadStyle style)
		{
			return style == NORMAL ? 1.5d : 1d;
		}
		
		private double getZ(ReloadStyle style)
		{
			return style == NORMAL ? 1.5d : 1d;
		}
	}
}
