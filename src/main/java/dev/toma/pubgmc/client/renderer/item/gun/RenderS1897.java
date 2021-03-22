package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelS1897;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderS1897 extends WeaponRenderer {

    final ModelS1897 model = new ModelS1897();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.43F, -0.28F, 0.04F, 1F, 1F, 1.8F, 0F, -30F, 0F),
                IRenderConfig.positioned(0.2F, -0.2F, 0.4F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {

    }
}
