package com.toma.pubgmc.animation;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class HeldAnimation extends Animation
{
	private Vector3f value = EMPTYVEC;
	private float mx, my, mz;
	private float rrx, rry, rrz;
	private float lrx, lry, lrz;
	private HeldStyle style;
	private EntityPlayer player;
	
	public HeldAnimation() 
	{
		player = Minecraft.getMinecraft().player;
	}
	
	public void processAnimation()
	{
		if(player == null)
		{
			player = Minecraft.getMinecraft().player;
			return;
		}
		
		this.getHeldStyle().handle(this, player.isSprinting());
	}
	
	@Override
	public Quat4f[] getRotationVectors()
	{
		return new Quat4f[] {this.getRight(), this.getLeft()};
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(mx, my, mz);
	}
	
	@Override
	public Quat4f getLeft()
	{
		return new Quat4f(lrx, lry, lrz, 0f);
	}
	
	@Override
	public Quat4f getRight() 
	{
		return new Quat4f(rrx, rry, rrz, 0f);
	}
	
	public void setStyle(HeldStyle style)
	{
		this.style = style;
	}
	
	public HeldStyle getHeldStyle()
	{
		return style;
	}
	
	public enum HeldStyle
	{
		SMALL(EMPTYQUAT, EMPTYQUAT, 0f, 0f, 0f),
		NORMAL(new Quat4f(1.5f, 0f, -1.2f, 0f), new Quat4f(1.5f, 0f, 0f, 0f), 0f, 0f, 0f);
		
		public float x, y, z;
		public Quat4f left, right;
		
		private HeldStyle(Quat4f left, Quat4f right, float x, float y, float z)
		{
			this.left = left;
			this.right = right;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public void handle(HeldAnimation animation, boolean sprint)
		{
			if(this.equals(SMALL)) {
				handleSmall(animation, sprint);
			} else if(this.equals(NORMAL)) {
				handleNormal(animation, sprint);
			}
		}
		
		private void handleSmall(HeldAnimation animation, boolean sprint)
		{
			
		}
		
		private void handleNormal(HeldAnimation animation, boolean sprint)
		{
			
		}
	}
}
