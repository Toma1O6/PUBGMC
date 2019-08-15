package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.init.PMCSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicleUAZ extends EntityVehicle {
    private static final Vec3d[] VECTORS = {new Vec3d(2d, 1.5d, 0), new Vec3d(-1.9d, 0.3, -0.6d)};

    public EntityVehicleUAZ(World world) {
        super(world);
        setSize(2f, 1.5f);
    }

    public EntityVehicleUAZ(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
        maxHealth = ConfigPMC.common.vehicleSettings.uaz.maxHealth;
        health = ConfigPMC.common.vehicleSettings.uaz.maxHealth;
        maxSpeed = ConfigPMC.common.vehicleSettings.uaz.maxSpeed;
        acceleration = ConfigPMC.common.vehicleSettings.uaz.acceleration;
        maximalTurningModifier = ConfigPMC.common.vehicleSettings.uaz.maxTurningAngle;
        turnSpeed = ConfigPMC.common.vehicleSettings.uaz.turningSpeed;
        fuel = this.generateFuel();
    }

    @Override
    public int getMaximumCapacity() {
        return 4;
    }

    @Override
    public double getMountedYOffset() {
        return 0.75d;
    }

    @Override
    public Vec3d getEnginePosition() {
        return VECTORS[0];
    }

    @Override
    public Vec3d getExhaustPosition() {
        return VECTORS[1];
    }

    @Override
    public SoundEvent vehicleSound() {
        return PMCSounds.uaz;
    }

    @Override
    public String[] getTextureVariants() {
        return null;
    }
}
