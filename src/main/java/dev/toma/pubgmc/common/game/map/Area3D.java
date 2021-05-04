package dev.toma.pubgmc.common.game.map;

import net.minecraft.util.math.BlockPos;

public class Area3D extends Area2D {

    private int height;

    public Area3D(BlockPos center, int horizontalRadius, int height) {
        super(center, horizontalRadius);
        this.height = height;
    }

    @Override
    public boolean isInside(double x, double y, double z) {
        return super.isInside(x, y, z) && y >= center.getY() - 0.5 && y <= center.getY() + height;
    }
}
