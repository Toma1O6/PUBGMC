package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class AnimationSpec {

    private final Map<AnimationElement, List<KeyFrame>> frameDefs;

    public AnimationSpec(Map<AnimationElement, List<KeyFrame>> frameDefs) {
        this.frameDefs = frameDefs;
    }

    public static AnimationSpec jump(AnimationElement element, double x, double y, double z, double rotateX, double rotateY, double rotateZ) {
        Map<AnimationElement, List<KeyFrame>> map = DevUtil.make(new HashMap<>(), m -> {
            List<KeyFrame> list = new ArrayList<>();
            list.add(KeyFrame.rotate(0.5F, new Vec3d(x, y, z), new Vec3d(rotateX, rotateY, rotateZ)));
            list.add(KeyFrame.rotate(1.0F, new Vec3d(-x, -y, -z), new Vec3d(-rotateX, -rotateY, -rotateZ)));
            m.put(element, list);
        });
        return new AnimationSpec(map);
    }

    public Optional<List<KeyFrame>> getDefs(AnimationElement element) {
        return Optional.ofNullable(frameDefs.get(element));
    }

    public Map<AnimationElement, List<KeyFrame>> getFrameDefs() {
        return frameDefs;
    }
}
