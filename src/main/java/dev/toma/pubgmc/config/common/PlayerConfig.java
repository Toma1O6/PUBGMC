package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public class PlayerConfig extends ObjectType {

    public BooleanType knockbackEnabled;
    public BooleanType forceInventoryRestrictions;
    public IntType proneCooldown;
    public BooleanType sneakResetFallDistance;
    public BooleanType autoParachute;
    public IntType autoParachuteStartDistance;
    public IntType autoParachuteHeight;
    public BooleanType autoParachuteConsumption;

    public PlayerConfig() {
        super("Player");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        knockbackEnabled = configCreator.createBoolean("Allow knockback", false);
        forceInventoryRestrictions = configCreator.createBoolean("Force Inventory Restrictions", true);
        proneCooldown = configCreator.createInt("Prone Cooldown", 30, 0, 100);
        sneakResetFallDistance = configCreator.createBoolean("Crouch as parachute", false, "Crouch before landing to avoid falling damage");
        autoParachute = configCreator.createBoolean("Auto parachure", false, "Auto deploy a parachute before falling from high");
        autoParachuteStartDistance = configCreator.createInt("Auto parachute start distance", 120, 4, 255, "Fall distance required to perform auto parachure");
        autoParachuteHeight = configCreator.createInt("Auto parachute height", 50, 10, 255, "Maximum height from the ground when auto parachuting");
        autoParachuteConsumption = configCreator.createBoolean("Auto parachute consumption", true, "If true, a parachute in inventory is needed");
    }
}
