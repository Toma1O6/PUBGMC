package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelSLR;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderSLR extends WeaponRenderer {

    final ModelSLR model = new ModelSLR();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, -0.08, 0.0);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.positioned(0F, 0.01F, 0.3F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, 0.05F, 0.22F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positioned(0F, 0.15F, 0.3F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positioned(0F, 0.13F, 0.24F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.2F, 0.24F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(0F, 0.05F, 0.2F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0.005F, 0.06F, 0.14F));

    }
}
