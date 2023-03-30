package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelG36C;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderG36C extends WeaponRenderer {

    final ModelG36C model = new ModelG36C();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.3F, -0.405F, 0.28F, 1F, 1F, 2.4F, 0F, -50F, 0F),
                IRenderConfig.positioned(0.2F, -0.28F, 0.3F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.12F, 0.29F, 0.16F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0.02F, 0.06F, 0.655F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.positioned(0.02F, 0.14F, 0.24F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positioned(0.02F, 0.11F, 0.2F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.07F, 0.2F, 0.16F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.07F, 0.15F, 0.1F, 0.9F, 1F, 1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.17F, 0.36F, 0.2F, 0.7F, 0.7F, 0.7F));
    }

    @Override
    public String getResourcePrefix() {
        return "g36c";
    }
}
