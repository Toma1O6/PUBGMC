package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.*;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartialPlayAreaPoint extends GameMapPoint implements GameMap {

    private final GameMapInstance parentMap;
    private Position2 min;
    private Position2 max;
    private String name;

    public PartialPlayAreaPoint(BlockPos pos, GameMapInstance parent) {
        super(pos);
        this.parentMap = parent;
        this.min = new Position2(pos.getX(), pos.getZ());
        this.max = new Position2(pos.getX() + 1.0, pos.getZ() + 1.0);
        this.name = parent.getMapName() + "-partial";
    }

    @Override
    public <P extends GameMapPoint> Collection<P> getPoints(GameMapPointType<P> pointType) {
        return parentMap.getPoints(pointType).stream()
                .filter(point -> isWithin(point.getPointPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<GameMapPoint> getPoints() {
        return parentMap.getPoints().stream()
                .filter(point -> isWithin(point.getPointPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GameMapPoint> getPointAt(BlockPos pos) {
        return parentMap.getPointAt(pos).filter(poi -> isWithin(poi.getPointPosition()));
    }

    @Override
    public void deletePoiAt(BlockPos pos) {
    }

    @Override
    public void deletePoints() {
    }

    @Override
    public void setMapPoint(BlockPos pos, GameMapPoint point) {
    }

    @Override
    public String getMapName() {
        return name;
    }

    @Override
    public StaticPlayzone bounds() {
        return new StaticPlayzone(AbstractDamagingPlayzone.DamageOptions.NONE, min, max);
    }

    @Override
    public boolean isWithin(double x, double z) {
        return x >= min.getX() && x <= max.getX() && z >= min.getZ() && z <= max.getZ();
    }

    @Override
    public Position2 getPositionMin(float partialTicks) {
        return min;
    }

    @Override
    public Position2 getPositionMax(float partialTicks) {
        return max;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMin(Position2 min) {
        this.min = min;
    }

    public void setMax(Position2 max) {
        this.max = max;
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.PARTIAL_PLAY_AREA;
    }

    @Override
    public boolean isSubMap() {
        return true;
    }

    public static final class Serializer implements GameMapPointSerializer<PartialPlayAreaPoint> {

        @Override
        public NBTTagCompound serializePointData(PartialPlayAreaPoint point) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("parentMap", point.parentMap.getMapName());
            nbt.setTag("min", point.min.toNbt());
            nbt.setTag("max", point.max.toNbt());
            nbt.setString("name", point.name);
            return nbt;
        }

        @Override
        public PartialPlayAreaPoint deserializePointData(BlockPos pointPosition, NBTTagCompound nbt, GameMapInstance parent) {
            Position2 min = new Position2(nbt.getCompoundTag("min"));
            Position2 max = new Position2(nbt.getCompoundTag("max"));
            String name = nbt.getString("name");
            PartialPlayAreaPoint point = new PartialPlayAreaPoint(pointPosition, parent);
            point.min = min;
            point.max = max;
            point.name = name;
            return point;
        }

        @Override
        public PartialPlayAreaPoint createDefaultInstance(BlockPos pos, World world, GameMapInstance map) {
            return new PartialPlayAreaPoint(pos, map);
        }
    }
}
