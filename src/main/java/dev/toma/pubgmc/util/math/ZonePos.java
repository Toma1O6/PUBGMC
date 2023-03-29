package dev.toma.pubgmc.util.math;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class ZonePos {

    public double x;
    public double z;

    public ZonePos(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public static NBTTagCompound toNBT(ZonePos pos) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("x", pos.x);
        tag.setDouble("z", pos.z);
        return tag;
    }

    public static ZonePos fromNBT(NBTTagCompound nbt) {
        double x = nbt.getDouble("x");
        double z = nbt.getDouble("z");
        return new ZonePos(x, z);
    }

    public ZonePos add(double x, double z) {
        this.x += x;
        this.z += z;
        return this;
    }

    public ZonePos add(double f) {
        return add(f, f);
    }

    public ZonePos subtract(double x, double z) {
        return add(-x, -z);
    }

    public ZonePos subtract(double f) {
        return subtract(f, f);
    }

    public ZonePos multiply(double x, double z) {
        this.x *= x;
        this.z *= z;
        return this;
    }

    public ZonePos multiply(double f) {
        return multiply(f, f);
    }

    public ZonePos divide(double x, double z) {
        return multiply(1 / x, 1 / z);
    }

    public ZonePos divide(double f) {
        return multiply(1 / f);
    }

    public double distanceX(ZonePos pos) {
        return this.x - pos.x;
    }

    public double distanceZ(ZonePos pos) {
        return this.z - pos.z;
    }

    public double distance(ZonePos pos) {
        double x = this.distanceX(pos);
        double z = this.distanceZ(pos);
        return MathHelper.sqrt(x * x + z * z);
    }

    @Override
    public String toString() {
        return "Zone:[x=" + x + ",z=" + z + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ZonePos) {
            ZonePos pos = (ZonePos) obj;
            return pos.x == this.x && pos.z == this.z;
        }
        return false;
    }
}
