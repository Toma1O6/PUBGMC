package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelScope15x;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.renderer.GlStateManager;

public class Scope15xRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelScope15x model = new ModelScope15x();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 1.1, 0.25);
        GlStateManager.scale(0.08F, 0.08F, 0.08F);
    }

    @Override
    public ModelScope15x getModel() {
        return model;
    }
}
