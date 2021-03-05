package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.atachments.ModelAngledGrip;
import dev.toma.pubgmc.common.items.attachment.ItemGrip;

public class AngledGripRenderer extends AttachmentRenderer<ItemGrip> {

    private final ModelAngledGrip model = new ModelAngledGrip();

    @Override
    public ModelAngledGrip getModel() {
        return model;
    }
}
