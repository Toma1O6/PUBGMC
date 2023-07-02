package dev.toma.pubgmc.api.game.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public interface LootProcessorSerializer<P extends LootProcessor> {

    P parse(JsonObject object) throws JsonParseException;

    void serialize(JsonObject object, P processor);
}
