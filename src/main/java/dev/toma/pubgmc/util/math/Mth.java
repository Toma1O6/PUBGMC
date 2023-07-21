package dev.toma.pubgmc.util.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class Mth {

    public static BlockPos findNearestPositionWithin(double minX, double minZ, double maxX, double maxZ, Vec3d origin, World world, int offset) {
        boolean isWithinX = origin.x >= minX && origin.x <= maxX;
        boolean isWithinZ = origin.z >= minZ && origin.z <= maxZ;
        double xDistMin = Math.abs(origin.x - minX) + offset;
        double xDistMax = Math.abs(origin.x - maxX) + offset;
        double zDistMin = Math.abs(origin.z - minZ) + offset;
        double zDistMax = Math.abs(origin.z - maxZ) + offset;
        double moveX = isWithinX ? 0.0 : xDistMin > xDistMax ? -xDistMax : xDistMin;
        double moveZ = isWithinZ ? 0.0 : zDistMin > zDistMax ? -zDistMax : zDistMin;
        double x = origin.x + moveX;
        double z = origin.z + moveZ;
        return new BlockPos(x, world.getHeight((int) x, (int) z), z);
    }
}
