package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelS12K;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderS12K extends WeaponRenderer {

    final ModelS12K model = new ModelS12K();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.36F, -0.43F, -0.2F, 1F, 1F, 1.2F, 10F, -30F, 0F),
                IRenderConfig.positioned(0.1F, -0.2F, 0.4F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.positionedScaled(0.075F, 0.1F, 0.27F, 0.8F, 0.9F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.positionedScaled(0.075F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.positionedScaled(0.175F, 0.31F, 0.32F, 0.6F, 0.6F, 0.6F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.125F, 0.18F, 0.27F, 0.7F, 0.8F, 0.8F));
    }

    @Override
    public String getResourcePrefix() {
        return "s12k";
    }
}
