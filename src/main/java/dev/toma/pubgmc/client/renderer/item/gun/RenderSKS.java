package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelSKS;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderSKS extends WeaponRenderer {

    final ModelSKS model = new ModelSKS();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.33F, -0.52F, 0.065F, 1F, 1F, 1.7F, 10F, -40F, 0F),
                IRenderConfig.positioned(0.2F, -0.2F, 0.4F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.positioned(0F, -0.01F, 0.21F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.1F, 0.15F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positionedScaled(0.1F, 0.24F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positionedScaled(0.1F, 0.23F, 0.21F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.positionedScaled(0.1F, 0.17F, 0.25F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.15F, 0.24F, 0.2F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.1F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positionedScaled(0.1F, 0.15F, 0.23F, 0.8F, 0.8F, 0.8F));
    }

    @Override
    public String getResourcePrefix() {
        return "sks";
    }
}
