package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGroza;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderGroza extends WeaponRenderer {

    final ModelGroza model = new ModelGroza();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.15F, 0.42F, -0.06F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.translated(0F, 0.09F, 0.81F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0.1F, 0.34F, -0.07F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.1F, 0.35F, -0.15F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.2F, 0.49F, -0.08F, 0.6F, 0.6F, 0.6F));

    }
}
