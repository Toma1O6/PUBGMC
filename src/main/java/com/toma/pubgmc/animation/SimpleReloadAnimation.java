package com.toma.pubgmc.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class SimpleReloadAnimation extends Animation
{
	private static final float ROTATION = 1.0f;
	private static final double MOV_X = 0.025D;
	private static final double MOV_Y = 0.02D;
	private static final double MOV_Z = 0.03D;
	
	private double mx,my,mz;
	private float rx,ry,rz;
	private ReloadStyle style;
	
	public SimpleReloadAnimation(ReloadStyle style)
	{
		this.style = style;
	}
	
	@Override
	public void run(boolean reloading) 
	{ 
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			if(reloading)
			{
				if(this.rx < style.getRotationX()) this.rx += calculateMovement(ROTATION) * 3f;
				if(this.rx > style.getRotationX()) this.rx = style.getRotationX();
					
				if(this.ry < style.getRotationY()) this.ry += calculateMovement(ROTATION);
				if(this.ry > style.getRotationY()) this.ry = style.getRotationY();
					
				if(this.rz > style.getRotationZ()) this.rz -= calculateMovement(ROTATION);
				if(this.rz < style.getRotationZ()) this.rz = style.getRotationZ();
					
				if(this.mx > style.getX()) this.mx -= calculateMovement(MOV_X);
				if(this.mx < style.getX()) this.mx = style.getX();
					
				if(this.my < style.getY()) this.my += calculateMovement(MOV_Y);
				if(this.my > style.getY()) this.my = style.getY();
					
				if(this.mz > style.getZ()) this.mz -= calculateMovement(MOV_Z);
				if(this.mz < style.getZ()) this.mz = style.getZ();
			}
			
			else
			{
				if(rx != 0)
				{
					if(rx > 0) rx -= calculateMovement(ROTATION)*3f;
					if(rx < 0) rx = 0;
				}
				
				if(ry != 0)
				{
					if(ry > 0) ry -= calculateMovement(ROTATION);
					if(ry < 0) ry = 0;
				}
				
				if(rz != 0)
				{
					if(rz < 0) rz += calculateMovement(ROTATION);
					if(rz > 0) rz = 0;
				}
				
				if(mx != 0)
				{
					if(mx < 0) mx += calculateMovement(MOV_X);
					if(mx > 0) mx = 0;
				}
				
				if(my != 0)
				{
					if(my > 0) my -= calculateMovement(MOV_Y);
					if(my < 0) my = 0;
				}
				
				if(mz != 0)
				{
					if(mz < 0) mz += calculateMovement(MOV_Z);
					if(mz > 0) mz = 0;
				}
			}
		}
		
		GlStateManager.rotate(rx, 1, 0, 0);
		GlStateManager.rotate(ry, 0, 1, 0);
		GlStateManager.rotate(rz, 0, 0, 1);
		GlStateManager.translate(mx, my, mz);
	}
	
	public void reset()
	{
		mx = 0;
		my = 0;
		mz = 0;
		rx = 0;
		ry = 0;
		rz = 0;
	}
	
	public ReloadStyle getReloadStyle()
	{
		return style;
	}
	
	public enum ReloadStyle
	{
		NORMAL(-0.4, 0.3, -0.5, 45f, 15f, -20f),
		SHOTGUN(-0.9, 0, -0.3, 25f, 75f, -35f);
		
		private double x,y,z;
		private float rx,ry,rz;
		
		ReloadStyle(double x, double y, double z, float rx, float ry, float rz)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.rx = rx;
			this.ry = ry;
			this.rz = rz;
		}
		
		protected double getX()
		{
			return x;
		}
		
		protected double getY()
		{
			return y;
		}
		
		protected double getZ()
		{
			return z;
		}
		
		protected float getRotationX()
		{
			return rx;
		}
		
		protected float getRotationY()
		{
			return ry;
		}
		
		protected float getRotationZ()
		{
			return rz;
		}
		
		public void setTranslation(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public void setRotation(float x, float y, float z)
		{
			this.rx = x;
			this.ry = y;
			this.rz = z;
		}
	}
}
