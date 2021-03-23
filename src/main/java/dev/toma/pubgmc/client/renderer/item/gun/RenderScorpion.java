package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelScorpion;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderScorpion extends WeaponRenderer {

    final ModelScorpion model = new ModelScorpion();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.48F, -0.23F, 0.1F, 1F, 1F, 1.9F, 0F, -26F, 0F),
                IRenderConfig.positioned(0.15F, -0.18F, 0.03F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        if(transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.translate(0, 0, -0.2);
        }
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(PMCItems.RED_DOT, IRenderConfig.positionedScaled(0.05F, 0.14F, 0F, 0.9F, 0.9F, 0.9F));
        registerRenderConfig(PMCItems.SILENCER_SMG, IRenderConfig.positionedScaled(-0.1F, -0.01F, 0.7F, 1.2F, 1.2F, 1.2F));
        registerRenderConfig(PMCItems.GRIP_VERTICAL, IRenderConfig.positioned(0F, 0.1F, 0.13F));
    }

    @Override
    public String getResourcePrefix() {
        return "scorpion";
    }
}
