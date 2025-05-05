package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;

public class ClassicConfig extends ObjectType {

    public BooleanType vehicleBomb;
    public DoubleType bombStrength;
    public BooleanType speedForce;
    public BooleanType vulnerableVehicle;

    public ClassicConfig() {
        super("Classic");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        vehicleBomb = configCreator.createBoolean("Vehicle bomb", true, "Use C4 and fuel can to blow up vehicles", "This feature has existed in PUBG for 8 years");
        bombStrength = configCreator.createDouble("Bomb strength", 1.0, 0, 5.0, "Special Bomb Effects from C4, fuel can, etc.", "Recommend to set it to no higher than 1.5");
//        speedForce = configCreator.createBoolean("Speed force", true, "Get superfast speed through vehicles");
//        vulnerableVehicle = configCreator.createBoolean("Vulnerable vehicle", false, "Make vehicles more likely to explode");
    }
}
