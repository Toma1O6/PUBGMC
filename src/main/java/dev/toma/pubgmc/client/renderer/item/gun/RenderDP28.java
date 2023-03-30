package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelDP28;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderDP28 extends WeaponRenderer {

    final ModelDP28 model = new ModelDP28();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.5F, -0.58F, -0.3F, 20F, -20F, 0F),
                IRenderConfig.rotated(0.1F, -0.2F, 0.3F, 0F, -5F, 0F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, -0.02F, 0.2F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.15F, 0.17F, 0.32F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0F, -0.05F, 0.33F, 1F, 1F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.15F, 0.17F, 0.3F, 0.7F, 0.7F, 0.7F));

    }

    @Override
    public String getResourcePrefix() {
        return "dp28";
    }
}
