package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public class PlayerConfig extends ObjectType {

    public BooleanType knockbackEnabled;
    public BooleanType forceInventoryRestrictions;

    public PlayerConfig() {
        super("Player");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        knockbackEnabled = configCreator.createBoolean("Allow knockback", false);
        forceInventoryRestrictions = configCreator.createBoolean("Force Inventory Restrictions", false);
    }
}
