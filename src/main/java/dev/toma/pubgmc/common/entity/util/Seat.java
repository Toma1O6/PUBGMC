package dev.toma.pubgmc.common.entity.util;

import net.minecraft.util.math.Vec3d;

public final class Seat {

    private final Vec3d position;
    private final boolean driver;

    private Seat(Vec3d position, boolean driver) {
        this.position = position;
        this.driver = driver;
    }

    public static Seat createDriver(double x, double y, double z) {
        return new Seat(new Vec3d(x, y, z), true);
    }

    public static Seat createPassenger(double x, double y, double z) {
        return new Seat(new Vec3d(x, y, z), false);
    }

    public Vec3d getPosition() {
        return position;
    }

    public boolean isDriver() {
        return driver;
    }
}
