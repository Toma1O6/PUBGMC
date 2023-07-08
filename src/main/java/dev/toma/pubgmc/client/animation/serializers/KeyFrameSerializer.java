package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.*;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class KeyFrameSerializer implements JsonSerializer<KeyFrame>, JsonDeserializer<KeyFrame> {

    @Override
    public JsonElement serialize(KeyFrame src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        float endPoint = src.endPoint();
        Vec3d move = src.moveTarget();
        Vec3d rotate = src.rotateTarget();
        object.addProperty("endpoint", endPoint);
        if (!move.equals(Vec3d.ZERO))
            object.add("move", context.serialize(move, Vec3d.class));
        if (!rotate.equals(Vec3d.ZERO))
            object.add("rotate", context.serialize(rotate, Vec3d.class));
        return object;
    }

    @Override
    public KeyFrame deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject())
            throw new JsonParseException("Expected object, got: " + json.getClass().getSimpleName());
        JsonObject object = json.getAsJsonObject();
        float endpoint = JsonUtils.getFloat(object.get("endpoint"), "endpoint");
        Vec3d move = Vec3d.ZERO;
        if (object.has("move")) {
            move = context.deserialize(object.get("move"), Vec3d.class);
        }
        KeyFrame frame;
        if (object.has("rotate")) {
            Vec3d rotate = context.deserialize(object.get("rotate"), Vec3d.class);
            frame = KeyFrame.rotate(endpoint, move, rotate);
        } else if (!move.equals(Vec3d.ZERO)) {
            frame = KeyFrame.move(endpoint, move);
        } else frame = KeyFrame.empty(endpoint);
        return frame;
    }
}
