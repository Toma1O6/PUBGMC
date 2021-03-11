package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelUmp45;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderUMP45 extends WeaponRenderer {

    final ModelUmp45 model = new ModelUmp45();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.rotate(1.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0, -0.045, -0.5);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {

    }
}
