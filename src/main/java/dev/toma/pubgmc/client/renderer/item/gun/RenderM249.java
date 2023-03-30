package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelM249;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderM249 extends WeaponRenderer {

    final ModelM249 model = new ModelM249();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.6F, -0.6F, -0.3F, 20F, -20F, 0F),
                IRenderConfig.rotated(0.1F, -0.2F, 0.3F, 0F, -5F, 0F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, 0.07F, 0.23F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.1F, 0.2F, 0.3F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, 0.05F, 0.27F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.28F, 0.25F, 0.8F, 0.7F, 0.9F));
    }

    @Override
    public String getResourcePrefix() {
        return "m249";
    }
}
