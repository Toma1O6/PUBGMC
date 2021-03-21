package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelM16A4;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderM16A4 extends WeaponRenderer {

    final ModelM16A4 model = new ModelM16A4();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.3F, -0.315F, 0.35F, 1F, 1F, 2.4F, 0F, -40F, 0F),
                IRenderConfig.positioned(0.2F, -0.3F, 0.3F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.44, 0.23, 0.0);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.rotatedScaled(0.11F, -0.02F, 0.44F, 1F, 1F, 1F, 0F, 90F, 0F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.rotatedScaled(-0.03F, -0.2F, 0.44F, 1F, 1F, 1F, 0F, 90F, 0F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.rotatedScaled(0.11F, -0.03F, 0.44F, 1F, 1F, 1F, 0F, 90F, 0F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.rotatedScaled(0.04F, -0.02F, 0.44F, 1F, 1F, 1F, 0F, 90F, 0F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.rotatedScaled(0.16F, 0.18F, 0.29F, 0.7F, 0.7F, 0.7F, 0F, 90F, 0F));
    }
}
