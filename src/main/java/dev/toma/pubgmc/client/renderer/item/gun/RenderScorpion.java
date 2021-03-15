package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelScorpion;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderScorpion extends WeaponRenderer {

    final ModelScorpion model = new ModelScorpion();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.05F, 0.14F, 0F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translatedScaled(-0.1F, -0.01F, 0.7F, 1.2F, 1.2F, 1.2F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translated(0F, 0.1F, 0.13F));
    }
}
