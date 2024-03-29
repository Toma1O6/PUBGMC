package dev.toma.pubgmc.api.util;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public final class Position2 {

    public static final Position2 ORIGIN = new Position2(0.0, 0.0);
    private double x;
    private double z;

    public Position2(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public Position2(NBTTagCompound nbt) {
        this.x = nbt.getDouble("x");
        this.z = nbt.getDouble("z");
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double length(Position2 other) {
        double dx = x - other.x;
        double dz = z - other.z;
        return Math.sqrt(dx * dx + dz * dz);
    }

    public Position2 around(Random random, double distance) {
        double x = (random.nextDouble() - random.nextDouble()) * distance;
        double z = (random.nextDouble() - random.nextDouble()) * distance;
        return new Position2(this.x + x, this.z + z);
    }

    public NBTTagCompound toNbt() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setDouble("x", x);
        nbt.setDouble("z", z);
        return nbt;
    }

    @Override
    public String toString() {
        return x + ", " + z;
    }
}
