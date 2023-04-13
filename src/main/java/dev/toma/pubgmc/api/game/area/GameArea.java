package dev.toma.pubgmc.api.game.area;

import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface GameArea {

    GameAreaType<?> getAreaType();

    void tickGameArea(World world);

    Position2 getPositionMin(float partialTicks);

    Position2 getPositionMax(float partialTicks);

    boolean isVisible();

    void setVisible(boolean visible);

    default boolean isWithin(double x, double z) {
        Position2 min = getPositionMin(1.0F);
        Position2 max = getPositionMax(1.0F);
        return x > min.getX() && x < max.getX() && z > min.getZ() && z < max.getZ();
    }

    default boolean isWithin(Entity entity) {
        return isWithin(entity.posX, entity.posZ);
    }

    default boolean isWithin(BlockPos pos) {
        return isWithin(pos.getX(), pos.getZ());
    }
}
