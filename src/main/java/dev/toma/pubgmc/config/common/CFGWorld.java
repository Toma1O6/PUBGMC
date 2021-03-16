package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public final class CFGWorld extends ObjectType {

    public IntType planeHeight;
    public IntType planeDelay;
    public BooleanType titleZoneNotifications;
    public BooleanType weaponGriefing;

    public CFGWorld() {
        super("World");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        planeHeight = configCreator.createInt("Plane Y position", 150, 40, 255);
        planeDelay = configCreator.createInt("Plane start delay", 5, 0, 60, "Define how many seconds plane will wait before flying");
        titleZoneNotifications = configCreator.createBoolean("Zone notifications", true, "Toggle sending zone status to players");
        weaponGriefing = configCreator.createBoolean("Weapon griefing", true, "Determines whether blocks can be affected by bullets", "This will for example cause glass breaking");
    }
}
