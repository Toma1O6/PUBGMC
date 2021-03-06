package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelDP28;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderDP28 extends WeaponRenderer {

    final ModelDP28 model = new ModelDP28();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {

    }
}
