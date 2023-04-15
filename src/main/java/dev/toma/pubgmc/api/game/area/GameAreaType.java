package dev.toma.pubgmc.api.game.area;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;

public final class GameAreaType<A extends GameArea> extends RegistryObject {

    private final GameAreaSerializer<A> serializer;

    private GameAreaType(ResourceLocation identifier, GameAreaSerializer<A> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <A extends GameArea> GameAreaType<A> create(ResourceLocation gameAreaTypeId, GameAreaSerializer<A> serializer) {
        return new GameAreaType<>(Objects.requireNonNull(gameAreaTypeId), Objects.requireNonNull(serializer));
    }

    @SuppressWarnings("unchecked")
    public static <A extends GameArea> NBTTagCompound serialize(A area) {
        NBTTagCompound nbt = new NBTTagCompound();
        GameAreaType<A> areaType = (GameAreaType<A>) area.getAreaType();
        nbt.setString("areaType", areaType.getIdentifier().toString());
        GameAreaSerializer<A> serializer = areaType.serializer;
        nbt.setTag("area", serializer.serializeArea(area));
        return nbt;
    }

    @Nullable
    public static <A extends GameArea> A deserialize(NBTTagCompound nbt) {
        ResourceLocation areaId = new ResourceLocation(nbt.getString("areaType"));
        GameAreaType<A> areaType = PubgmcRegistries.GAME_AREA_TYPES.getUnsafeGenericValue(areaId);
        if (areaType == null) {
            return null;
        }
        GameAreaSerializer<A> serializer = areaType.serializer;
        NBTTagCompound areaData = nbt.getCompoundTag("area");
        return serializer.deserializeArea(areaData);
    }
}