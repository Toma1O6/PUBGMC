package dev.toma.pubgmc.api.game.map;

import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import net.minecraft.util.math.BlockPos;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface GameMap extends Area {

    Collection<GameMapPoint> getPoints();

    Optional<GameMapPoint> getPointAt(BlockPos pos);

    void deletePoiAt(BlockPos pos);

    <P extends GameMapPoint> Collection<P> getPoints(GameMapPointType<P> pointType);

    default <P extends GameMapPoint> Stream<P> getPoints(GameMapPointType<P> pointType, Predicate<P> filter) {
        return getPoints(pointType).stream()
                .filter(filter);
    }

    void setMapPoint(BlockPos pos, GameMapPoint point);

    void deletePoints();

    String getMapName();

    StaticPlayzone bounds();

    default boolean isSubMap() {
        return false;
    }
}
