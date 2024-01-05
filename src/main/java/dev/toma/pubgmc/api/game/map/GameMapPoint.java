package dev.toma.pubgmc.api.game.map;

import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.function.Function;

public abstract class GameMapPoint {

    private final BlockPos pointPosition;

    public GameMapPoint(BlockPos pointPosition) {
        this.pointPosition = Objects.requireNonNull(pointPosition);
    }

    public abstract GameMapPointType<?> getType();

    public final BlockPos getPointPosition() {
        return pointPosition;
    }

    public final boolean is(GameMapPointType<?> type) {
        return this.getType() == type;
    }

    public final void teleportOn(Entity entity) {
        GameHelper.teleport(entity, pointPosition.getX() + 0.5, pointPosition.getY() + 1.0, pointPosition.getZ() + 0.5);
    }

    public static <P extends GameMapPoint> GameMapPointSerializer<P> createSimpleSerializer(Function<BlockPos, P> pointFactory) {
        return new SimpleSerializerImpl<>(pointFactory);
    }

    @Override
    public String toString() {
        return "GameMapPoint{" +
                "pointPosition=" + pointPosition +
                '}';
    }

    private static final class SimpleSerializerImpl<P extends GameMapPoint> implements GameMapPointSerializer<P> {

        private final Function<BlockPos, P> factory;

        private SimpleSerializerImpl(Function<BlockPos, P> factory) {
            this.factory = factory;
        }

        @Override
        public NBTTagCompound serializePointData(P point) {
            return new NBTTagCompound();
        }

        @Override
        public P deserializePointData(BlockPos pointPosition, NBTTagCompound nbt, GameMapInstance parent) {
            return factory.apply(pointPosition);
        }

        @Override
        public P createDefaultInstance(BlockPos pos, World world, GameMapInstance map) {
            return factory.apply(pos);
        }
    }
}
