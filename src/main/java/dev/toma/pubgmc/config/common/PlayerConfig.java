package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public class PlayerConfig extends ObjectType {

    public BooleanType knockbackEnabled;
    public BooleanType inventoryRestrictions;

    public PlayerConfig() {
        super("Player");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        knockbackEnabled = configCreator.createBoolean("Allow knockback", false);
        inventoryRestrictions = configCreator.createBoolean("Inventory restrictions", true, "Your inventory capacity will be affected by equipped backpack");
    }
}
