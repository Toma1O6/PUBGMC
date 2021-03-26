package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP18C;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderP18C extends WeaponRenderer {

    final ModelP18C model = new ModelP18C();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.6F, -0.22F, -0.2F, 0F, -20F, 0F),
                IRenderConfig.positioned(0.2F, -0.15F, -0.2F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        if(transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.translate(0, 0, -0.4);
        }
        GlStateManager.translate(1.055, 0.3, 0.7);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        //registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.rotatedScaled(0.93F, -0.1F, 0.7F, 0.8F, 0.8F, 0.8F, 0F, 180F, 0F));
        //registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.rotatedScaled(1.03F, -0.17F, -0.05F, 1F, 1F, 1F, 0F, 180F, 0F));
    }

    @Override
    public String getResourcePrefix() {
        return "glock18";
    }
}
