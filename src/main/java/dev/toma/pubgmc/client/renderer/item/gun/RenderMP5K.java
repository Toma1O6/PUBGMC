package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMP5K;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMP5K extends WeaponRenderer {

    final ModelMP5K model = new ModelMP5K();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.24F, -0.315F, 0.1F, 1F, 1F, 1.7F, 5F, -40F, 0F),
                IRenderConfig.positioned(0.15F, -0.3F, 0.2F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0, 0.03, -0.3);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(-0.01F, 0.06F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(-0.01F, 0.07F, 0.27F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.225F, 0.3F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.04F, 0.15F, 0.24F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(-0.01F, 0.09F, 0.2F));
    }
}
