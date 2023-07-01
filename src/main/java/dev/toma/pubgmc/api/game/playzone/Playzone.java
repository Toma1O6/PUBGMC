package dev.toma.pubgmc.api.game.playzone;

import dev.toma.pubgmc.api.util.Bounds;
import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.world.World;

public interface Playzone extends Bounds {

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
}
