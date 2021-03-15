package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelTommyGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderTommyGun extends WeaponRenderer {

    final ModelTommyGun model = new ModelTommyGun();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(0F, -0.01F, 0.35F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translatedScaled(0.05F, 0.1F, 0F, 0.9F, 0.9F, 0.9F));

    }
}
