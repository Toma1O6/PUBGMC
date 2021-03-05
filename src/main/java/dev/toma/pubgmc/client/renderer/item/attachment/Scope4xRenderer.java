package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope4x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class Scope4xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope4x model = new ModelScope4x();

    @Override
    public ModelScope4x getModel() {
        return model;
    }
}
