package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelRedDot;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.renderer.GlStateManager;

public class RedDotRenderer extends AttachmentRenderer<ItemScope> {

    final ModelRedDot model = new ModelRedDot();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 0.97, 0.4);
        GlStateManager.scale(0.06F, 0.06F, 0.04F);
    }

    @Override
    public ModelRedDot getModel() {
        return model;
    }
}
