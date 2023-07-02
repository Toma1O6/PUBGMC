package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public final class LootConfiguration {

    private final Map<String, LootProvider> groups;
    private final LootProvider pool;

    public LootConfiguration(Map<String, LootProvider> groups, LootProvider pool) {
        this.groups = groups;
        this.pool = pool;
    }

    public Map<String, LootProvider> getGroups() {
        return groups;
    }

    public LootProvider getPool() {
        return pool;
    }

    public JsonElement serialize() {
        JsonObject object = new JsonObject();
        JsonObject map = new JsonObject();
        for (Map.Entry<String, LootProvider> entry : groups.entrySet()) {
            map.add(entry.getKey(), LootProviderType.serialize(entry.getValue()));
        }
        object.add("itemGroupDeclarations", map);
        JsonObject poolObj = LootProviderType.serialize(pool);
        object.add("pool", poolObj);
        return object;
    }

    public static LootConfiguration parse(JsonElement element) throws JsonParseException {
        JsonObject object = SerializationHelper.asObject(element);
        JsonObject groups = JsonUtils.getJsonObject(object, "itemGroupDeclarations", new JsonObject());
        JsonObject pool = JsonUtils.getJsonObject(object, "pool");
        Map<String, LootProvider> map = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : groups.entrySet()) {
            JsonObject groupPool = SerializationHelper.asObject(entry.getValue());
            LootProvider groupPoolProvider = LootProviderType.parse(groupPool);
            map.put(entry.getKey(), groupPoolProvider);
        }
        return new LootConfiguration(map, LootProviderType.parse(pool));
    }
}
