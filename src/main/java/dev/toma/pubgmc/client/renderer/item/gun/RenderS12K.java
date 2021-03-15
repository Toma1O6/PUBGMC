package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelS12K;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderS12K extends WeaponRenderer {

    final ModelS12K model = new ModelS12K();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0.075F, 0.1F, 0.27F, 0.8F, 0.9F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.075F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.175F, 0.31F, 0.32F, 0.6F, 0.6F, 0.6F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.125F, 0.18F, 0.27F, 0.7F, 0.8F, 0.8F));
    }
}
