package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelUmp45;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderUMP45 extends WeaponRenderer {

    final ModelUmp45 model = new ModelUmp45();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.rotate(1.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0, -0.045, -0.5);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translated(0F, 0.16F, 0.6F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.05F, 0.24F, 0.6F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.translated(0F, 0.1F, 0.45F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.translated(0F, 0.125F, 0.7F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(0F, 0.16F, 0.6F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translated(0F, 0.09F, 0.43F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.1F, 0.3F, 0.7F, 0.8F, 0.8F, 0.8F));
    }
}
