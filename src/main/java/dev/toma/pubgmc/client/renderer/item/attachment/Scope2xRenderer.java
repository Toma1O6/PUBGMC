package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope2x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.renderer.GlStateManager;

public class Scope2xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope2x model = new ModelScope2x();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 1.3, 0.45);
        GlStateManager.scale(0.13F, 0.13F, 0.13F);
    }

    @Override
    public ModelScope2x getModel() {
        return model;
    }
}
