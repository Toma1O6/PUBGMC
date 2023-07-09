package dev.toma.pubgmc.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public interface EntityProviderSerializer<E extends EntityProvider> {

    E parse(JsonObject data) throws JsonParseException;

    void serializeData(JsonObject object, E provider);
}
