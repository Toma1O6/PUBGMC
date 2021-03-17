package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiFrameAnimation extends TickableAnimation {

    final AnimationSpec spec;
    final Map<String, Integer> elementIndexCache;
    float progress, progressPrev, progressSmooth;

    public MultiFrameAnimation(int length, AnimationSpec spec) {
        super(length);
        this.spec = spec;
        this.elementIndexCache = DevUtil.make(new HashMap<>(), map -> spec.getFrameDefs().keySet().forEach(element -> map.put(element, 0)));
    }

    @Override
    public void animateElement(String element) {
        spec.getDefs(element).ifPresent(list -> {
            int index = elementIndexCache.get(element);
            KeyFrame currentFrame = list.get(index);
            KeyFrame previousFrame = DevUtil.getPrevious(list, index, KeyFrame.EMPTY_FRAME);
            float f0 = previousFrame.endPoint();
            float f1 = currentFrame.endPoint();
            float f2 = (f1 - f0) * progressSmooth;
            AnimationProcessor.processKeyFrame(previousFrame, currentFrame, f2);
        });
    }

    @Override
    public void renderTick(float partialTicks) {
        progressSmooth = progressPrev + (progress - progressPrev) * partialTicks;
    }

    @Override
    public void tick() {
        super.tick();
        this.progressPrev = progress;
        progress = this.getProgress();
        for (Map.Entry<String, List<KeyFrame>> entry : spec.getFrameDefs().entrySet()) {
            List<KeyFrame> keyFrames = entry.getValue();
            String element = entry.getKey();
            int index = elementIndexCache.get(element);
            KeyFrame frame = keyFrames.get(index);
            float endpoint = frame.endPoint();
            if(progress > endpoint)
                elementIndexCache.put(element, index + 1);
        }
    }
}
