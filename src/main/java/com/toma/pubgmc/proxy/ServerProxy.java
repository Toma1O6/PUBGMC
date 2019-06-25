package com.toma.pubgmc.proxy;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
	}
	
	public void init(FMLInitializationEvent e)
	{
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
	
	@Override
	public void notifyWorkbenchUpdate() {
	}
	
	@Override
	public void playDelayedSound(SoundEvent event, double x, double y, double z, float volume) {
	}
}
