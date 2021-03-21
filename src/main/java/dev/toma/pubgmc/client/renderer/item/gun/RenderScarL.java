package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.models.weapons.ModelScarL;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class RenderScarL extends WeaponRenderer {

    final ModelScarL model = new ModelScarL();

    @Override
    public void preRender(ItemCameraTransforms.TransformType transformType) {

    }

    @Override
    public ModelGun getWeaponModel() {
        return model;
    }

    @Override
    public void registerAttachmentRenders() {
        registerRenderConfig(IRenderConfig.empty(), PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC, PMCItems.SCOPE2X, PMCItems.SCOPE4X, PMCItems.GRIP_ANGLED, PMCItems.GRIP_VERTICAL, PMCItems.SILENCER_AR);
    }
}
