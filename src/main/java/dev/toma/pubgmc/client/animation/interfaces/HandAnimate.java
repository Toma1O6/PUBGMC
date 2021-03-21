package dev.toma.pubgmc.client.animation.interfaces;

import dev.toma.pubgmc.client.renderer.IRenderConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface HandAnimate {

    @SideOnly(Side.CLIENT)
    void animate(EnumHandSide side);

    @SideOnly(Side.CLIENT)
    static void renderHand(EnumHandSide side, IRenderConfig cfg) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
        Render<AbstractClientPlayer> render = mc.getRenderManager().getEntityRenderObject(mc.player);
        RenderPlayer playerRender = (RenderPlayer) render;
        GlStateManager.pushMatrix();
        cfg.applyTransforms();
        GlStateManager.rotate(40.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
        if (side == EnumHandSide.RIGHT) {
            GlStateManager.translate(0.8F, -0.3F, -0.4F);
            GlStateManager.rotate(-41.0F, 0.0F, 0.0F, 1.0F);
            playerRender.renderRightArm(mc.player);
        } else {
            GlStateManager.translate(-0.5F, 0.6F, -0.36F);
            GlStateManager.rotate(-41.0F, 0.0F, 0.0F, 1.0F);
            playerRender.renderLeftArm(mc.player);
        }
        GlStateManager.popMatrix();
    }
}
