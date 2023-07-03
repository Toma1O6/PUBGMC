package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGroza;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderGroza extends WeaponRenderer {

    final ModelGroza model = new ModelGroza();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.345F, -0.385F, 0.38F, 1F, 1F, 1.7F, 10F, -35F, 0F),
                IRenderConfig.positioned(0.15F, -0.18F, 0.095F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.15F, 0.42F, -0.06F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0F, 0.09F, 0.81F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.1F, 0.34F, -0.07F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1F, 0.35F, -0.15F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.2F, 0.49F, -0.08F, 0.6F, 0.6F, 0.6F));
    }

    @Override
    public String getResourcePrefix() {
        return "groza";
    }
}
