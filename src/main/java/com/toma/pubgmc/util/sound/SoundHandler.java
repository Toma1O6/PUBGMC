package com.toma.pubgmc.util.sound;

import java.util.ArrayList;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.event.SoundTickEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SoundHandler
{
	private static final ArrayList<SoundEntry> ENTRIES = new ArrayList<SoundEntry>();
	
	public static void initSoundHandler()
	{
		ENTRIES.clear();
		Pubgmc.logger.info("Sound handler initialized!");
	}
	
	public static void putEntry(SoundEntry entry)
	{
		ENTRIES.add(entry);
	}
	
	public static void putEntry(SoundEvent event, int time, float volume)
	{
		SoundEntry entry = new SoundEntry(event, time, volume);
		ENTRIES.add(entry);
	}
	
	@EventBusSubscriber(Side.CLIENT)
	public static class Handler
	{
		@SubscribeEvent
		public static void tick(SoundTickEvent e)
		{
			if(!ENTRIES.isEmpty()) 
			{
				ENTRIES.forEach(SoundEntry::tick);
				
				for(SoundEntry entry : ENTRIES) {
					if(entry.getTime() == 0) {
						Minecraft.getMinecraft().player.playSound(entry.getSound(), entry.getVolume(), 1.0F);
						ENTRIES.remove(ENTRIES.indexOf(entry));
					}
				}
			}
		}
	}
}
