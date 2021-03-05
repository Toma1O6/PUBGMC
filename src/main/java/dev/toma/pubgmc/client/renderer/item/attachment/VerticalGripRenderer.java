package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelVerticalGrip;
import dev.toma.pubgmc.common.items.attachment.ItemGrip;

public class VerticalGripRenderer extends AttachmentRenderer<ItemGrip> {

    private final ModelVerticalGrip model = new ModelVerticalGrip();

    @Override
    public ModelVerticalGrip getModel() {
        return model;
    }
}
