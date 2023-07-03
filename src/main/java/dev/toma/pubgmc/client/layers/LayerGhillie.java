package dev.toma.pubgmc.client.layers;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.GhillieSuit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class LayerGhillie<E extends EntityLivingBase> implements LayerRenderer<E> {

    public static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/ghillie_main.png");
    public static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/ghillie_overlay.png");

    protected final RenderLivingBase<E> renderLivingBase;
    protected final Function<E, SpecialInventoryProvider> inventoryProvider;
    protected final ModelBiped baseLayer;
    protected final ModelBiped overlay;

    public LayerGhillie(RenderLivingBase<E> renderLivingBase, Function<E, SpecialInventoryProvider> provider) {
        this.renderLivingBase = renderLivingBase;
        this.inventoryProvider = provider;
        this.baseLayer = new ModelBiped(1.2F);
        this.overlay = new ModelBiped(1.4F);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    @Override
    public void doRenderLayer(E entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        SpecialInventoryProvider inventory = inventoryProvider.apply(entitylivingbaseIn);
        if (inventory == null)
            return;
        ItemStack stack = inventory.getSpecialItemFromSlot(SpecialEquipmentSlot.GHILLIE);
        renderGhillie(stack, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }

    private void renderGhillie(ItemStack stack, EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!stack.isEmpty() && stack.getItem() instanceof GhillieSuit) {
            GhillieSuit ghillieSuit = (GhillieSuit) stack.getItem();
            this.baseLayer.setModelAttributes(this.renderLivingBase.getMainModel());
            this.overlay.setModelAttributes(this.renderLivingBase.getMainModel());
            this.baseLayer.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.overlay.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            int color = ghillieSuit.getColor(stack);
            float red = (color >> 16 & 255) / 255.0F;
            float green = (color >> 8 & 255) / 255.0F;
            float blue = (color & 255) / 255.0F;
            this.renderLivingBase.bindTexture(TEXTURE_MAIN);
            GlStateManager.color(red, green, blue);
            this.baseLayer.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.renderLivingBase.bindTexture(TEXTURE_OVERLAY);
            this.overlay.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.color(1f, 1f, 1f);
        }
    }
}
