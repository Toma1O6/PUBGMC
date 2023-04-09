package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.Pubgmc;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class LootProviderType<P extends LootProvider> {

    private final ResourceLocation location;
    private final LootProviderSerializer<P> serializer;

    private LootProviderType(ResourceLocation location, LootProviderSerializer<P> serializer) {
        this.location = location;
        this.serializer = serializer;
    }

    public static <P extends LootProvider> LootProviderType<P> create(ResourceLocation resourceLocation, LootProviderSerializer<P> serializer) {
        return new LootProviderType<>(resourceLocation, serializer);
    }

    public ResourceLocation getProviderId() {
        return location;
    }

    public static <P extends LootProvider> P parse(JsonObject object) throws JsonParseException {
        ResourceLocation type = new ResourceLocation(JsonUtils.getString(object, "type"));
        LootProviderType<P> providerType = Pubgmc.LOOT_PROVIDER_TYPE_REGISTRY.getGenericValue(type);
        if (providerType == null) {
            throw new JsonSyntaxException("Unknown loot provider type: " + type);
        }
        return providerType.serializer.parse(object);
    }

    @SuppressWarnings("unchecked")
    public static <P extends LootProvider> JsonObject serialize(P provider) {
        LootProviderType<P> type = (LootProviderType<P>) provider.getType();
        JsonObject object = new JsonObject();
        object.addProperty("type", type.location.toString());
        type.serializer.serializeData(object, provider);
        return object;
    }
}