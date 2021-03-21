package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMicroUzi;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMicroUzi extends WeaponRenderer {

    final ModelMicroUzi model = new ModelMicroUzi();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.26F, -0.32F, 0.2F, 1F, 1F, 2.2F, 0F, -40F, 0F),
                IRenderConfig.rotated(0.175F, -0.3F, -0.2F, 2.5F, 0F, 0F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, -0.1, 0.0);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(0F, 0.175F, 0.19F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(-0.045F, 0.19F, -0.25F, 1.1F, 1.1F, 1.1F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, 0.275F, -0.28F));

    }
}
