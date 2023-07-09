package dev.toma.pubgmc.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public final class EntityProviderType<E extends EntityProvider> extends RegistryObject {

    private final EntityProviderSerializer<E> serializer;

    private EntityProviderType(ResourceLocation location, EntityProviderSerializer<E> serializer) {
        super(location);
        this.serializer = serializer;
    }

    public static <E extends EntityProvider> EntityProviderType<E> create(ResourceLocation location, EntityProviderSerializer<E> serializer) {
        return new EntityProviderType<>(
                Objects.requireNonNull(location), Objects.requireNonNull(serializer)
        );
    }

    public static <E extends EntityProvider> E parse(JsonObject object) throws JsonParseException {
        ResourceLocation type = new ResourceLocation(JsonUtils.getString(object, "type"));
        EntityProviderType<E> providerType = PubgmcRegistries.ENTITY_PROVIDERS.getUnsafeGenericValue(type);
        if (providerType == null) {
            throw new JsonSyntaxException("Unknown entity provider type: " + type);
        }
        return providerType.serializer.parse(object);
    }

    @SuppressWarnings("unchecked")
    public static <E extends EntityProvider> JsonObject serialize(E provider) {
        EntityProviderType<E> type = (EntityProviderType<E>) provider.getType();
        JsonObject object = new JsonObject();
        object.addProperty("type", type.getIdentifier().toString());
        type.serializer.serializeData(object, provider);
        return object;
    }
}
