package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelDeagle;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderDeagle extends WeaponRenderer {

    final ModelDeagle model = new ModelDeagle();

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
