package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimationSpec {

    private final Map<String, List<KeyFrame>> frameDefs = new HashMap<>();

    public Optional<List<KeyFrame>> getDefs(String element) {
        return Optional.of(frameDefs.get(element));
    }

    public Map<String, List<KeyFrame>> getFrameDefs() {
        return frameDefs;
    }
}
