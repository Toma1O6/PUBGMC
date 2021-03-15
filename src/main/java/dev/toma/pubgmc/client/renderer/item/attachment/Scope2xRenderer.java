package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope2x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class Scope2xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope2x model = new ModelScope2x();

    @Override
    public void preRenderCallback() {

    }

    @Override
    public ModelScope2x getModel() {
        return model;
    }
}
