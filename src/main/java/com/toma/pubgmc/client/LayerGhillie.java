package com.toma.pubgmc.client;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class LayerGhillie implements LayerRenderer<EntityLivingBase>
{
	private final RenderPlayer playerRenderer;
	private static final ResourceLocation GHILLIE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/armor/ghillie_suit_layer_1.png");
	
	public LayerGhillie(RenderPlayer playerRender)
	{
		this.playerRenderer = playerRender;
	}
	
	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}
	
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		if(entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == PMCItems.GHILLIE_SUIT)
		{
			this.playerRenderer.bindTexture(GHILLIE);
			GlStateManager.pushMatrix();
			{
				this.playerRenderer.getMainModel().bipedHead.showModel = false;
			}
			GlStateManager.popMatrix();
		}
	}
}
