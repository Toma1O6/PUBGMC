package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class AnimationSpec {

    public static final AnimationSpec EMPTY_SPEC = new AnimationSpec(new HashMap<>());
    private final Map<AnimationElement, List<KeyFrame>> frameDefs;

    public AnimationSpec(Map<AnimationElement, List<KeyFrame>> frameDefs) {
        this.frameDefs = frameDefs;
        compileFrames();
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

    protected void compileFrames() {
        for (List<KeyFrame> list : frameDefs.values()) {
            Vec3d totalPos = new Vec3d(0, 0, 0);
            Vec3d totalRot = new Vec3d(0, 0, 0);
            for (int i = 0; i < list.size(); i++) {
                KeyFrame currentFrame = list.get(i);
                KeyFrame previousFrame = DevUtil.getPrevious(list, i, KeyFrame.EMPTY_FRAME);
                totalPos = totalPos.add(previousFrame.moveTarget());
                totalRot = totalRot.add(previousFrame.rotateTarget());
                currentFrame.setPositionStart(totalPos);
                currentFrame.setRotationStart(totalRot);
            }
        }
    }
}
