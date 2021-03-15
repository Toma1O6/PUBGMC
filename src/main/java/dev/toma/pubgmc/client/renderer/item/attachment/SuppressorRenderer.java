package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelSuppressor;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import net.minecraft.client.renderer.GlStateManager;

public class SuppressorRenderer extends AttachmentRenderer<ItemMuzzle> {

    private final ModelSuppressor model = new ModelSuppressor();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 1.04, -1.25);
        GlStateManager.scale(0.1F, 0.1F, 0.1F);
    }

    @Override
    public ModelSuppressor getModel() {
        return model;
    }
}
