package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelKar98K;
import dev.toma.pubgmc.client.models.weapons.ModelWin94;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderKar98k extends WeaponRenderer {

    final ModelKar98K model = new ModelKar98K();

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
