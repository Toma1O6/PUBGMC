package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelHolographic;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.renderer.GlStateManager;

public class HolographicRenderer extends AttachmentRenderer<ItemScope> {

    private final ModelHolographic model = new ModelHolographic();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 1.3, 0.45);
        GlStateManager.scale(0.13F, 0.13F, 0.13F);
    }

    @Override
    public ModelHolographic getModel() {
        return model;
    }
}
