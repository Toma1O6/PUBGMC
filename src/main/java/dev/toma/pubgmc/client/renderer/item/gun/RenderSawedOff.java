package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelSawedOff;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderSawedOff extends WeaponRenderer {

    final ModelSawedOff model = new ModelSawedOff();

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
