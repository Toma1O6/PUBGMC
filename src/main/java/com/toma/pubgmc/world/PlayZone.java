package com.toma.pubgmc.world;

public class PlayZone 
{
	private int zoneID;
	int startShrinking, shrinkingTime, finalSize;
	float damagePerBlock;
	String name;
	
	public PlayZone(int ID)
	{
		this.zoneID = ID;
		name = "Phase " + ID;
	}
	
	public void setup(int startShrinking, int shrinkingTime, int finalSize, float damage)
	{
		this.startShrinking = startShrinking;
		this.shrinkingTime = shrinkingTime;
		this.finalSize = finalSize;
		this.damagePerBlock = damage;
	}
	
	public int getID()
	{
		return zoneID;
	}
	
	public int getStartOfShrinking()
	{
		return startShrinking;
	}
	
	public int getShrinkingTime()
	{
		return shrinkingTime;
	}
	
	public int getFinalSize()
	{
		return finalSize;
	}
	
	public float getDamage()
	{
		return damagePerBlock;
	}
}
