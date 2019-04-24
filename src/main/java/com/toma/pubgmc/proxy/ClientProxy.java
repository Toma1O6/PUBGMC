package com.toma.pubgmc.proxy;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.client.ClientEvents;
import com.toma.pubgmc.client.renderer.LootSpawnerRenderer;
import com.toma.pubgmc.client.renderer.RenderGrenade;
import com.toma.pubgmc.client.renderer.RenderMolotov;
import com.toma.pubgmc.client.renderer.RenderParachute;
import com.toma.pubgmc.client.renderer.RenderPlane;
import com.toma.pubgmc.client.renderer.RenderSmokeGrenade;
import com.toma.pubgmc.client.renderer.RenderUAZ;
import com.toma.pubgmc.client.util.KeyBinds;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.entity.EntityMolotov;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.entity.EntitySmokeGrenade;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy implements IProxy
{
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent e)
	{
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
		registerEntityRenderers();
	}
	
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent e)
	{
		KeyBinds.registerKeybinding();
		
		if(ConfigPMC.client.other.lootRenderType > 0)
		{
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootSpawner.class, new LootSpawnerRenderer());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void postInit(FMLPostInitializationEvent e)
	{
	}
	
	private static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, RenderGrenade::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeGrenade.class, RenderSmokeGrenade::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, RenderMolotov::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityParachute.class, RenderParachute::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVehicleUAZ.class, RenderUAZ::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPlane.class, RenderPlane::new);
	}
}
