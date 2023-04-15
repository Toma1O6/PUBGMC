package dev.toma.pubgmc.api.game.map;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Objects;

public final class GameMapPointType<P extends GameMapPoint> extends RegistryObject {

    private final GameMapPointSerializer<P> serializer;

    private GameMapPointType(ResourceLocation identifier, GameMapPointSerializer<P> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <P extends GameMapPoint> GameMapPointType<P> create(ResourceLocation identifier, GameMapPointSerializer<P> serializer) {
        return new GameMapPointType<>(identifier, Objects.requireNonNull(serializer));
    }

    @SuppressWarnings("unchecked")
    public static <P extends GameMapPoint> NBTTagCompound serializePointData(P point) {
        GameMapPointType<P> type = (GameMapPointType<P>) point.getType();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("type", type.getIdentifier().toString());
        nbt.setTag("position", NBTUtil.createPosTag(point.getPointPosition()));
        nbt.setTag("poi", type.serializer.serializePointData(point));
        return nbt;
    }

    @Nullable
    public static <P extends GameMapPoint> P deserializePointData(NBTTagCompound nbt) {
        ResourceLocation type = new ResourceLocation(nbt.getString("type"));
        BlockPos position = NBTUtil.getPosFromTag(nbt.getCompoundTag("position"));
        GameMapPointType<P> pointType = PubgmcRegistries.GAME_MAP_POINTS.getUnsafeGenericValue(type);
        if (pointType == null) {
            Pubgmc.logger.error("Unknown point type: " + type);
            return null;
        }
        return pointType.serializer.deserializePointData(position, nbt.getCompoundTag("poi"));
    }
}
