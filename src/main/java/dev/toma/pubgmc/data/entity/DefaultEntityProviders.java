package dev.toma.pubgmc.data.entity;

import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;

import java.util.Arrays;

public final class DefaultEntityProviders {

    public static final String VEHICLE_SPAWNER = "vehicle";

    public static void registerDefaults() {
        EntityProviderManager.INSTANCE.registerDefaultConfiguration(VEHICLE_SPAWNER, new RandomChanceEntityProvider(0.2F, new RandomEntityProvider(Arrays.asList(
                new SimpleEntityProvider(EntityVehicleUAZ.class),
                new SimpleEntityProvider(EntityVehicleDacia.class)
        ))));
    }

    private DefaultEntityProviders() {}
}
