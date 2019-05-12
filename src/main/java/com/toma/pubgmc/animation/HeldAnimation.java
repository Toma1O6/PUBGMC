package com.toma.pubgmc.animation;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class HeldAnimation extends Animation
{
	private Vector3f value = EMPTYVEC;
	private float mx, my, mz;
	private float rx, ry, rz;
	private final HeldStyle style;
	
	public HeldAnimation(HeldStyle style) 
	{
		this.style = style;
	}
	
	public void processAnimation()
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			if(player == null)
			{
				player = Minecraft.getMinecraft().player;
				return;
			}

			this.getHeldStyle().handle(this, player.isSprinting());
		}
	}
	
	@Override
	public Vector3f getRotationVector()
	{
		return new Vector3f(rx, ry, rz);
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(mx, my, mz);
	}
	
	public HeldStyle getHeldStyle()
	{
		return style;
	}
	
	private boolean isNormalModeFinished()
	{
		return ry == style.rotation.y && mx == style.x;
	}
	
	private boolean hasReturned()
	{
		return ry == 0f && mx == 0f;
	}
	
	private boolean isSmallModeRotFinished()
	{
		return rx == style.rotation.x;
	}
	
	public enum HeldStyle
	{
		SMALL(new Vector3f(30f, 0f, 0f), 0f, 0f, 0f),
		NORMAL(new Vector3f(0f, 80f, 0f), -0.5f, 0f, 0f);
		
		public final float x, y, z;
		public final Vector3f rotation;
		
		private HeldStyle(Vector3f rot, float x, float y, float z)
		{
			this.rotation = rot;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public void handle(HeldAnimation a, boolean sprint)
		{
			if(this.equals(SMALL)) {
				handleSmall(a, sprint);
			} else if(this.equals(NORMAL)) {
				handleNormal(a, sprint);
			}
		}
		
		private void handleSmall(HeldAnimation a, boolean sprint)
		{
			if(sprint && !a.isSmallModeRotFinished())
			{
				a.rx = increasePartialMovement(a.rx, rotation.x, 3.5f);
			}
			
			else if(!sprint && a.rx != 0)
			{
				a.rx = decreasePartialMovement(a.rx, 0f, 3.5f);
			}
			
			GlStateManager.rotate(a.rx, 1f, 0f, 0f);
		}
		
		private void handleNormal(HeldAnimation a, boolean sprint)
		{
			if(sprint && !a.isNormalModeFinished())
			{
				a.ry = increasePartialMovement(a.ry, rotation.y, 4f);
				a.mx = decreasePartialMovement(a.mx, x, 0.025f);
			}
			
			else if(!sprint && !a.hasReturned())
			{
				a.ry = decreasePartialMovement(a.ry, 0f, 4f);
				a.mx = increasePartialMovement(a.mx, 0f, 0.025f);
			}
			
			GlStateManager.translate(a.mx, a.my, a.mz);
			GlStateManager.rotate(a.ry, 0f, 1f, 0f);
		}
	}
}
