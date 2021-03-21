package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelTommyGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderTommyGun extends WeaponRenderer {

    final ModelTommyGun model = new ModelTommyGun();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.41F, -0.265F, 0.01F, 1F, 1F, 1.9F, 0F, -33F, 0F),
                IRenderConfig.positioned(0.15F, -0.25F, 0.1F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.0, 0.05, -0.1);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(0F, -0.01F, 0.35F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positionedScaled(0.05F, 0.1F, 0F, 0.9F, 0.9F, 0.9F));

    }
}
