package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.gui.animator.AnimatorCache;

import java.util.List;

public class AnimatorAnimation extends MultiFrameAnimation {

    boolean paused;
    boolean markedForRemoval;

    public AnimatorAnimation(int length) {
        super(length, new AnimationSpec(AnimatorCache.project.toImmutable()));
    }

    public void set(float progress) {
        this.progress = progress;
        this.progressPrev = progress;
        this.progressSmooth = progress;
        int max = 0;
        for (List<KeyFrame> list : spec.getFrameDefs().values()) {
            if(list.size() > max)
                max = list.size();
        }
        resetCache();
        for (int i = 0; i < max; i++) {
            updateCache();
        }
    }

    @Override
    public boolean shouldRemove() {
        return markedForRemoval;
    }

    @Override
    public void tick() {
        if (!paused)
            super.tick();
        if(actualTickLength < 0) {
            actualTickLength = initialTickLength;
            resetCache();
        }
    }

    @Override
    public void renderTick(float partialTicks) {
        if (!paused)
            super.renderTick(partialTicks);
    }

    public boolean isPaused() {
        return paused;
    }

    public AnimatorAnimation setPaused(boolean paused) {
        this.paused = paused;
        return this;
    }

    public void markForRemoval() {
        this.markedForRemoval = true;
    }
}
