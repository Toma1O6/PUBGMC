package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public class PlayerConfig extends ObjectType {

    final ConfigPlugin plugin;
    public BooleanType knockbackEnabled;
    public BooleanType forceInventoryRestrictions;
    public IntType proneCooldown;
    public ParachuteConfig parachutes;

    public PlayerConfig(ConfigPlugin plugin) {
        super("Player");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        knockbackEnabled = configCreator.createBoolean("Allow knockback", false);
        forceInventoryRestrictions = configCreator.createBoolean("Force Inventory Restrictions", true);
        proneCooldown = configCreator.createInt("Prone Cooldown", 30, 0, 100);
        parachutes = configCreator.createObject(new ParachuteConfig(), plugin);
    }
}
