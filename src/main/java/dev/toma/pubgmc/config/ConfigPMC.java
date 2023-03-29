package dev.toma.pubgmc.config;

import dev.toma.configuration.api.Config;
import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.client.ClientHandles;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.client.CFGOtherSettings;
import dev.toma.pubgmc.config.client.CFGOverlaySettings;
import dev.toma.pubgmc.config.client.ClientConfig;
import dev.toma.pubgmc.config.common.CFGVehicles;
import dev.toma.pubgmc.config.common.CFGWeapons;
import dev.toma.pubgmc.config.common.CFGWorld;
import dev.toma.pubgmc.config.common.CommonConfig;
import dev.toma.pubgmc.config.type.PMCConfigCreator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config
public class ConfigPMC implements ConfigPlugin {

    public static ClientConfig client;
    public static CommonConfig common;
    public static BooleanType developerMode;

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

    public static CFGVehicles vehicles() {
        return common.vehicles;
    }

    public static CFGWeapons guns() {
        return common.weapons;
    }

    @Override
    public String getModID() {
        return Pubgmc.MOD_ID;
    }

    @Override
    public String getConfigFileName() {
        return "pubgmc-config";
    }

    @Override
    public void buildConfigStructure(ConfigCreator builder) {
        client = builder.createObject(new ClientConfig(this), this);
        common = builder.createObject(new CommonConfig(this), this);
        developerMode = builder.createBoolean("Developer mode", false, "Enables use of some internal tools", "O - attachment placer", "N - hand placer", "M - animator");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ClientHandles getClientHandles() {
        return PMCClientHandles.PMC_HANDLES;
    }

    @Override
    public ConfigCreator builder(ObjectType configObject) {
        PMCConfigCreator configCreator = new PMCConfigCreator();
        configCreator.assignTo(configObject);
        return configCreator;
    }
}
