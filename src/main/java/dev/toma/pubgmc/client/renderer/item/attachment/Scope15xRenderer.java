package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope15x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class Scope15xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope15x model = new ModelScope15x();

    @Override
    public ModelScope15x getModel() {
        return model;
    }
}
