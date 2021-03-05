package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelTommyGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderTommyGun extends WeaponRenderer {

    final ModelTommyGun model = new ModelTommyGun();

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
