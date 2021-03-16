package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMini14;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderMini14 extends WeaponRenderer {

    final ModelMini14 model = new ModelMini14();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.translated(0F, 0.04F, -0.5F), PMCItems.SILENCER_AR, PMCItems.SILENCER_SNIPER);
        registerRenderConfig(PMCItems.HOLOGRAPHIC, IRenderConfig.translated(0F, 0.01F, -0.53F));
        registerRenderConfig(PMCItems.SCOPE15X, IRenderConfig.translated(0F, 0.09F, -0.6F));
        registerRenderConfig(PMCItems.SCOPE8X, IRenderConfig.translated(0F, 0.12F, -0.6F));
        registerRenderConfig(PMCItems.SCOPE2X, IRenderConfig.translatedScaled(0.05F, 0.11F, -0.55F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SCOPE4X, IRenderConfig.translated(0F, 0.04F, -0.56F));
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.translated(0F, 0.05F, -0.56F));
    }
}
