package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;

public class BombConfig extends ObjectType {

    public BooleanType grenadeGriefing;
    public DoubleType bombStrength;

    public BombConfig() {
        super("Bomb");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        grenadeGriefing = configCreator.createBoolean("Grenade griefing", false, "Determines whether blocks can be affected by grenades", "This will break blocks like TNT");
        bombStrength = configCreator.createDouble("Bomb strength", 1.0, 0, 5.0, "Special Bomb Effects from C4, fuel can, etc.", "Recommend to set it to no higher than 1.5");
    }
}
