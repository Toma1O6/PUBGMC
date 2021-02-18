package dev.toma.pubgmc.config;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.client.CFGOtherSettings;
import dev.toma.pubgmc.config.client.CFGOverlaySettings;
import dev.toma.pubgmc.config.client.ClientConfig;
import dev.toma.pubgmc.config.common.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME + " Config")
public class ConfigPMC {

    @Name("Client options")
    public static ClientConfig client = new ClientConfig();

    @Name("Common options")
    public static CommonConfig common = new CommonConfig();

    @SideOnly(Side.CLIENT)
    public static CFGOverlaySettings overlays() {
        return client.overlays;
    }

    @SideOnly(Side.CLIENT)
    public static CFGOtherSettings other() {
        return client.other;
    }

    public static CFGWorld world() {
        return common.world;
    }

    public static CFGPlayer player() {
        return common.player;
    }

    public static CFGItems items() {
        return common.items;
    }

    public static CFGVehicles vehicles() {
        return common.vehicles;
    }

    public static CFGWeapons guns() {
        return common.weapons;
    }

    @Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
    public static class Synchronization {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent e) {
            if (e.getModID().equals(Pubgmc.MOD_ID)) {
                ConfigManager.sync(Pubgmc.MOD_ID, Type.INSTANCE);
            }
        }
    }
}
