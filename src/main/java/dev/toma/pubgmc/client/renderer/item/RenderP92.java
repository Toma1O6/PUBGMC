package dev.toma.pubgmc.client.renderer.item;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP92;
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

    }
}
