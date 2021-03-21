package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelBerylM762;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderBerylM762 extends WeaponRenderer {

    final ModelBerylM762 model = new ModelBerylM762();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.425F, -0.555F, 0.05F, 1F, 1F, 1.4F, 20F, -30F, 0F),
                IRenderConfig.positioned(0.1F, -0.23F, 0.3F)
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
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.05F, 0.14F, 0.3F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_AR, IRenderConfig.positioned(0F, 0.06F, 0.2F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.positioned(0F, 0.12F, 0.3F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positioned(0F, 0.1F, 0.26F));
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positioned(0F, 0.06F, 0.32F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positioned(0F, 0.09F, 0.2F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.15F, 0.3F, 0.34F, 0.7F, 0.7F, 0.7F));

    }
}
