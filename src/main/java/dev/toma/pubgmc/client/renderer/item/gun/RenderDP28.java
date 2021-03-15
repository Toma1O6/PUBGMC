package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelDP28;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderDP28 extends WeaponRenderer {

    final ModelDP28 model = new ModelDP28();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(0F, -0.02F, 0.2F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.15F, 0.17F, 0.32F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0F, -0.05F, 0.33F, 1F, 1F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.15F, 0.17F, 0.3F, 0.7F, 0.7F, 0.7F));

    }
}
