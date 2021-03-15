package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP92;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderP92 extends WeaponRenderer {

    final ModelP92 model = new ModelP92();

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(0.09F, 0.03F, 0.67F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.195F, 0.11F, 0.06F, 0.8F, 0.8F, 0.8F));
    }
}
