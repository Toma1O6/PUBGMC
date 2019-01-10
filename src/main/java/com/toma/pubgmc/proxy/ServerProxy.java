package com.toma.pubgmc.proxy;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.CommonEvents;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataStorage;
import com.toma.pubgmc.init.RegistryHandler;
import com.toma.pubgmc.util.handlers.ConfigHandler;
import com.toma.pubgmc.util.handlers.GuiHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
}
