package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class KeyFrameSerializer implements JsonSerializer<KeyFrame> {

    @Override
    public JsonElement serialize(KeyFrame src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        float endPoint = src.endPoint();
        Vec3d move = src.moveTarget();
        Vec3d rotate = src.rotateTarget();
        object.addProperty("endpoint", endPoint);
        if(!move.equals(Vec3d.ZERO))
            object.add("move", context.serialize(move, Vec3d.class));
        if(!rotate.equals(Vec3d.ZERO))
            object.add("rotate", context.serialize(rotate, Vec3d.class));
        return object;
    }
}
