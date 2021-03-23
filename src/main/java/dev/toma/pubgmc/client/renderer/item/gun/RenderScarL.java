package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelScarL;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.Pair;

public class RenderScarL extends WeaponRenderer {

    final ModelScarL model = new ModelScarL();

    @Override
    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(
                IRenderConfig.rotatedScaled(0.27F, -0.345F, 0.35F, 1F, 1F, 2.4F, 0F, -40F, 0F),
                IRenderConfig.positioned(0.2F, -0.28F, 0.3F)
        );
    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.empty(), PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC, PMCItems.SCOPE2X, PMCItems.SCOPE4X, PMCItems.GRIP_ANGLED, PMCItems.GRIP_VERTICAL, PMCItems.SILENCER_AR);
    }

    @Override
    public String getResourcePrefix() {
        return "scarl";
    }
}
