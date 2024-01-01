package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import net.minecraft.util.math.BlockPos;

public class SpectatorPoint extends GameMapPoint {

    public SpectatorPoint(BlockPos pointPosition) {
        super(pointPosition);
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.SPECTATOR_POINT;
    }
}
