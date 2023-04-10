package dev.toma.pubgmc.client.layers;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.items.equipment.GhillieSuit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
        if (entitylivingbaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entitylivingbaseIn;
            IPlayerData data = PlayerData.get(player);
            if (data == null) {
                return;
            }
            ItemStack stack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.GHILLIE);
            renderGhillie(stack, player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        } else if (entitylivingbaseIn instanceof SpecialInventoryProvider) {
            ItemStack stack = ((SpecialInventoryProvider) entitylivingbaseIn).getSpecialItemFromSlot(SpecialEquipmentSlot.GHILLIE);
            renderGhillie(stack, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        }
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
