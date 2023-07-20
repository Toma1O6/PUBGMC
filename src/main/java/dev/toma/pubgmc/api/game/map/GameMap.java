package dev.toma.pubgmc.api.game.map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.*;
import java.util.regex.Pattern;

public final class GameMap implements Bounds2 {

    public static final Pattern ALLOWED_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_]*");
    private final String mapName;
    private final Position2 min;
    private final Position2 max;
    private final Map<BlockPos, GameMapPoint> pointsByPosition;
    private final Multimap<GameMapPointType<?>, GameMapPoint> pointsByType;

    public GameMap(String mapName, Position2 min, Position2 max) {
        this.mapName = mapName;
        this.min = min;
        this.max = max;
        this.pointsByPosition = new HashMap<>();
        this.pointsByType = ArrayListMultimap.create();
    }

    @Nullable
    public GameMap getOverlappingMap(Collection<GameMap> maps) {
        for (GameMap map : maps) {
            if (map.isWithin(min.getX(), min.getZ()) || map.isWithin(max.getX(), max.getZ())) {
                return map;
            }
        }
        return null;
    }

    public Collection<GameMapPoint> getPoints() {
        return pointsByPosition.values();
    }

    public Optional<GameMapPoint> getPointAt(BlockPos pos) {
        return Optional.ofNullable(pointsByPosition.get(pos));
    }

    public void deletePoiAt(BlockPos pos) {
        pointsByPosition.remove(pos);
        remapPointsByType();
    }

    @SuppressWarnings("unchecked")
    public <P extends GameMapPoint> Collection<P> getPoints(GameMapPointType<P> pointType) {
        return (Collection<P>) pointsByType.get(pointType);
    }

    public void setMapPoint(BlockPos pos, GameMapPoint point) {
        boolean existsAt = pointsByPosition.containsKey(pos);
        if (existsAt || point == null) {
            pointsByPosition.remove(pos);
        } else {
            pointsByPosition.put(pos, point);
        }
        remapPointsByType();
    }

    public void deletePoints() {
        pointsByPosition.clear();
        pointsByType.clear();
    }

    public String getMapName() {
        return mapName;
    }

    public StaticPlayzone bounds() {
        return new StaticPlayzone(AbstractDamagingPlayzone.DamageOptions.NONE, min, max);
    }

    @Override
    public boolean isWithin(double x, double z) {
        return x > min.getX() && x < max.getX() && z > min.getZ() && z < max.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMap gameMap = (GameMap) o;
        return mapName.equals(gameMap.mapName);
    }

    @Override
    public int hashCode() {
        return mapName.hashCode();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", mapName);
        nbt.setTag("min", min.toNbt());
        nbt.setTag("max", max.toNbt());
        nbt.setTag("pois", SerializationHelper.collectionToNbt(pointsByPosition.values(), GameMapPointType::serializePointData));
        return nbt;
    }

    public static GameMap deserialize(NBTTagCompound nbt) {
        String name = nbt.getString("name");
        Position2 min = new Position2(nbt.getCompoundTag("min"));
        Position2 max = new Position2(nbt.getCompoundTag("max"));
        GameMap map = new GameMap(name, min, max);
        List<GameMapPoint> points = new ArrayList<>();
        SerializationHelper.collectionFromNbt(points, nbt.getTagList("pois", Constants.NBT.TAG_COMPOUND), base -> GameMapPointType.deserializePointData((NBTTagCompound) base));
        points.forEach(point -> map.pointsByPosition.put(point.getPointPosition(), point));
        map.remapPointsByType();
        return map;
    }

    private void remapPointsByType() {
        pointsByType.clear();
        pointsByPosition.forEach((pos, point) -> {
            GameMapPointType<?> pointType = point.getType();
            pointsByType.put(pointType, point);
        });
    }
}
