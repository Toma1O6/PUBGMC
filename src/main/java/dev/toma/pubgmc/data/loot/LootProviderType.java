package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class LootProviderType<P extends LootProvider> extends RegistryObject {

    private final LootProviderSerializer<P> serializer;

    private LootProviderType(ResourceLocation location, LootProviderSerializer<P> serializer) {
        super(location);
        this.serializer = serializer;
    }

    public static <P extends LootProvider> LootProviderType<P> create(ResourceLocation resourceLocation, LootProviderSerializer<P> serializer) {
        return new LootProviderType<>(resourceLocation, serializer);
    }

    public static <P extends LootProvider> P parse(JsonObject object) throws JsonParseException {
        ResourceLocation type = new ResourceLocation(JsonUtils.getString(object, "type"));
        LootProviderType<P> providerType = PubgmcRegistries.LOOT_PROVIDERS.getUnsafeGenericValue(type);
        if (providerType == null) {
            throw new JsonSyntaxException("Unknown loot provider type: " + type);
        }
        return providerType.serializer.parse(object);
    }

    @SuppressWarnings("unchecked")
    public static <P extends LootProvider> JsonObject serialize(P provider) {
        LootProviderType<P> type = (LootProviderType<P>) provider.getType();
        JsonObject object = new JsonObject();
        object.addProperty("type", type.getIdentifier().toString());
        type.serializer.serializeData(object, provider);
        return object;
    }
}
