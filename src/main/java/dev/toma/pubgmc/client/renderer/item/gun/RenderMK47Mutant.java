package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMK47Mutant;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMK47Mutant extends WeaponRenderer {

    final ModelMK47Mutant model = new ModelMK47Mutant();

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
