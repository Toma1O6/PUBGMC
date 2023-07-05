package dev.toma.pubgmc.api.game.map;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public interface Bounds {

    boolean isWithin(double x, double z);

    default boolean isWithin(Entity entity) {
        return isWithin(entity.posX, entity.posZ);
    }

    default boolean isWithin(BlockPos pos) {
        return isWithin(pos.getX(), pos.getZ());
    }
}
