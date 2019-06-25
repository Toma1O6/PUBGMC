package com.toma.pubgmc.client;

import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {
	
	@SubscribeEvent
	public void renderHands(RenderSpecificHandEvent e) {
		if(e.getItemStack().getItem() instanceof GunBase) {
			ItemStack stack = e.getItemStack();
			GunBase gun = (GunBase)stack.getItem();
			GlStateManager.pushMatrix();
			{
				
			}
			GlStateManager.popMatrix();
		}
	}
	
	private static void renderHand(boolean right, GunBase gun) {
		Minecraft mc = Minecraft.getMinecraft();
		AbstractClientPlayer player = mc.player;
		mc.getTextureManager().bindTexture(player.getLocationSkin());
		RenderPlayer render = (RenderPlayer) mc.getRenderManager().<AbstractClientPlayer>getEntityRenderObject(player);
		GlStateManager.disableCull();
		{
			ModelRenderer arm = right ? render.getMainModel().bipedRightArm : render.getMainModel().bipedLeftArm;
			resetRotationAngles(arm);
			arm.render(0.625f);
		}
		GlStateManager.enableCull();
	}
	
	private static void resetRotationAngles(ModelRenderer m) {
		m.rotateAngleX = 0f;
		m.rotateAngleY = 0f;
		m.rotateAngleZ = 0f;
	}
}
