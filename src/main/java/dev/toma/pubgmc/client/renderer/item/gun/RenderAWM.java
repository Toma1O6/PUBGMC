package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelAWM;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderAWM extends WeaponRenderer {

    final ModelAWM model = new ModelAWM();

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
