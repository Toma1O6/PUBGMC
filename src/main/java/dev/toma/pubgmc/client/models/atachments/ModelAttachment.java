package dev.toma.pubgmc.client.models.atachments;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public abstract class ModelAttachment<I extends ItemAttachment> extends ModelBase {

    public static final ResourceLocation OVERLAY = Pubgmc.getResource("textures/white_texture.png");

    public static void renderReticle(float aimPct, ModelRenderer main, ModelRenderer reticle, ResourceLocation texture, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        main.render(1.0F);
        float a = (ConfigPMC.developerMode.get() ? 1.0F : aimPct) * ((color >> 24) & 255) / 255F;
        float r = ((color >> 16) & 255) / 255F;
        float g = ((color >> 8) & 255) / 255F;
        float b = (color & 255) / 255F;
        mc.getTextureManager().bindTexture(texture);
        GlStateManager.color(r, g, b, a);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        reticle.render(1.0F);
        int light = mc.world.getCombinedLight(mc.getRenderViewEntity().getPosition(), 0);
        int i = light % 0x10000;
        int j = light / 0x10000;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, i, j);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void renderScope(ModelRenderer mainRenderer, ResourceLocation reticleTexture, float aimPct, ModelRenderer reticleRenderer, ModelRenderer overlayRenderer) {
        renderScope(mainRenderer, reticleTexture, aimPct, reticleRenderer, overlayRenderer, 0.05F);
    }

    public static void renderScope(ModelRenderer mainRenderer, ResourceLocation reticleTexture, float aimPct, ModelRenderer reticleRenderer, ModelRenderer overlayRenderer, float scaleMin) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager manager = mc.getTextureManager();
        float inv = 1.0F - aimPct;
        float z2 = 1.0F - scaleMin;
        GlStateManager.scale(1.0F, 1.0F, scaleMin + z2 * inv);
        mainRenderer.render(1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, aimPct);
        manager.bindTexture(reticleTexture);
        reticleRenderer.render(1.0F);
        GlStateManager.color(0.0F, 0.0F, 0.0F, inv);
        manager.bindTexture(OVERLAY);
        overlayRenderer.render(1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public abstract void render(float aimPct);
}
