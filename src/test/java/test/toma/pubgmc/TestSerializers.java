package test.toma.pubgmc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.animation.serializers.Vec3dSerializer;
import net.minecraft.util.math.Vec3d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSerializers {

    @Test
    public void testVec3dSerialization() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Vec3d.class, new Vec3dSerializer()).create();
        String vec3dString = "{\"x\":0.15,\"y\":0.35,\"z\":0.55}";
        Vec3d vec3d = gson.fromJson(vec3dString, Vec3d.class);
        assertEquals(0.15, vec3d.x, 0.0001);
        assertEquals(0.35, vec3d.y, 0.0001);
        assertEquals(0.55, vec3d.z, 0.0001);

        vec3d = new Vec3d(-0.12, 3.45, 6.789);
        JsonElement element = gson.toJsonTree(vec3d, Vec3d.class);
        assertEquals(-0.12, element.getAsJsonObject().get("x").getAsFloat(), 0.0001);
        assertEquals(3.45, element.getAsJsonObject().get("y").getAsFloat(), 0.0001);
        assertEquals(6.789, element.getAsJsonObject().get("z").getAsFloat(), 0.0001);
    }

    @Test
    public void testKeyFrameSerialization() {
        Gson gson = AnimationLoader.GSON;
        KeyFrame empty = KeyFrame.EMPTY_FRAME;
        KeyFrame emptyEndpoint = () -> 0.5F;
        KeyFrame moved = KeyFrame.move(0.4F, new Vec3d(1, 2, 3));
        KeyFrame rotated = KeyFrame.rotate(0.978F, new Vec3d(3, 2, 1), new Vec3d(1, 2, 3));
        JsonElement je0 = gson.toJsonTree(empty);
        JsonElement je1 = gson.toJsonTree(emptyEndpoint);
        JsonElement je2 = gson.toJsonTree(moved);
        JsonElement je3 = gson.toJsonTree(rotated);
        KeyFrame d_empty = gson.fromJson(je0, KeyFrame.class);
        KeyFrame d_emptyEndpoint = gson.fromJson(je1, KeyFrame.class);
        KeyFrame d_moved = gson.fromJson(je2, KeyFrame.class);
        KeyFrame d_rotated = gson.fromJson(je3, KeyFrame.class);
        assertEquals(0.0F, d_empty.endPoint(), 0.0001F);
        assertEquals(0.5F, d_emptyEndpoint.endPoint(), 0.0001F);
        assertEquals(Vec3d.ZERO, d_emptyEndpoint.moveTarget());
        assertEquals(new Vec3d(1, 2, 3), d_moved.moveTarget());
        assertEquals(0.978D, d_rotated.endPoint(), 0.0001F);
        assertEquals(new Vec3d(1, 2, 3), d_rotated.rotateTarget());
    }
}
