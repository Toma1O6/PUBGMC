package dev.toma.pubgmc.common.entity.controllable;

import dev.toma.pubgmc.common.entity.util.VehicleCategory;
import net.minecraft.world.World;

public abstract class EntityWaterVehicle extends EntityDriveable {

    public EntityWaterVehicle(World world) {
        super(world);
    }

    @Override
    public VehicleCategory getVehicleCategory() {
        return VehicleCategory.WATER;
    }
}
