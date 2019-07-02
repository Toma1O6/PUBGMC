package com.toma.pubgmc.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelGhillie extends ModelBiped
{
	public ModelRenderer head;
	public ModelRenderer r_arm;
	public ModelRenderer l_arm;
	public ModelRenderer body;
	public ModelRenderer r_leg;
	public ModelRenderer l_leg;
	
    public ModelGhillie()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 90, 32);
        //this.head.setRotationPoint(-4.0F, -8.0F, -4.0F);
        this.head.addBox(-1.0F, -0.5F, -0.5F, 10, 9, 9, 0.0F);
        this.l_arm = new ModelRenderer(this, 0, 32);
        //this.l_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.l_arm.addBox(-0.5F, -0.5F, -0.5F, 5, 12, 5, 0.0F);
        this.l_leg = new ModelRenderer(this, 0, 32);
        //this.l_leg.setRotationPoint(-0.1F, 12.0F, -2.0F);
        this.l_leg.addBox(-0.5F, -0.5F, -0.5F, 5, 11, 5, 0.0F);
        this.r_arm = new ModelRenderer(this, 0, 32);
        //this.r_arm.setRotationPoint(-8.0F, 0.0F, -2.0F);
        this.r_arm.addBox(-0.5F, -0.5F, -0.5F, 5, 12, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 32);
        //this.body.setRotationPoint(-4.0F, 0.0F, -2.0F);
        this.body.addBox(-0.5F, -0.5F, -0.5F, 9, 13, 5, 0.0F);
        this.r_leg = new ModelRenderer(this, 0, 32);
        //this.r_leg.setRotationPoint(-3.9F, 12.0F, -2.0F);
        this.r_leg.addBox(-0.5F, -0.5F, -0.5F, 5, 11, 5, 0.0F);
        bipedHead.addChild(head);
        bipedBody.addChild(body);
        bipedLeftArm.addChild(l_arm);
        bipedLeftLeg.addChild(l_leg);
        bipedRightArm.addChild(r_arm);
        bipedRightLeg.addChild(r_leg);
    }
    
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    		float headPitch, float scale) {
    	// TODO Auto-generated method stub
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
    
    private float interpolate(float current, float prev, float partial) {
    	return prev + partial * (current - prev);
    }
    
    private static void setRotationAngle(ModelRenderer model, float x, float y, float z) {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
}
