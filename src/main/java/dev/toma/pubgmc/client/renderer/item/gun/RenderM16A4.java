package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelM16A4;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderM16A4 extends WeaponRenderer {

    final ModelM16A4 model = new ModelM16A4();

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
