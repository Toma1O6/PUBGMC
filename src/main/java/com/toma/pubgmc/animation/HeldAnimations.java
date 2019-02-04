package com.toma.pubgmc.animation;

import net.minecraft.client.renderer.GlStateManager;

public class HeldAnimations extends Animation
{
	private static final float ROTATION_MODIFIER = 2.5f;
	private static final double X_MOD = 0.035d;
	private static final double Y_MOD = 0.03d;
	private static final double Z_MOD = 0.025d;
	
	private double mX, mY, mZ;
	private float rotX, rotY;
	private boolean small_weapon;
	
	@Override
	public void run(boolean sprinting)
	{
		if(sprinting)
		{	
			if(small_weapon)
			{
				if(rotX < 40f) rotX += calculateMovement(ROTATION_MODIFIER);
				if(rotX > 40f) rotX = 40f;
				
				if(mY < 0.5d) mY += calculateMovement(Y_MOD);
				if(mY > 0.5d) mY = 0.5d;
				
				if(mZ > -0.4d) mZ -= calculateMovement(Z_MOD);
				if(mZ < -0.4d) mZ = -0.4d;
			}
			
			//BIGGER WEAPONS
			else
			{
				if(rotY < 75f) rotY += calculateMovement(ROTATION_MODIFIER);
				if(rotY > 75f) rotY = 75f;
				
				if(mX > -1) mX -= calculateMovement(X_MOD);
				if(mZ > -0.4) mZ -= calculateMovement(Z_MOD);
			}
		}
		
		else
		{
			if(mX != 0 || mY != 0 || mZ != 0)
			{
				if(mX < 0) mX += calculateMovement(X_MOD);
				if(mX > 0) mX = 0;
				
				if(mY > 0) mY -= calculateMovement(Y_MOD);
				if(mY < 0) mY = 0;
				
				if(mZ < 0) mZ += calculateMovement(Z_MOD);
				if(mZ > 0) mZ = 0;
			}
			
			if(rotY != 0)
			{
				if(rotY > 0) rotY -= calculateMovement(ROTATION_MODIFIER);
				if(rotY < 0) rotY = 0;
			}
			
			if(rotX != 0)
			{
				if(rotX > 0) rotX -= calculateMovement(ROTATION_MODIFIER);
				if(rotX < 0) rotX = 0;
			}
		}
		
		GlStateManager.rotate(rotY, 0, 1f, 0);
		GlStateManager.rotate(rotX, 1f, 0f, 0f);
		GlStateManager.translate(mX, mY, mZ);
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
