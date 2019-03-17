package com.toma.pubgmc.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy
{
	public void preInit(FMLPreInitializationEvent e);
	public void init(FMLInitializationEvent e);
	public void postInit(FMLPostInitializationEvent e);
	
	public default EntityPlayer getPlayer(MessageContext ctx)
	{
		return ctx.side.isClient() ? Minecraft.getMinecraft().player : ctx.getServerHandler().player;
	}
	
	public default World getWorld(MessageContext ctx)
	{
		return ctx.side.isClient() ? Minecraft.getMinecraft().world : ctx.getServerHandler().player.getServerWorld();
	}
}
