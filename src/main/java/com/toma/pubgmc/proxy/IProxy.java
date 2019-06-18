package com.toma.pubgmc.proxy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{
	public void preInit(FMLPreInitializationEvent e);
	public void init(FMLInitializationEvent e);
	public void postInit(FMLPostInitializationEvent e);
	public void notifyWorkbenchUpdate();
}
