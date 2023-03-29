package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelP1911;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderP1911 extends WeaponRenderer {

    final ModelP1911 model = new ModelP1911();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.6F, -0.3F, -0.2F, 0F, -20F, 0F),
                IRenderConfig.positioned(0.2F, -0.23F, -0.2F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.translate(0, 0, -0.4);
        }
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.1025F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positioned(0F, 0.08F, 0.67F));
    }

    @Override
    public String getResourcePrefix() {
        return "p1911";
    }
}
