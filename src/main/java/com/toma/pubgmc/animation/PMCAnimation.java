package com.toma.pubgmc.animation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.GlStateManager;

public class PMCAnimation
{
	private static PMCAnimation instance;
	public List<AnimationPoint> animations = new ArrayList<AnimationPoint>();
	public int speed;
	
	public static PMCAnimation getAnimationInstance()
	{
		return instance;
	}
	
	public void addAnimation(AnimationPoint animPoint)
	{
		animations.add(animPoint);
	}
	
	public List<AnimationPoint> getAnimation()
	{
		return animations;
	}
	
	public AnimationPoint getAnimationPoint(int id)
	{
		return id < animations.size() ? animations.get(id) : new AnimationPoint(0, 0, 0);
	}
	
	public void clearAnimation()
	{
		animations.clear();
	}
	
	public int getAnimationSize()
	{
		return animations.size();
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void runAnimation()
	{
		for(int i = 0; i < getAnimationSize(); i++)
		{
			AnimationPoint point = getAnimationPoint(i);
			double x = point.getX();
			double y = point.getY();
			double z = point.getZ();
			
			/*for(int k = 0; k < getSpeed(); k++)
			{
				GlStateManager.translate(x / speed, y / speed, z / speed);
			}*/
		}
	}
}
