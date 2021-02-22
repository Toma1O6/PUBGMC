package dev.toma.pubgmc.client.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

public interface EventTypeDeserializer<C extends MenuDisplayContent> {

    C deserialize(JsonObject object, JsonDeserializationContext ctx);
}
