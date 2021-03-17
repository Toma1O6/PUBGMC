package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.toma.pubgmc.client.animation.AnimationSpec;

import java.lang.reflect.Type;

public class AnimationSpecDeserializer implements JsonDeserializer<AnimationSpec> {

    @Override
    public AnimationSpec deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
