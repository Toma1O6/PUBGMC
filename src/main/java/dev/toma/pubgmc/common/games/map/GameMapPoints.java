package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointSerializer;
import dev.toma.pubgmc.api.game.map.GameMapPointType;

public final class GameMapPoints {

    public static final GameMapPointType<SpawnerPoint> SPAWNER = create("spawner", GameMapPoint.createSimpleSerializer(SpawnerPoint::new));
    public static final GameMapPointType<PointOfInterestPoint> POINT_OF_INTEREST = create("point_of_interest", GameMapPoint.createSimpleSerializer(PointOfInterestPoint::new));
    public static final GameMapPointType<CaptureZonePoint> CAPTURE_ZONE = create("capture_zone", new CaptureZonePoint.Serializer());
    public static final GameMapPointType<TeamSpawnerPoint> TEAM_SPAWNER = create("team_spawner", new TeamSpawnerPoint.Serializer());

    private static <P extends GameMapPoint> GameMapPointType<P> create(String id, GameMapPointSerializer<P> serializer) {
        return GameMapPointType.create(Pubgmc.getResource(id), serializer);
    }
}
