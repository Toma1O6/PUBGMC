package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelKar98K;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderKar98k extends WeaponRenderer {

    final ModelKar98K model = new ModelKar98K();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.1F, 0.2F, 0.48F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positionedScaled(0.1F, 0.29F, 0.52F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1F, 0.2F, 0.41F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.1F, 0.19F, 0.44F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.2F, 0.35F, 0.49F, 0.6F, 0.6F, 0.6F));
        registerRenderConfig(PMCItems.SILENCER_SNIPER, IRenderConfig.positioned(0F, 0.125F, -0.08F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positionedScaled(0.1F, 0.27F, 0.47F, 0.8F, 0.8F, 0.8F));
    }
}
