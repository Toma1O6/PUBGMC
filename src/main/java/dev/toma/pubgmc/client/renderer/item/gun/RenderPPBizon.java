package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelPP19Bizon;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderPPBizon extends WeaponRenderer {

    final ModelPP19Bizon model = new ModelPP19Bizon();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.3F, -0.71F, -0.8F, 10F, -30F, 0F),
                IRenderConfig.positioned(0.1F, -0.3F, 0.2F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(0F, -0.01F, -0.2F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(-0.055F, -0.01F, 0.1F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positioned(-0.005F, 0.08F, 0.05F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(-0.005F, 0.08F, 0.06F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(-0.01F, 0.08F, 0.1F));
    }
}
