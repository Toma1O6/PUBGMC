package dev.toma.pubgmc.client.animation.impl;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectedAnimation extends MultiFrameAnimation {

    ConnectedAnimation(Builder builder) {
        super(builder.length, builder.convert());
    }

    static class ConnectionSpec {

        final float endpoint;
        final int repeatCount;
        final AnimationSpec spec;

        ConnectionSpec(float endpoint, AnimationSpec spec) {
            this(endpoint, 1, spec);
        }

        ConnectionSpec(float endpoint, int repeatCount, AnimationSpec spec) {
            this.endpoint = endpoint;
            this.repeatCount = repeatCount;
            this.spec = spec;
        }
    }

    public static class Builder {

        int length;
        List<ConnectionSpec> specs = new ArrayList<>();

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder addSpec(float endpoint, AnimationSpec spec) {
            return this.addSpec(endpoint, 1, spec);
        }

        public Builder addSpec(float endpoint, int repeatCount, AnimationSpec spec) {
            this.specs.add(new ConnectionSpec(endpoint, repeatCount, spec));
            return this;
        }

        public ConnectedAnimation build() {
            Preconditions.checkState(length > 0, "Length must be bigger than 0");
            Preconditions.checkState(specs.size() > 0, "You must define atleast one animation");
            return new ConnectedAnimation(this);
        }

        // quite slow, avoid calling when not needed
        private AnimationSpec convert() {
            Map<AnimationElement, List<KeyFrame>> map = new HashMap<>();
            float min = 0.0F;
            for (ConnectionSpec connectionSpec : specs) {
                float max = connectionSpec.endpoint;
                AnimationSpec animSpec = connectionSpec.spec;
                int repeats = connectionSpec.repeatCount;
                float repeatModifier = 1.0F / repeats;
                for (int i = 1; i <= repeats; i++) {
                    float f = i * repeatModifier;
                    for (Map.Entry<AnimationElement, List<KeyFrame>> entry : animSpec.getFrameDefs().entrySet()) {
                        List<KeyFrame> modifiedList = map.computeIfAbsent(entry.getKey(), element -> new ArrayList<>());
                        for (KeyFrame frame : entry.getValue()) {
                            float end = frame.endPoint();
                            float modifiedEnd = (max - min) * (end * f) + min;
                            boolean hasRotation = !frame.rotateTarget().equals(Vec3d.ZERO);
                            modifiedList.add(hasRotation ? KeyFrame.rotate(modifiedEnd, frame.moveTarget(), frame.rotateTarget()) : KeyFrame.move(modifiedEnd, frame.moveTarget()));
                        }
                    }
                }
                min = max;
            }
            return new AnimationSpec(map);
        }
    }
}
