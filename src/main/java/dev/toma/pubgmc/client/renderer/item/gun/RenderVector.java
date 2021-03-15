package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelVector;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderVector extends WeaponRenderer {

    final ModelVector model = new ModelVector();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(-0.05F, -0.12F, 0.1F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(-0.05F, -0.1F, 0F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(0F, -0.09F, 0.44F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(0F, -0.04F, 0.13F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translated(0F, -0.05F, 0.06F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.05F, 0.03F, 0.09F, 0.9F, 0.9F, 0.9F));

    }
}
