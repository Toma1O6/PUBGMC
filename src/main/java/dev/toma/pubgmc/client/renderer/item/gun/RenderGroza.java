package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGroza;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderGroza extends WeaponRenderer {

    final ModelGroza model = new ModelGroza();

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
