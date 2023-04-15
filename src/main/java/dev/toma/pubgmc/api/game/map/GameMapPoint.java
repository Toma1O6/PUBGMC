package dev.toma.pubgmc.api.game.map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

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

    public static <P extends GameMapPoint> GameMapPointSerializer<P> createSimpleSerializer(Function<BlockPos, P> pointFactory) {
        return new SimpleSerializerImpl<>(pointFactory);
    }

    private static final class SimpleSerializerImpl<P extends GameMapPoint> implements GameMapPointSerializer<P> {

        private final Function<BlockPos, P> factory;

        private SimpleSerializerImpl(Function<BlockPos, P> factory) {
            this.factory = factory;
        }

        @Override
        public void serializePointData(P point, NBTTagCompound nbt) {
        }

        @Override
        public P deserializePointData(BlockPos pointPosition, NBTTagCompound nbt) {
            return factory.apply(pointPosition);
        }
    }
}
