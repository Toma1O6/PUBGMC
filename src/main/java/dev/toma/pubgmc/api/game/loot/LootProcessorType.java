package dev.toma.pubgmc.api.game.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.util.RegistryObject;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class LootProcessorType<P extends LootProcessor> extends RegistryObject {

    private final LootProcessorSerializer<P> serializer;

    private LootProcessorType(ResourceLocation identifier, LootProcessorSerializer<P> serializer) {
        super(identifier);
        this.serializer = serializer;
    }

    public static <P extends LootProcessor> LootProcessorType<P> create(ResourceLocation identifier, LootProcessorSerializer<P> serializer) {
        return new LootProcessorType<>(identifier, serializer);
    }

    public static <P extends LootProcessor> P parse(JsonObject object) throws JsonParseException {
        ResourceLocation type = new ResourceLocation(JsonUtils.getString(object, "type"));
        LootProcessorType<P> processorType = PubgmcRegistries.LOOT_PROCESSORS.getUnsafeGenericValue(type);
        if (processorType == null) {
            throw new JsonSyntaxException("Unknown loot provider type: " + type);
        }
        return processorType.serializer.parse(object);
    }

    @SuppressWarnings("unchecked")
    public static <P extends LootProcessor> JsonObject serialize(P processor) {
        LootProcessorType<P> type = (LootProcessorType<P>) processor.getType();
        JsonObject object = new JsonObject();
        object.addProperty("type", type.getIdentifier().toString());
        type.serializer.serialize(object, processor);
        return object;
    }

    public static List<LootProcessor> parseMany(JsonArray array) throws JsonParseException {
        List<LootProcessor> list = new ArrayList<>();
        array.forEach(element -> {
            JsonObject object = SerializationHelper.asObject(element);
            list.add(parse(object));
        });
        return list;
    }

    public static JsonArray serializeMany(List<LootProcessor> list) {
        JsonArray array = new JsonArray();
        list.forEach(lootProcessor -> array.add(serialize(lootProcessor)));
        return array;
    }
}
