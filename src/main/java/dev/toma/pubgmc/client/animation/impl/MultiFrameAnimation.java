package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiFrameAnimation extends TickableAnimation {

    final AnimationSpec spec;
    final Map<AnimationElement, Integer> elementIndexCache;
    float progress, progressPrev, progressSmooth;

    public MultiFrameAnimation(int length, AnimationSpec spec) {
        super(length);
        this.spec = spec;
        this.elementIndexCache = DevUtil.make(new HashMap<>(), map -> spec.getFrameDefs().keySet().forEach(element -> map.put(element, 0)));
    }

    @Override
    public void animateElement(AnimationElement element) {
        spec.getDefs(element).ifPresent(list -> {
            int index = elementIndexCache.get(element);
            KeyFrame currentFrame = list.get(index);
            KeyFrame previousFrame = DevUtil.getPrevious(list, index, KeyFrame.EMPTY_FRAME);
            float f0 = previousFrame.endPoint();
            float f1 = currentFrame.endPoint();
            float f2 = (progressSmooth - f0) / (f1 - f0);
            AnimationProcessor.processKeyFrame(currentFrame, f2);
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
        this.updateCache();
    }

    public void updateCache() {
        for (Map.Entry<AnimationElement, List<KeyFrame>> entry : spec.getFrameDefs().entrySet()) {
            List<KeyFrame> keyFrames = entry.getValue();
            AnimationElement element = entry.getKey();
            int index = elementIndexCache.get(element);
            KeyFrame frame = keyFrames.get(index);
            float endpoint = frame.endPoint();
            if(progress > endpoint && index + 1 < keyFrames.size())
                elementIndexCache.put(element, index + 1);
        }
    }

    public void resetCache() {
        elementIndexCache.replaceAll((k, v) -> 0);
    }

    public AnimationSpec getSpec() {
        return spec;
    }

    public float getProgressPrev() {
        return progressPrev;
    }

    public float getProgressSmooth() {
        return progressSmooth;
    }
}
