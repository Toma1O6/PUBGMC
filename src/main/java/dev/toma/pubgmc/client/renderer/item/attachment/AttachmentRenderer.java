package dev.toma.pubgmc.client.renderer.item.attachment;

import dev.toma.pubgmc.client.models.atachments.ModelAttachment;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import net.minecraft.client.renderer.GlStateManager;

import java.util.HashMap;
import java.util.Map;

public abstract class AttachmentRenderer<I extends ItemAttachment> {

    private static final Map<ItemAttachment, AttachmentRenderer<?>> RENDERERS = new HashMap<>();

    public abstract ModelAttachment<I> getModel();

    public void preRenderCallback() {}

    public final void render(WeaponRenderer renderer, I item) {
        GlStateManager.pushMatrix();
        IRenderConfig config = renderer.getRenderConfig(item);
        if(config != null) {
            config.applyTransforms();
        }
        this.preRenderCallback();
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.2F, 0.2F, 0.2F);
        this.getModel().render();
        GlStateManager.popMatrix();
    }

    public static <I extends ItemAttachment> void registerRenderer(I item, AttachmentRenderer<I> renderer) {
        RENDERERS.put(item, renderer);
    }

    @SuppressWarnings("unchecked")
    public static <I extends ItemAttachment> AttachmentRenderer<I> getRenderFor(I attachment) {
        return (AttachmentRenderer<I>) RENDERERS.get(attachment);
    }

    public static boolean hasRender(ItemAttachment attachment) {
        return RENDERERS.containsKey(attachment);
    }
}
