package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.entity.controllable.EntityLandVehicle;
import dev.toma.pubgmc.common.entity.util.Seat;
import dev.toma.pubgmc.common.entity.util.Wheel;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class EntityVehicleUAZ extends EntityLandVehicle {

    private static final Vec3d ENGINE_POSITION = new Vec3d(2.0, 1.5, 0.0);
    private static final Vec3d EXHAUST_POSITION = new Vec3d(-1.9, 0.3, -0.6);
    private static final List<Seat> SEATS = DevUtil.make(new ArrayList<>(), list -> {
        list.add(Seat.createDriver(0.4, 0.4, 0.2));
        list.add(Seat.createPassenger(-0.4, 0.4, 0.2));
    });

    public EntityVehicleUAZ(World world) {
        super(world);
        this.setSize(2f, 1.5f);
    }

    @Override
    public float getMaxHealth() {
        return 1.0F; // TODO config
    }

    @Override
    public float getFuelTankCapacity() {
        return 120.0F;
    }

    @Override
    public void createWheels(Consumer<Wheel> registration) {
        final float wheelSize = 0.75F;
        final float wheelHealth = 20.0F;

        Wheel frontRight = new Wheel(this, "fr", new Vec3d(-1.0, 0.0, 1.8), wheelSize, wheelHealth);
        frontRight.setAccelerationWheel(true);
        frontRight.setTurnWheel(true);
        registration.accept(frontRight);

        Wheel frontLeft = new Wheel(this, "fl", new Vec3d(1.0, 0.0, 1.8), wheelSize, wheelHealth);
        frontLeft.setAccelerationWheel(true);
        frontLeft.setTurnWheel(true);
        registration.accept(frontLeft);

        Wheel rearRight = new Wheel(this, "rr", new Vec3d(-1.0, 0.0, -0.8), wheelSize, wheelHealth);
        rearRight.setAccelerationWheel(true);
        registration.accept(rearRight);

        Wheel rearLeft = new Wheel(this, "rl", new Vec3d(1.0, 0.0, -0.8), wheelSize, wheelHealth);
        rearLeft.setAccelerationWheel(true);
        registration.accept(rearLeft);
    }

    @Override
    public Collection<Seat> getSeats() {
        return SEATS;
    }

    @Override
    public void doEngineParticles(Consumer<Vec3d> consumer) {
        consumer.accept(ENGINE_POSITION);
    }

    @Override
    public void doExhaustParticles(Consumer<Vec3d> consumer) {
        consumer.accept(EXHAUST_POSITION);
    }
}