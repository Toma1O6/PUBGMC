package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.client.animation.interfaces.Animation;

public abstract class TickableAnimation implements Animation {

    final int initialTickLength;
    protected int actualTickLength;

    public TickableAnimation(int length) {
        this.initialTickLength = length;
        this.actualTickLength = length;
    }

    public float getProgress() {
        return 1.0F - ((float) actualTickLength / initialTickLength);
    }

    @Override
    public void tick() {
        --actualTickLength;
    }

    @Override
    public boolean shouldRemove() {
        return actualTickLength <= 0;
    }
}
