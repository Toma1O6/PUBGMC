package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelG36C;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderG36C extends WeaponRenderer {

    final ModelG36C model = new ModelG36C();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.12F, 0.29F, 0.16F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.translated(0.02F, 0.06F, 0.655F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.translated(0.02F, 0.14F, 0.24F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translated(0.02F, 0.11F, 0.2F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0.07F, 0.2F, 0.16F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.07F, 0.15F, 0.1F, 0.9F, 1F, 1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.17F, 0.36F, 0.2F, 0.7F, 0.7F, 0.7F));

    }
}
