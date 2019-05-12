package com.toma.pubgmc.client.renderer;

import javax.annotation.Nullable;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelParachute;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderParachute extends Render<EntityParachute>
{
	private final ModelParachute chute = new ModelParachute();
	private static final ResourceLocation[] TEXTURE = {
			
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_white.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_orange.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_magenta.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_light_blue.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_yellow.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_lime.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_pink.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_gray.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_light_gray.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_cyan.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_purple.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_blue.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_brown.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_green.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_red.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/chutes/parachute_black.png")
	};
	
	public RenderParachute(RenderManager manager)
	{
		super(manager);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityParachute entity)
	{
		return null;
	}
	
	@Override
	public void doRender(EntityParachute entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if(entity.isBeingRidden())
		{
			int color = entity.getColor();
			
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.rotate(180, 1, 0, 0);
				GlStateManager.scale(0.06f, 0.06f, 0.06f);
				GlStateManager.translate(0, -60, 0);
				float f0 = PUBGMCUtil.interpolate(entity.prevRotationYaw, entityYaw, partialTicks);
				float f1 = PUBGMCUtil.interpolate(entity.prevRotationPitch, entity.rotationPitch, partialTicks);
				GlStateManager.rotate(f0, 0, 1, 0);
				GlStateManager.rotate(f1, 1, 0, 0);
				GlStateManager.translate(0, 0, entity.rotationPitch * -0.4f);
				
				bindTexture(color);
				chute.render();
			}
			GlStateManager.popMatrix();
		}
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	private void bindTexture(int color)
	{
		if(color != -1)
		{
			bindTexture(TEXTURE[color]);
		}
		else bindTexture(TEXTURE[0]);
	}
}
