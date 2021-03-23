package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelQBZ;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderQBZ extends WeaponRenderer {

    final ModelQBZ model = new ModelQBZ();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.27F, -0.405F, 0.35F, 1F, 1F, 2.4F, 0F, -40F, 0F),
                IRenderConfig.positioned(0.2F, -0.28F, 0.3F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.003, 0.365, 0.4);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positioned(0F, -0.21F, -0.5F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0F, -0.45F, -0.36F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.positioned(0F, -0.36F, -0.53F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positioned(0F, -0.39F, -0.5F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, -0.23F, -0.44F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, -0.2F, -0.5F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.1F, -0.06F, -0.41F, 0.8F, 0.8F, 0.8F));
    }

    @Override
    public String getResourcePrefix() {
        return "qbz";
    }
}
