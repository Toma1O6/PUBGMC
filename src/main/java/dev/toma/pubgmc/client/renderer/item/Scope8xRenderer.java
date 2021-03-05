package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.atachments.ModelScope8x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class Scope8xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope8x model = new ModelScope8x();

    @Override
    public ModelScope8x getModel() {
        return model;
    }
}
