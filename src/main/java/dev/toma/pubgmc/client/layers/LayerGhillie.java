package dev.toma.pubgmc.client.layers;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.items.armor.ItemGhillie;
import dev.toma.pubgmc.init.PMCRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LayerGhillie implements LayerRenderer<EntityLivingBase> {

    public static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/ghillie_main.png");
    public static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/ghillie_overlay.png");

    protected final RenderLivingBase<?> renderLivingBase;
    protected final ModelBiped baseLayer;
    protected final ModelBiped overlay;

    public LayerGhillie(RenderLivingBase<?> renderLivingBase) {
        this.renderLivingBase = renderLivingBase;
        this.baseLayer = new ModelBiped(1.2F);
        this.overlay = new ModelBiped(1.4F);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        if(!stack.isEmpty() && stack.getItem() == PMCRegistry.PMCItems.GHILLIE_SUIT) {
            this.baseLayer.setModelAttributes(this.renderLivingBase.getMainModel());
            this.overlay.setModelAttributes(this.renderLivingBase.getMainModel());
            this.baseLayer.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.overlay.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            int color = stack.hasTagCompound() && stack.getTagCompound().hasKey("ghillieColor") ? stack.getTagCompound().getInteger("ghillieColor") : ItemGhillie.DEFAULT_COLOR;
            float red = (color >> 16 & 255) / 255.0F;
            float green = (color >> 8 & 255) / 255.0F;
            float blue = (color & 255) / 255.0F;
            this.renderLivingBase.bindTexture(TEXTURE_MAIN);
            GlStateManager.color(red, green, blue);
            //this.copyModelAngles();
            this.baseLayer.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.renderLivingBase.bindTexture(TEXTURE_OVERLAY);
            this.overlay.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.color(1f, 1f, 1f);
        }
    }

    public void copyModelAngles() {
        ModelBase model = this.renderLivingBase.getMainModel();
        if(!(model instanceof ModelPlayer)) return;
        ModelPlayer modelBiped = (ModelPlayer) model;
        this.copyRotations(baseLayer.bipedRightArm, modelBiped.bipedRightArm);
        this.copyRotations(baseLayer.bipedLeftArm, modelBiped.bipedLeftArm);
        this.copyRotations(baseLayer.bipedRightLeg, modelBiped.bipedRightLeg);
        this.copyRotations(baseLayer.bipedLeftLeg, modelBiped.bipedLeftLeg);
    }

    public void copyRotations(ModelRenderer model, ModelRenderer from) {
        model.rotateAngleX = from.rotateAngleX;
        model.rotateAngleY = from.rotateAngleY;
        model.rotateAngleZ = from.rotateAngleZ;
    }
}
