package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP18C;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderP18C extends WeaponRenderer {

    final ModelP18C model = new ModelP18C();

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
