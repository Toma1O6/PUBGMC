package com.toma.pubgmc.animation;

import net.minecraft.client.renderer.GlStateManager;

public class HeldAnimations 
{
	private static final float ROTATION_MODIFIER = 0.175f;
	private static final double X_MOD = 0.0024d;
	private static final double Y_MOD = 0.0008d;
	private static final double Z_MOD = 0.0012d;
	
	private double mX, mY, mZ;
	private float rotX, rotY;
	private boolean small_weapon;
	
	public void runAnimation(boolean sprinting)
	{
		if(sprinting)
		{	
			if(small_weapon)
			{
				
			}
			
			//BIGGER WEAPONS
			else
			{
				if(rotY < 75f) rotY += ROTATION_MODIFIER;
				if(rotY > 75f) rotY = 75f;
				
				if(mX > -1) mX -= X_MOD;
				if(mZ > -0.4) mZ -= Z_MOD;
			}
		}
		
		else
		{
			if(mX != 0 || mY != 0 || mZ != 0)
			{
				if(mX < 0) mX += X_MOD;
				if(mX > 0) mX = 0;
				
				if(mZ < 0) mZ += Z_MOD;
				if(mZ > 0) mZ = 0;
			}
			
			if(rotY != 0)
			{
				if(rotY > 0) rotY -= ROTATION_MODIFIER;
				if(rotY < 0) rotY = 0;
			}
		}
		
		GlStateManager.rotate(rotY, 0, 1f, 0);
		GlStateManager.translate(mX, 0, mZ);
	}
	
	public void setWeaponType(boolean isSmallGun)
	{
		this.small_weapon = isSmallGun;
	}
	
	public boolean getWeaponType()
	{
		return small_weapon;
	}
}
