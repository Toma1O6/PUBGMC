package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelAirdrop;
import com.toma.pubgmc.common.entity.EntityAirdrop;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAirdrop extends Render<EntityAirdrop>
{
	private static final ResourceLocation[] TEXTURES = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/entity/airdrop"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/entity/bigAirdrop")};
	//TODO
	private final ModelAirdrop[] models = null;
	
	public RenderAirdrop(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAirdrop entity)
	{
		return TEXTURES[getTypeID(entity)];
	}
	
	@Override
	public void doRender(EntityAirdrop entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		this.bindTexture(this.getEntityTexture(entity));
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			models[getTypeID(entity)].render();
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	private static final byte getTypeID(EntityAirdrop drop)
	{
		return drop.getType();
	}
}
