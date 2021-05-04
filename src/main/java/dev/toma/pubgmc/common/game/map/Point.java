package dev.toma.pubgmc.common.game.map;

import net.minecraft.util.math.BlockPos;

public class Point {

    protected final BlockPos pos;

    public Point(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return pos.equals(point.pos);
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }
}
