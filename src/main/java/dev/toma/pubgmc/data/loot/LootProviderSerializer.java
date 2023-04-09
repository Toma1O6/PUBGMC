package dev.toma.pubgmc.data.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public interface LootProviderSerializer<P extends LootProvider> {

    P parse(JsonObject object) throws JsonParseException;

    void serializeData(JsonObject object, P provider);
}
