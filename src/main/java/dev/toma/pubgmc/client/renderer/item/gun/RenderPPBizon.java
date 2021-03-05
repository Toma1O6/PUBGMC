package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelPP19Bizon;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderPPBizon extends WeaponRenderer {

    final ModelPP19Bizon model = new ModelPP19Bizon();

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
