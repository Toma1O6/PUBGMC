package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMP5K;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMP5K extends WeaponRenderer {

    final ModelMP5K model = new ModelMP5K();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(-0.01F, 0.06F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translated(-0.01F, 0.07F, 0.27F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.1F, 0.225F, 0.3F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.04F, 0.15F, 0.24F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(-0.01F, 0.09F, 0.2F));
    }
}
