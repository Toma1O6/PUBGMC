package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import net.minecraft.util.math.BlockPos;

public class SpawnerPoint extends GameMapPoint {

    public SpawnerPoint(BlockPos pointPosition) {
        super(pointPosition);
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.SPAWNER;
    }
}
