package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointSerializer;
import dev.toma.pubgmc.api.game.map.GameMapPointType;

public final class GameMapPoints {

    public static final GameMapPointType<SpawnerPoint> SPAWNER = create("spawner", GameMapPoint.createSimpleSerializer(SpawnerPoint::new));
    public static final GameMapPointType<PointOfInterestPoint> POINT_OF_INTEREST = create("point_of_interest", GameMapPoint.createSimpleSerializer(PointOfInterestPoint::new));

    private static <P extends GameMapPoint> GameMapPointType<P> create(String id, GameMapPointSerializer<P> serializer) {
        return GameMapPointType.create(Pubgmc.getResource(id), serializer);
    }
}
