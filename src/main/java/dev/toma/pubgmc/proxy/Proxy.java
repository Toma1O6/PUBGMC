package dev.toma.pubgmc.proxy;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.GunBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class Proxy {
    public abstract void preInit(FMLPreInitializationEvent e);

    public abstract void init(FMLInitializationEvent e);

    public abstract void postInit(FMLPostInitializationEvent e);

    public void handleReloadStart(IPlayerData data, GunBase gun, ItemStack stack) {

    }

    public void handleReloadInterrupt() {

    }

    public void notifyWorkbenchUpdate() {

    }

    public void playMCDelayedSound(SoundEvent event, double x, double y, double z, float volume, int delay) {

    }

    public void playDelayedSound(SoundEvent event, double x, double y, double z, float volume) {

    }

    public void initWeapon(GunBuilder builder, GunBase gunBase) {

    }
}
