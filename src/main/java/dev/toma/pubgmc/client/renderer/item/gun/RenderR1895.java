package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelR1895;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderR1895 extends WeaponRenderer {

    final ModelR1895 model = new ModelR1895();

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
