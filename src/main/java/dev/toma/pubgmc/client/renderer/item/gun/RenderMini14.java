package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMini14;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderMini14 extends WeaponRenderer {

    final ModelMini14 model = new ModelMini14();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.43F, -0.45F, -0.175F, 1F, 1F, 1.7F, 10F, -30F, 0F),
                IRenderConfig.positioned(0.2F, -0.2F, 0.4F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.19F, -0.1F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(0F, 0.05F, -0.26F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positioned(0F, 0.14F, -0.17F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1F, 0.18F, 0F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.1F, 0.17F, 0.01F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positioned(0F, 0.13F, -0.2F));
        registerRenderConfig(IRenderConfig.positioned(0F, 0.04F, -0.5F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
    }

    @Override
    public String getResourcePrefix() {
        return "mini14";
    }
}
