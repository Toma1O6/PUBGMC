package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelQBU;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderQBU extends WeaponRenderer {

    final ModelQBU model = new ModelQBU();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.positioned(0F, -0.1F, 0.1F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, -0.08F, 0F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positioned(0F, 0F, 0F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positioned(0F, 0F, 0F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.07F, 0.1F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(0F, -0.06F, -0.05F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, -0.07F, 0F));

    }
}
