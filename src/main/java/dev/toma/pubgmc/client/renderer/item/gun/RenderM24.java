package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelM24;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderM24 extends WeaponRenderer {

    final ModelM24 model = new ModelM24();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, -0.11, 0.1);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.1F, 0.33F, -0.1F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.translatedScaled(0.1F, 0.41F, 0F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.1F, 0.33F, -0.1F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0.1F, 0.3F, -0.1F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.1F, 0.34F, -0.13F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SILENCER_SNIPER, IRenderConfig.translated(0F, 0.22F, 0F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.translatedScaled(0.1F, 0.39F, -0.06F, 0.8F, 0.8F, 0.8F));
    }
}
