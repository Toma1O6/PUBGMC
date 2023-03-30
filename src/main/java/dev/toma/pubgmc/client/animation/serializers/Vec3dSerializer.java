package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class Vec3dSerializer implements JsonSerializer<Vec3d>, JsonDeserializer<Vec3d> {

    @Override
    public JsonElement serialize(Vec3d src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("x", src.x);
        object.addProperty("y", src.y);
        object.addProperty("z", src.z);
        return object;
    }

    @Override
    public Vec3d deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(!json.isJsonObject())
            throw new JsonParseException("Expected object, got: " + json.getClass().getSimpleName());
        JsonObject object = json.getAsJsonObject();
        double x = JsonUtils.getFloat(object.get("x"), "x");
        double y = JsonUtils.getFloat(object.get("y"), "y");
        double z = JsonUtils.getFloat(object.get("z"), "z");
        if(x == 0 && y == 0 && z == 0)
            return Vec3d.ZERO;
        return new Vec3d(x, y, z);
    }
}
