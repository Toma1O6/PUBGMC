package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelAngledGrip;
import dev.toma.pubgmc.common.items.attachment.ItemGrip;
import net.minecraft.client.renderer.GlStateManager;

public class AngledGripRenderer extends AttachmentRenderer<ItemGrip> {

    private final ModelAngledGrip model = new ModelAngledGrip();

    @Override
    public void preRenderCallback() {
        GlStateManager.translate(0.5, 0.75, -0.2);
        GlStateManager.scale(0.1F, 0.1F, 0.1F);
    }

    @Override
    public ModelAngledGrip getModel() {
        return model;
    }
}
