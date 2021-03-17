package dev.toma.pubgmc.client.animation.interfaces;

public interface Animation {

    void animateElement(String element);

    void tick();

    void renderTick(float partialTicks);

    boolean shouldRemove();
}
