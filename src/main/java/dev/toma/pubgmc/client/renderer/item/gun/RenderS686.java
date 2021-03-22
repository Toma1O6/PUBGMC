package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelS686;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderS686 extends WeaponRenderer {

    final ModelS686 model = new ModelS686();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.5F, -0.3F, -0.1F, 1F, 1F, 1.1F, 10F, -30F, 0F),
                IRenderConfig.positioned(0.15F, -0.1F, 0.53F)
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
