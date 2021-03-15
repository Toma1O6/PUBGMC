package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelPP19Bizon;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderPPBizon extends WeaponRenderer {

    final ModelPP19Bizon model = new ModelPP19Bizon();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(0F, -0.01F, -0.2F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(-0.055F, -0.01F, 0.1F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translated(-0.005F, 0.08F, 0.05F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translated(-0.005F, 0.08F, 0.06F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(-0.01F, 0.08F, 0.1F));
    }
}
