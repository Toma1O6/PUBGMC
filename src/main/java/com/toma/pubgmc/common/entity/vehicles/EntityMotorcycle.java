package com.toma.pubgmc.common.entity.vehicles;

import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.init.PMCSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMotorcycle extends EntityVehicle {
    public EntityMotorcycle(World world) {
        super(world);
    }

    public EntityMotorcycle(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
        maxHealth = 150f;
        health = 150f;
        maxSpeed = 2.3f;
        acceleration = 0.025f;
        turnSpeed = 0.25f;
        fuel = generateFuel();
    }

    @Override
    public Vec3d getEnginePosition() {
        return Vec3d.ZERO;
    }

    @Override
    public Vec3d getExhaustPosition() {
        return Vec3d.ZERO;
    }

    @Override
    public int getMaximumCapacity() {
        return 2;
    }

    @Override
    public String[] getTextureVariants() {
        return null;
    }

    @Override
    public SoundEvent vehicleSound() {
        return PMCSounds.uaz;
    }
}
