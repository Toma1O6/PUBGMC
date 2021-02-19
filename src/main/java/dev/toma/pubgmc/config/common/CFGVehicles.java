package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.ObjectType;

public final class CFGVehicles extends ObjectType {

    final ConfigPlugin plugin;
    public CFGVehicle uaz;
    public CFGVehicle dacia;

    public CFGVehicles(ConfigPlugin plugin) {
        super("Vehicles");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        uaz = configCreator.createObject(new CFGVehicle("UAZ", 250.0F, 1.6F, 3.0F, 0.015F, 0.25F), plugin);
        dacia = configCreator.createObject(new CFGVehicle("Dacia", 200.0F, 2.35F, 3.3F, 0.01F, 0.3F), plugin);
    }
}
