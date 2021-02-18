package dev.toma.pubgmc.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;

public class ModelAIPlayer extends ModelBiped {

    public ModelAIPlayer() {
        this(0.0F, false);
    }

    public ModelAIPlayer(float modelSize, boolean useSmallTexture) {
        super(modelSize, 0.0F, 64, useSmallTexture ? 32 : 64);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.rightArmPose = ((EntityLivingBase) entityIn).getHeldItemMainhand().getItem() instanceof ItemSword ? ArmPose.ITEM : ArmPose.EMPTY;
    }
}
