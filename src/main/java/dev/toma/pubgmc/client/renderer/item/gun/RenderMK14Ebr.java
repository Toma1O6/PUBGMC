package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMK14;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderMK14Ebr extends WeaponRenderer {

    final ModelMK14 model = new ModelMK14();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.33F, -0.5F, -0.065F, 1F, 1F, 1.7F, 10F, -35F, 0F),
                IRenderConfig.positioned(0.2F, -0.2F, 0.4F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.075F, 0.16F, 0F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(-0.0825F, -0.05F, -0.27F, 1.2F, 1.2F, 1.2F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.positionedScaled(-0.08F, 0.07F, -0.2F, 1.2F, 1.2F, 1.2F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0.02F, 0.08F, -0.02F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0.02F, 0.07F, -0.03F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.positionedScaled(-0.08F, 0.04F, -0.2F, 1.2F, 1.2F, 1.2F));
        registerRenderConfig(IRenderConfig.positionedScaled(-0.18F, -0.16F, -0.4F, 1.4F, 1.4F, 1.4F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
    }

    @Override
    public String getResourcePrefix() {
        return "mk14ebr";
    }
}
