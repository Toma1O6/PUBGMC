package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.lang.reflect.Type;

public class KeyFrameDeserializer implements JsonDeserializer<KeyFrame> {

    @Override
    public KeyFrame deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
