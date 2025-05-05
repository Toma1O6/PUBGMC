package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public class CFGWorld extends ObjectType {

    final ConfigPlugin plugin;
    public IntType planeHeight;
    public IntType planeDelay;
    public BooleanType gunGriefing;
    public IntType bulletTime;
    public IntType aiPathFindRange;
    public DamageConfig damages;
    public BooleanType grenadeGriefing;
    public BooleanType vehicleGriefing;
    public ClassicConfig classicss;

    public CFGWorld(ConfigPlugin plugin) {
        super("World");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        planeHeight = configCreator.createInt("Plane Y position", 150, 40, 255);
        planeDelay = configCreator.createInt("Plane start delay", 5, 0, 60, "Define how many seconds plane will wait before flying");
        gunGriefing = configCreator.createBoolean("Gun griefing", false, "Determines whether blocks can be affected by bullets", "This will for example cause glass breaking");
        grenadeGriefing = configCreator.createBoolean("Grenade griefing", false, "Determine whether blocks can be affected by grenades", "This will break blocks like TNT");
        vehicleGriefing = configCreator.createBoolean("Vehicle griefing", false, "Determine whether blocks can be affected by vehicle");
        bulletTime = configCreator.createInt("Bullet time", 100, 0,200,"Ticks before bullet dead");
        aiPathFindRange = configCreator.createInt("AI Pathfinding Range", 256, 32, 2048, "Max pathfinding for AIs", "Large values may lag your server");
        damages = configCreator.createObject(new DamageConfig(), plugin);
        classicss = configCreator.createObject(new ClassicConfig(), plugin);
    }
}
