package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import net.minecraft.util.math.BlockPos;

public class PointOfInterestPoint extends GameMapPoint {

    public PointOfInterestPoint(BlockPos pointPosition) {
        super(pointPosition);
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.POINT_OF_INTEREST;
    }
}
