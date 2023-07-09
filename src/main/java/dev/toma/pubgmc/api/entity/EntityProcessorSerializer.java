package dev.toma.pubgmc.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public interface EntityProcessorSerializer<E extends EntityProcessor> {

    E parse(JsonObject object) throws JsonParseException;

    void serialize(JsonObject object, E processor);
}
