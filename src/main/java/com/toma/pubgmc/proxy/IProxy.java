package com.toma.pubgmc.proxy;

import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    void preInit(FMLPreInitializationEvent e);

    void init(FMLInitializationEvent e);

    void postInit(FMLPostInitializationEvent e);

    void notifyWorkbenchUpdate();

    void playMCDelayedSound(SoundEvent event, double x, double y, double z, float volume, int delay);

    void playDelayedSound(SoundEvent event, double x, double y, double z, float volume);
}
