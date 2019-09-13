package com.toma.pubgmc.util.math;

import net.minecraft.nbt.NBTTagCompound;

public class ZoneBounds {

    private ZonePos min;
    private ZonePos max;

    public ZoneBounds(ZonePos min, ZonePos max) {
        this.min = min;
        this.max = max;
    }

    public ZoneBounds(double x1, double z1, double x2, double z2) {
        this(new ZonePos(x1, z1), new ZonePos(x2, z2));
    }

    public void shrink(double x, double z, double xn, double zn) {
        min.add(x, z);
        max.subtract(xn, zn);
    }

    public ZonePos min() {
        return min;
    }

    public ZonePos max() {
        return max;
    }

    @Override
    public String toString() {
        return "ZoneBounds[min=" + min.toString() + ",max=" + max.toString() + "]";
    }

    public static NBTTagCompound toNBT(ZoneBounds zoneBounds) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("min", ZonePos.toNBT(zoneBounds.min));
        nbt.setTag("max", ZonePos.toNBT(zoneBounds.max));
        return nbt;
    }

    public static ZoneBounds fromNBT(NBTTagCompound nbt) {
        ZonePos min = ZonePos.fromNBT(nbt.getCompoundTag("min"));
        ZonePos max = ZonePos.fromNBT(nbt.getCompoundTag("max"));
        return new ZoneBounds(min, max);
    }
}
