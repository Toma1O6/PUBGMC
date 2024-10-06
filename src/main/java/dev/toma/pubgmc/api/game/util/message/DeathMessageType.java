package dev.toma.pubgmc.api.game.util.message;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public final class DeathMessageType<M extends DeathMessage> extends RegistryObject {

    private final MessageSerializer<M> serializer;

    DeathMessageType(String path, MessageSerializer<M> serializer) {
        this(Pubgmc.getResource(path), serializer);
    }

    public DeathMessageType(ResourceLocation identifier, MessageSerializer<M> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    @SuppressWarnings("unchecked")
    public static <M extends DeathMessage> NBTTagCompound serialize(M message) {
        DeathMessageType<M> type = (DeathMessageType<M>) message.getType();
        MessageSerializer<M> serializer = type.serializer;
        NBTTagCompound root = new NBTTagCompound();
        root.setString("id", type.getIdentifier().toString());
        root.setTag("data", serializer.serialize(message));
        return root;
    }

    @SuppressWarnings("unchecked")
    public static <M extends DeathMessage> M deserialize(NBTTagCompound root) {
        ResourceLocation identifier = new ResourceLocation(root.getString("id"));
        DeathMessageType<M> type = PubgmcRegistries.DEATH_MESSAGES.getUnsafeGenericValue(identifier);
        if (type == null)
            return (M) EmptyDeathMessage.EMPTY;
        NBTTagCompound data = root.getCompoundTag("data");
        return type.serializer.deserialize(data);
    }

    public interface MessageSerializer<M extends DeathMessage> {

        NBTTagCompound serialize(M message);

        M deserialize(NBTTagCompound message);
    }
}
