package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP18C;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderP18C extends WeaponRenderer {

    final ModelP18C model = new ModelP18C();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(1.055, 0.3, 0.7);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.rotatedScaled(0.93F, -0.1F, 0.7F, 0.8F, 0.8F, 0.8F, 0F, 180F, 0F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.rotatedScaled(1.03F, -0.17F, -0.05F, 1F, 1F, 1F, 0F, 180F, 0F));
    }
}
