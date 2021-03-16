package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelSKS;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderSKS extends WeaponRenderer {

    final ModelSKS model = new ModelSKS();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.translated(0F, -0.01F, 0.21F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translatedScaled(0.1F, 0.15F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.translatedScaled(0.1F, 0.24F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.translatedScaled(0.1F, 0.23F, 0.21F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.GRIP_ANGLED, IRenderConfig.translatedScaled(0.1F, 0.17F, 0.25F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.15F, 0.24F, 0.2F, 0.7F, 0.7F, 0.7F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translatedScaled(0.1F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translatedScaled(0.1F, 0.17F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.translatedScaled(0.1F, 0.15F, 0.23F, 0.8F, 0.8F, 0.8F));

    }
}
