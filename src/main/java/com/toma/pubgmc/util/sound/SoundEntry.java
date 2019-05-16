package com.toma.pubgmc.util.sound;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class SoundEntry
{
	private final SoundEvent event;
	private final float volume;
	private int time;
	
	public SoundEntry(SoundEvent soundToPlay, int timeLeft, float volume)
	{
		this.event = soundToPlay;
		this.time = timeLeft;
		this.volume = volume;
	}
	
	public static int getTimeByDistance(BlockPos pos, EntityPlayer player)
	{
		return 0;
	}
	
	public SoundEvent getSound()
	{
		return event;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public float getVolume()
	{
		return volume;
	}
	
	public void tick()
	{
		--time;
	}
}
