package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelDeagle;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderDeagle extends WeaponRenderer {

    final ModelDeagle model = new ModelDeagle();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.58F, -0.22F, -0F, 0F, -20F, 0F),
                IRenderConfig.positioned(0.18F, -0.15F, -0F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        if(transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.translate(0, 0, -0.4);
        }
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        //registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.155F, 0.28F, 0.3F, 0.7F, 0.7F, 0.7F));
    }

    @Override
    public String getResourcePrefix() {
        return "deagle";
    }
}
