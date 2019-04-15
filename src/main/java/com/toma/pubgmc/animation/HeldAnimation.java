package com.toma.pubgmc.animation;

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
		
		boolean b = player.isSprinting();
		this.getHeldStyle().handle(this, b);
	}
	
	@Override
	public Vector3f[] getRotationVectors()
	{
		return new Vector3f[] {this.getRight(), this.getLeft()};
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(mx, my, mz);
	}
	
	@Override
	public Vector3f getLeft()
	{
		return new Vector3f(lrx, lry, lrz);
	}
	
	@Override
	public Vector3f getRight() 
	{
		return new Vector3f(rrx, rry, rrz);
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
		SMALL,
		NORMAL;
		
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
			// TODO
		}
		
		private void handleNormal(HeldAnimation animation, boolean sprint)
		{
			// TODO
		}
	}
}
