package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelAUG;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderAUG extends WeaponRenderer {

    final ModelAUG model = new ModelAUG();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.45F, -0.335F, -0.4F, 0F, -30F, 0F),
                IRenderConfig.positioned(0.1F, -0.28F, 0F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, -0.05, 0.0);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.05F, 0.16F, -0.03F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0F, 0.04F, 0.65F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.positionedScaled(0F, 0.1F, 0.04F, 1F, 1F, 0.7F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positionedScaled(0.1F, 0.17F, 0.09F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, 0.07F, 0F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1F, 0.09F, 0F, 0.8F, 1F, 1F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, 0.23F, 0.02F, 0.8F, 0.8F, 0.8F));

    }
}
