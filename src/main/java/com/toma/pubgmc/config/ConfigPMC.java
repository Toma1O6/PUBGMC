package com.toma.pubgmc.config;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.config.client.ClientConfig;
import com.toma.pubgmc.config.common.CommonConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME + " Config")
public class ConfigPMC {

    @Name("Client options")
    public static ClientConfig client = new ClientConfig();

    @Name("Common options")
    public static CommonConfig common = new CommonConfig();


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
