package dev.toma.pubgmc.api.game.playzone;

import dev.toma.pubgmc.api.game.map.Bounds2;
import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface Playzone extends Bounds2 {

    PlayzoneType<?> getPlayzoneType();

    void tickPlayzone(World world);

    Position2 getPositionMin(float partialTicks);

    Position2 getPositionMax(float partialTicks);

    boolean isVisible();

    void setVisible(boolean visible);

    @Override
    default boolean isWithin(double x, double z) {
        Position2 min = getPositionMin(1.0F);
        Position2 max = getPositionMax(1.0F);
        return x > min.getX() && x < max.getX() && z > min.getZ() && z < max.getZ();
    }

    default double length() {
        Position2 min = getPositionMin(1.0F);
        Position2 max = getPositionMax(1.0F);
        double x = max.getX() - min.getX();
        double z = max.getZ() - min.getZ();
        return x * x + z * z;
    }

    default Position2 center() {
        Position2 min = getPositionMin(1.0F);
        Position2 max = getPositionMax(1.0F);
        double x = Math.abs(max.getX() - min.getX());
        double z = Math.abs(max.getZ() - min.getZ());
        return new Position2(min.getX() + x / 2.0, min.getZ() + z / 2.0);
    }

    default BlockPos findNearestPositionWithin(Vec3d origin, World world) {
        Position2 min = getPositionMin(1.0F);
        Position2 max = getPositionMax(1.0F);
        double minX = min.getX();
        double maxX = max.getX();
        double minZ = min.getZ();
        double maxZ = max.getZ();
        boolean isWithinX = origin.x >= minX && origin.x <= maxX;
        boolean isWithinZ = origin.z >= minZ && origin.z <= maxZ;
        double xDistMin = Math.abs(origin.x - minX) + 3;
        double xDistMax = Math.abs(origin.x - maxX) + 3;
        double zDistMin = Math.abs(origin.z - minZ) + 3;
        double zDistMax = Math.abs(origin.z - maxZ) + 3;
        double moveX = isWithinX ? 0.0 : xDistMin > xDistMax ? -xDistMax : xDistMin;
        double moveZ = isWithinZ ? 0.0 : zDistMin > zDistMax ? -zDistMax : zDistMin;
        double x = origin.x + moveX;
        double z = origin.z + moveZ;
        return new BlockPos(x, world.getHeight((int) x, (int) z), z);
    }
}
