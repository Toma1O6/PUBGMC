package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelVerticalGrip;
import dev.toma.pubgmc.common.items.attachment.ItemGrip;
import net.minecraft.client.renderer.GlStateManager;

public class VerticalGripRenderer extends AttachmentRenderer<ItemGrip> {

    private final ModelVerticalGrip model = new ModelVerticalGrip();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 0.53, -0.2);
        GlStateManager.scale(0.1F, 0.1F, 0.1F);
    }

    @Override
    public ModelVerticalGrip getModel() {
        return model;
    }
}
