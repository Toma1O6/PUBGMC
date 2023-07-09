package dev.toma.pubgmc.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public final class EntityProcessorType<E extends EntityProcessor> extends RegistryObject {

    private final EntityProcessorSerializer<E> serializer;

    private EntityProcessorType(ResourceLocation identifier, EntityProcessorSerializer<E> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <E extends EntityProcessor> EntityProcessorType<E> create(ResourceLocation location, EntityProcessorSerializer<E> serializer) {
        return new EntityProcessorType<>(
                Objects.requireNonNull(location), Objects.requireNonNull(serializer)
        );
    }

    public static <E extends EntityProcessor> E parse(JsonObject object) throws JsonParseException {
        ResourceLocation type = new ResourceLocation(JsonUtils.getString(object, "type"));
        EntityProcessorType<E> providerType = PubgmcRegistries.ENTITY_PROCESSORS.getUnsafeGenericValue(type);
        if (providerType == null) {
            throw new JsonSyntaxException("Unknown entity processor type: " + type);
        }
        return providerType.serializer.parse(object);
    }

    @SuppressWarnings("unchecked")
    public static <E extends EntityProcessor> JsonObject serialize(E provider) {
        EntityProcessorType<E> type = (EntityProcessorType<E>) provider.getType();
        JsonObject object = new JsonObject();
        object.addProperty("type", type.getIdentifier().toString());
        type.serializer.serialize(object, provider);
        return object;
    }
}
