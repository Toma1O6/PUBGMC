package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.item.Item;

public interface ScopeZoom {

    float getCurrentZoom(Item item, float deltaTicks);

    float getSensitivity(Item item);

    boolean hasMouseScrollOverrides();

    boolean zoomIn(Item item);

    boolean zoomOut(Item item);
}
