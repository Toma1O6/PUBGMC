package dev.toma.pubgmc.api.game.playzone;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;

public final class PlayzoneType<P extends Playzone> extends RegistryObject {

    private final PlayzoneSerializer<P> serializer;

    private PlayzoneType(ResourceLocation identifier, PlayzoneSerializer<P> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <P extends Playzone> PlayzoneType<P> create(ResourceLocation playzoneTypeId, PlayzoneSerializer<P> serializer) {
        return new PlayzoneType<>(Objects.requireNonNull(playzoneTypeId), Objects.requireNonNull(serializer));
    }

    @SuppressWarnings("unchecked")
    public static <P extends Playzone> NBTTagCompound serialize(P playzone) {
        NBTTagCompound nbt = new NBTTagCompound();
        PlayzoneType<P> playzoneType = (PlayzoneType<P>) playzone.getPlayzoneType();
        nbt.setString("playzoneType", playzoneType.getIdentifier().toString());
        PlayzoneSerializer<P> serializer = playzoneType.serializer;
        nbt.setTag("playzone", serializer.serializePlayzone(playzone));
        return nbt;
    }

    @Nullable
    public static <P extends Playzone> P deserialize(NBTTagCompound nbt) {
        ResourceLocation playzoneId = new ResourceLocation(nbt.getString("playzoneType"));
        PlayzoneType<P> playzoneType = PubgmcRegistries.PLAYZONE_TYPES.getUnsafeGenericValue(playzoneId);
        if (playzoneType == null) {
            return null;
        }
        PlayzoneSerializer<P> serializer = playzoneType.serializer;
        NBTTagCompound playzoneData = nbt.getCompoundTag("playzone");
        return serializer.deserializePlayzone(playzoneData);
    }
}
