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

    public PlayerConfig() {
        super("Player");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        knockbackEnabled = configCreator.createBoolean("Allow knockback", false);
        forceInventoryRestrictions = configCreator.createBoolean("Force Inventory Restrictions", true);
        proneCooldown = configCreator.createInt("Prone Cooldown", 30, 0, 100);
        sneakResetFallDistance = configCreator.createBoolean("Crouch as parachute", false, "Crouch before landing to avoid falling damage");
    }
}
