package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelAUG;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderAUG extends WeaponRenderer {

    final ModelAUG model = new ModelAUG();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, -0.05, 0.0);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.05F, 0.16F, -0.03F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.translated(0F, 0.04F, 0.65F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.translatedScaled(0F, 0.1F, 0.04F, 1F, 1F, 0.7F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translatedScaled(0.1F, 0.17F, 0.09F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translated(0F, 0.07F, 0F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.1F, 0.09F, 0F, 0.8F, 1F, 1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.1F, 0.23F, 0.02F, 0.8F, 0.8F, 0.8F));

    }
}
