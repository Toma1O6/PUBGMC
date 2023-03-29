package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelAKM;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderAKM extends WeaponRenderer {

    final ModelAKM model = new ModelAKM();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.425F, -0.505F, -0.05F, 1F, 1F, 1.4F, 20F, -30F, 0F),
                IRenderConfig.positioned(0.1F, -0.23F, 0.3F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.05F, 0.15F, 0.11F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0F, 0.075F, 0.08F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, 0.06F, 0.15F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, 0.08F, 0.1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.22F, 0.2F, 0.8F, 0.8F, 0.8F));
    }

    @Override
    public String getResourcePrefix() {
        return "akm";
    }
}
