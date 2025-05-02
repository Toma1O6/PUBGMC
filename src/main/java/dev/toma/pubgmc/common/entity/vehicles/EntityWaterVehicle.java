package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.util.VehicleCategory;
import net.minecraft.world.World;

public abstract class EntityWaterVehicle extends EntityVehicle {

    public EntityWaterVehicle(World world) {
        super(world);
    }

    @Override
    public VehicleCategory getVehicleCategory() {
        return VehicleCategory.WATER;
    }

    @Override
    protected float getStepHeight() {
        return 0.0F;
    }
}
