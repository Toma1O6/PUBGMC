package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.atachments.ModelHolographic;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

public class HolographicRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelHolographic model = new ModelHolographic();

    @Override
    public ModelHolographic getModel() {
        return model;
    }
}
