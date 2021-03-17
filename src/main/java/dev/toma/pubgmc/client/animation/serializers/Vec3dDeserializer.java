package dev.toma.pubgmc.client.animation.serializers;

import com.google.gson.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class Vec3dDeserializer implements JsonDeserializer<Vec3d> {

    @Override
    public Vec3d deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(!json.isJsonObject())
            throw new JsonParseException("Expected object, got: " + json.getClass().getSimpleName());
        JsonObject object = json.getAsJsonObject();
        double x = JsonUtils.getFloat(object, "x");
        double y = JsonUtils.getFloat(object, "y");
        double z = JsonUtils.getFloat(object, "z");
        if(x == 0 && y == 0 && z == 0)
            return Vec3d.ZERO;
        return new Vec3d(x, y, z);
    }
}
