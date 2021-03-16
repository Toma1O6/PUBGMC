package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope8x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.renderer.GlStateManager;

public class Scope8xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope8x model = new ModelScope8x();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 0.87, 0.3);
        GlStateManager.scale(0.08F, 0.08F, 0.08F);
    }

    @Override
    public ModelScope8x getModel() {
        return model;
    }
}
