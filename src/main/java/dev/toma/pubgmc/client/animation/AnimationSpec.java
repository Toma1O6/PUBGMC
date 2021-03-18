package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimationSpec {

    private final Map<AnimationElement, List<KeyFrame>> frameDefs;

    public AnimationSpec(Map<AnimationElement, List<KeyFrame>> frameDefs) {
        this.frameDefs = frameDefs;
    }

    public Optional<List<KeyFrame>> getDefs(AnimationElement element) {
        return Optional.of(frameDefs.get(element));
    }

    public Map<AnimationElement, List<KeyFrame>> getFrameDefs() {
        return frameDefs;
    }
}
