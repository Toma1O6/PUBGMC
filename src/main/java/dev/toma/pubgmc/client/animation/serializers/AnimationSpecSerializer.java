package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.*;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationSpecSerializer implements JsonSerializer<AnimationSpec>, JsonDeserializer<AnimationSpec> {

    @Override
    public JsonElement serialize(AnimationSpec src, Type typeOfSrc, JsonSerializationContext context) {
        Map<AnimationElement, List<KeyFrame>> defs = src.getFrameDefs();
        JsonObject object = new JsonObject();
        for (Map.Entry<AnimationElement, List<KeyFrame>> entry : defs.entrySet()) {
            JsonArray array = new JsonArray();
            for (KeyFrame frame : entry.getValue()) {
                array.add(context.serialize(frame, KeyFrame.class));
            }
            object.add(String.valueOf(entry.getKey().getIndex()), array);
        }
        return object;
    }

    @Override
    public AnimationSpec deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject())
            throw new JsonParseException("Expected object, got: " + json.getClass().getSimpleName());
        JsonObject object = json.getAsJsonObject();
        Map<AnimationElement, List<KeyFrame>> defs = new HashMap<>();
        for (AnimationElement element : AnimationElement.values()) {
            String key = String.valueOf(element.getIndex());
            if(object.has(key)) {
                List<KeyFrame> list = new ArrayList<>();
                defs.put(element, list);
                JsonArray array = JsonUtils.getJsonArray(object, key);
                for (JsonElement jsonElement : array) {
                    KeyFrame frame = context.deserialize(jsonElement, KeyFrame.class);
                    list.add(frame);
                }
            }
        }
        return new AnimationSpec(defs);
    }
}
