package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelSawedOff;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderSawedOff extends WeaponRenderer {

    final ModelSawedOff model = new ModelSawedOff();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.5F, -0.22F, -0.2F, 0F, -30F, 0F),
                IRenderConfig.positioned(0.17F, -0.1F, 0.54F)
        );
    }

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
