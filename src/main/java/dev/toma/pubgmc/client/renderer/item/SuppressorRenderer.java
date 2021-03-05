package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.atachments.ModelSuppressor;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;

public class SuppressorRenderer extends AttachmentRenderer<ItemMuzzle> {

    private final ModelSuppressor model = new ModelSuppressor();

    @Override
    public ModelSuppressor getModel() {
        return model;
    }
}
