package dev.toma.pubgmc.client.animation.interfaces;

import dev.toma.pubgmc.client.animation.AnimationElement;

public interface Animation {

    void animateElement(AnimationElement element);

    void tick();

    void renderTick(float partialTicks);

    boolean shouldRemove();

    default boolean blocksItemRender() {
        return false;
    }
}
