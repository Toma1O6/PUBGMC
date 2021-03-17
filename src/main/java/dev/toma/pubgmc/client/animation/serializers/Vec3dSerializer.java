package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class Vec3dSerializer implements JsonSerializer<Vec3d> {

    @Override
    public JsonElement serialize(Vec3d src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("x", src.x);
        object.addProperty("y", src.y);
        object.addProperty("z", src.z);
        return object;
    }
}
