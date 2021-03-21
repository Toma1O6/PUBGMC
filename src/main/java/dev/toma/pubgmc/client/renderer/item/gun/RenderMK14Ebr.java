package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMK14;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMK14Ebr extends WeaponRenderer {

    final ModelMK14 model = new ModelMK14();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.positionedScaled(-0.18F, -0.16F, -0.4F, 1.4F, 1.4F, 1.4F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0.02F, 0.05F, -0.5F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positioned(0.02F, 0.15F, -0.4F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positioned(0.02F, 0.15F, -0.5F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positioned(0.02F, 0.1F, -0.6F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(0.02F, 0.08F, -0.6F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0.02F, 0.075F, -0.53F));
    }
}
