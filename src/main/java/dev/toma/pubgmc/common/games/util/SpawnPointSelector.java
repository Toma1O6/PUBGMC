package dev.toma.pubgmc.common.games.util;

import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SpawnPointSelector<P extends GameMapPoint> {

    private static final int POINT_NOT_LOADED_WEIGHT = -1;
    private static final int ENTITY_DISTANCE_UNIT_WEIGHT = 10;

    private final GameMapPointType<P> mapPointType;
    private final Predicate<P> selector;
    private final Function<World, GameMap> mapProvider;

    private boolean allowUnloaded;

    public SpawnPointSelector(GameMapPointType<P> mapPointType, Predicate<P> selector, Function<World, GameMap> mapProvider) {
        this.mapPointType = mapPointType;
        this.selector = selector;
        this.mapProvider = mapProvider;
    }

    public SpawnPointSelector(GameMapPointType<P> mapPointType, Function<World, GameMap> mapProvider) {
        this(mapPointType, t -> true, mapProvider);
    }

    public void allowUnloadedSpawnPoints(boolean allowUnloaded) {
        this.allowUnloaded = allowUnloaded;
    }

    public P getPoint(World world, Collection<? extends Entity> enemyEntities) {
        GameMap map = mapProvider.apply(world);
        Predicate<P> filter = allowUnloaded ? selector : p -> selector.test(p) && world.isBlockLoaded(p.getPointPosition());
        Collection<P> points = map.getPoints(mapPointType).stream().filter(filter).collect(Collectors.toList());
        if (points.isEmpty()) {
            throw new UnsupportedOperationException("Map has to contain atleast one point");
        }
        List<PointData<P>> data = buildPointsMap(world, enemyEntities, points);
        data.sort(Comparator.comparingInt(PointData::getValue));
        return data.get(0).getPoint();
    }

    private List<PointData<P>> buildPointsMap(World world, Collection<? extends Entity> enemyEntities, Collection<P> points) {
        List<PointData<P>> pointData = new ArrayList<>();
        for (P point : points) {
            BlockPos pos = point.getPointPosition();
            if (!world.isBlockLoaded(pos)) {
                pointData.add(new PointData<>(POINT_NOT_LOADED_WEIGHT, point));
                break;
            }
            int weight = Integer.MAX_VALUE;
            double closestEnemyDistance = getClosest(pos, enemyEntities);
            int distValue = Math.min(weight, (int) closestEnemyDistance * ENTITY_DISTANCE_UNIT_WEIGHT);
            pointData.add(new PointData<>(weight - distValue, point));
        }
        return pointData;
    }

    private double getClosest(BlockPos pos, Collection<? extends Entity> entities) {
        double closest = Integer.MAX_VALUE;
        for (Entity entity : entities) {
            double dist = entity.getDistanceSq(pos);
            if (dist < closest) {
                closest = dist;
            }
        }
        return closest;
    }

    private static final class PointData<P> {

        private final int value;
        private final P point;

        public PointData(int value, P point) {
            this.value = value;
            this.point = point;
        }

        public int getValue() {
            return value;
        }

        public P getPoint() {
            return point;
        }
    }
}
