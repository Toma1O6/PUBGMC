package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.atachments.ModelRedDot;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class RedDotRenderer extends AttachmentRenderer<ItemScope> {

    final ModelRedDot model = new ModelRedDot();

    @Override
    public ModelRedDot getModel() {
        return model;
    }
}
