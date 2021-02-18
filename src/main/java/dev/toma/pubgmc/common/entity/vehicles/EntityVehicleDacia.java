package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.EntityVehicle;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicleDacia extends EntityVehicle {
    private static final Vec3d[] VECTORS = new Vec3d[]{new Vec3d(1.5, 0.5, 0d), new Vec3d(-3.8, 0, -0.6)};
    private static final String[] VARIANTS = new String[]{"yellow", "white", "blue", "orange"};

    public EntityVehicleDacia(World world) {
        super(world);
        setSize(2f, 1.5f);
    }

    public EntityVehicleDacia(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
        maxHealth = ConfigPMC.common.vehicles.dacia.maxHealth;
        health = ConfigPMC.common.vehicles.dacia.maxHealth;
        maxSpeed = ConfigPMC.common.vehicles.dacia.maxSpeed;
        acceleration = ConfigPMC.common.vehicles.dacia.acceleration;
        turnSpeed = ConfigPMC.common.vehicles.dacia.turningSpeed;
        maximalTurningModifier = ConfigPMC.common.vehicles.dacia.maxTurningAngle;
    }

    @Override
    public int getMaximumCapacity() {
        return 4;
    }

    @Override
    public double getMountedYOffset() {
        return 0.25d;
    }

    @Override
    public String[] getTextureVariants() {
        return VARIANTS;
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
    protected float getPassengerXOffset(int passengerIndex) {
        return passengerIndex % 2 == 0 ? -0.3f : -1.5f;
    }

    @Override
    protected float getPassengerZOffset(int passengerIndex) {
        return passengerIndex > 1 ? 0.5f : -0.5f;
    }
}
