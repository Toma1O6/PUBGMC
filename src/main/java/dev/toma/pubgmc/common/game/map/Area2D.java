package dev.toma.pubgmc.common.game.map;

import net.minecraft.util.math.BlockPos;

public class Area2D extends Point {

    private final int radius;

    public Area2D(BlockPos pos, int radius) {
        super(pos);
        this.radius = radius;
    }

    public boolean isInside(double x, double y, double z) {
        double minX = pos.getX() - radius - 0.5;
        double minZ = pos.getZ() - radius - 0.5;
        return x >= minX && x <= minX + radius && z >= minZ && z <= minZ + radius;
    }
}
