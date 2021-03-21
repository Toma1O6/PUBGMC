package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelFlareGun;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderFlareGun extends WeaponRenderer {

    final ModelFlareGun model = new ModelFlareGun();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.6F, -0.2F, -0.1F, 0F, -20F, 0F),
                IRenderConfig.positioned(0.2F, -0.13F, 0F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        if(transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.translate(0, 0, -0.4);
        }
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {

    }
}
