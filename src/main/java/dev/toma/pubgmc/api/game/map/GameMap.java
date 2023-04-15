package dev.toma.pubgmc.api.game.map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.toma.pubgmc.api.util.Bounds;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.area.AbstractDamagingArea;
import dev.toma.pubgmc.common.games.area.StaticGameArea;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class GameMap implements Bounds {

    public static final Pattern ALLOWED_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_]+");
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

    public void setMapPoint(BlockPos pos, GameMapPoint point) {
        boolean existsAt = pointsByPosition.containsKey(pos);
        if (existsAt || point == null) {
            pointsByPosition.remove(pos);
        } else {
            pointsByPosition.put(pos, point);
        }
        remapPointsByType();
    }

    public String getMapName() {
        return mapName;
    }

    public StaticGameArea bounds() {
        return new StaticGameArea(AbstractDamagingArea.DamageOptions.NONE, min, max);
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
        // TODO pois
        return nbt;
    }

    public static GameMap deserialize(NBTTagCompound nbt) {
        String name = nbt.getString("name");
        Position2 min = new Position2(nbt.getCompoundTag("min"));
        Position2 max = new Position2(nbt.getCompoundTag("max"));
        // TODO pois
        return new GameMap(name, min, max);
    }

    private void remapPointsByType() {
        pointsByType.clear();
        pointsByPosition.forEach((pos, point) -> {
            GameMapPointType<?> pointType = point.getType();
            pointsByType.put(pointType, point);
        });
    }
}
