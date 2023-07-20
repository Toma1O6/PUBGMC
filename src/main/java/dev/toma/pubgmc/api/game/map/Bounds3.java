package dev.toma.pubgmc.api.game.map;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public interface Bounds3 extends Bounds2 {

    boolean isWithin(double x, double y, double z);

    default boolean isWithin(double x, double z) {
        return isWithin(x, 0, z);
    }

    @Override
    default boolean isWithin(Entity entity) {
        return isWithin(entity.posX, entity.posY, entity.posZ);
    }

    @Override
    default boolean isWithin(BlockPos pos) {
        return isWithin(pos.getX(), pos.getY(), pos.getZ());
    }
}
