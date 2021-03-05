package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelFlareGun;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderFlareGun extends WeaponRenderer {

    final ModelFlareGun model = new ModelFlareGun();

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
