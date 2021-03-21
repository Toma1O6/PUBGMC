package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelMK14;
import dev.toma.pubgmc.client.models.weapons.ModelWin94;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderWincherster94 extends WeaponRenderer {

    final ModelWin94 model = new ModelWin94();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.38F, -0.5F, 0.235F, 1F, 1F, 1.7F, 10F, -35F, 0F),
                IRenderConfig.positioned(0.2F, -0.2F, 0.4F)
        );
    }

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {
        GlStateManager.translate(0.001, -0.01, 0.0);
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {

    }
}
