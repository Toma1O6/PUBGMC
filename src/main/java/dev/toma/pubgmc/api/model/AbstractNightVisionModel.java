package dev.toma.pubgmc.api.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;

public abstract class AbstractNightVisionModel extends ModelBiped {

    public AbstractNightVisionModel() {
        this.bipedHeadwear.isHidden = true;
        this.bipedRightArm.isHidden = true;
        this.bipedLeftArm.isHidden = true;
        this.bipedBody.isHidden = true;
        this.bipedRightLeg.isHidden = true;
        this.bipedLeftLeg.isHidden = true;
    }

    public abstract void setupRotations(EntityLivingBase entity, boolean isNightVisionActive);
}
