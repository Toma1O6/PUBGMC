package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelR45;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderR45 extends WeaponRenderer {

    final ModelR45 model = new ModelR45();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotated(0.58F, -0.22F, -0.2F, 0F, -20F, 0F),
                IRenderConfig.positioned(0.18F, -0.15F, -0.2F)
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
        //registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.095F, 0.28F, 0.01F, 0.8F, 0.8F, 0.8F));
    }

    @Override
    public String getResourcePrefix() {
        return "r45";
    }
}
