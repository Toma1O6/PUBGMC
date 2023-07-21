package dev.toma.pubgmc.api.game.playzone;

import dev.toma.pubgmc.api.game.map.Bounds2;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.util.math.Mth;
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
        return Mth.findNearestPositionWithin(min.getX(), min.getZ(), max.getX(), max.getZ(), origin, world, 3);
    }
}
