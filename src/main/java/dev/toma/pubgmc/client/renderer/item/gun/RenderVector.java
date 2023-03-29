package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelVector;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderVector extends WeaponRenderer {

    final ModelVector model = new ModelVector();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.3F, -0.59F, -0.5F, 10F, -40F, 0F),
                IRenderConfig.positioned(0.1F, -0.3F, 0.2F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(-0.05F, -0.12F, 0.1F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(-0.05F, -0.1F, 0F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(0F, -0.09F, 0.44F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, -0.04F, 0.13F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positioned(0F, -0.05F, 0.06F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.05F, 0.03F, 0.09F, 0.9F, 0.9F, 0.9F));
    }

    @Override
    public String getResourcePrefix() {
        return "vector";
    }
}
