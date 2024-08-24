package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.map.Area;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public abstract class AbstractMapBoundTask extends EntityAIBase {

    private Supplier<Area> areaProvider;

    public void setAreaProvider(Supplier<Area> areaProvider) {
        this.areaProvider = areaProvider;
    }

    public final boolean canMoveTo(BlockPos pos) {
        return this.canMoveTo(pos.getX(), pos.getZ());
    }

    public final boolean canMoveTo(double x, double z) {
        Area area = this.areaProvider != null ? this.areaProvider.get() : null;
        if (area == null)
            return false;
        return area.isWithin(x, z);
    }
}
