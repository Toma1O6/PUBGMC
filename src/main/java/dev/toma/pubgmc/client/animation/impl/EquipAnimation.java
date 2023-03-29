package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipAnimation implements Animation {

    final AnimationSpec spec;
    final Map<AnimationElement, Integer> elementIndexCache;
    float progress, progressPrev, progressSmooth;

    public EquipAnimation(AnimationSpec spec) {
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
    public void tick() {
        this.progressPrev = progress;
        progress = this.getProgress();
        this.updateCache();
    }

    @Override
    public void renderTick(float partialTicks) {
        progressSmooth = progressPrev + (progress - progressPrev) * partialTicks;
    }

    public void updateCache() {
        for (Map.Entry<AnimationElement, List<KeyFrame>> entry : spec.getFrameDefs().entrySet()) {
            List<KeyFrame> keyFrames = entry.getValue();
            AnimationElement element = entry.getKey();
            int index = elementIndexCache.get(element);
            KeyFrame frame = keyFrames.get(index);
            float endpoint = frame.endPoint();
            if (progress > endpoint && index + 1 < keyFrames.size())
                elementIndexCache.put(element, index + 1);
        }
    }

    public float getProgress() {
        return Minecraft.getMinecraft().getItemRenderer().equippedProgressMainHand;
    }

    @Override
    public boolean shouldRemove() {
        return Minecraft.getMinecraft().getItemRenderer().equippedProgressMainHand == 1.0F;
    }
}
