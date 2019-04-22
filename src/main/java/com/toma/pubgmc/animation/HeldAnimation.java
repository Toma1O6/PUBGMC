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
	private HeldStyle style;
	private EntityPlayer player;
	
	public HeldAnimation() 
	{
		style = HeldStyle.NORMAL;
		player = Minecraft.getMinecraft().player;
	}
	
	public void processAnimation()
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
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
	
	public void setStyle(HeldStyle style)
	{
		this.style = style;
	}
	
	public HeldStyle getHeldStyle()
	{
		return style;
	}
	
	private boolean isNormalModeFinished()
	{
		return rx == style.rotation.x && ry == style.rotation.y && rz == style.rotation.z;
	}
	
	private boolean hasReturned()
	{
		return rx == 0 && ry == 0 && rz == 0;
	}
	
	public enum HeldStyle
	{
		SMALL(EMPTYVEC, 0f, 0f, 0f),
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
			
		}
		
		private void handleNormal(HeldAnimation a, boolean sprint)
		{
			if(sprint && !a.isNormalModeFinished())
			{
				a.ry = a.ry < rotation.y ? a.ry + calculateMovement(4f) : rotation.y;
				a.mx = a.mx > x ? a.mx - calculateMovement(0.02f) : x;
			}
			
			else if(!sprint && !a.hasReturned())
			{
				a.ry = a.ry > 0f ? a.ry - calculateMovement(4f) : 0f;
				a.mx = a.mx < 0f ? a.mx + calculateMovement(0.02f) : 0f;
			}
			
			GlStateManager.translate(a.mx, a.my, a.mz);
			GlStateManager.rotate(a.ry, 0f, 1f, 0f);
		}
	}
}
