package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public class DamageConfig extends ObjectType {

    public BooleanType bulletPenetration;
    public BooleanType molotovPenetration;
    public BooleanType vehiclePenetration;
    public BooleanType zonePenetration;
    public BooleanType fuelcanPenetration;

    public DamageConfig() {
        super("Damage");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        bulletPenetration = configCreator.createBoolean("Bullet Penetration", true, "Whether bullet should ignore vanilla armor");
        molotovPenetration = configCreator.createBoolean("Molotov Penetration", true, "Whether molotov should ignore vanilla armor");
        vehiclePenetration = configCreator.createBoolean("Vehicle Penetration", true, "Whether vehicle hit should ignore vanilla armor");
        zonePenetration = configCreator.createBoolean("Zone Penetration", true, "Whether zone damage should ignore vanilla armor");
        fuelcanPenetration = configCreator.createBoolean("Fuel can Penetration", true, "Whether fuel can should ignore vanilla armor");
    }
}
