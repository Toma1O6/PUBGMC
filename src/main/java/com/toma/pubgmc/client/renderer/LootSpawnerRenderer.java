package com.toma.pubgmc.client.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.toma.pubgmc.client.models.weapons.ModelP92;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.heal.ItemAdrenalineSyringe;
import com.toma.pubgmc.common.items.heal.ItemBandage;
import com.toma.pubgmc.common.items.heal.ItemEnergyDrink;
import com.toma.pubgmc.common.items.heal.ItemFirstAidKit;
import com.toma.pubgmc.common.items.heal.ItemMedkit;
import com.toma.pubgmc.common.items.heal.ItemPainkiller;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.block.BlockPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.FastTESR;
import scala.collection.parallel.ParIterableLike.Min;

public class LootSpawnerRenderer extends TileEntitySpecialRenderer<TileEntityLootSpawner>
{
	private EntityItem entityItem = new EntityItem(null, 0D, 0D, 0D);
	private RenderEntityItem itemRenderer;
	
	//TODO: some optimalizations
	@Override
	public void render(TileEntityLootSpawner te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		if(te == null || te.isInvalid())
		{
			return;
		}
		
		//small correction
		y = y - 0.1;
		World world = this.getWorld();
		renderItem(te, x + 0.4, y, z - 0.25, 0, partialTicks);
		renderItem(te, x - 0.2, y, z - 0.3, 1, partialTicks);
		renderItem(te, x + 0.3, y, z + 0.1, 2, partialTicks);
		renderItem(te, x, y, z - 0.35, 3, partialTicks);
		renderItem(te, x + 0.3, y, z - 0.6, 4, partialTicks);
		renderItem(te, x - 0.3, y, z - 0.6, 5, partialTicks);
		renderItem(te, x - 0.3, y, z + 0.1, 6, partialTicks);
		renderItem(te, x, y, z - 0.6, 7, partialTicks);
		renderItem(te, x, y, z, 8, partialTicks);
	}
	
	private void renderItem(TileEntityLootSpawner te, double x, double y, double z, int slot, float ticks)
	{
        boolean is3D = te.getStackInSlot(slot).getItem() instanceof GunBase;
        boolean drinkable = te.getStackInSlot(slot).getItem() instanceof ItemEnergyDrink || te.getStackInSlot(slot).getItem() instanceof ItemPainkiller;
        boolean firstAid = te.getStackInSlot(slot).getItem() instanceof ItemFirstAidKit || te.getStackInSlot(slot).getItem() instanceof ItemBandage;
        boolean medkit = te.getStackInSlot(slot).getItem() == PMCItems.MEDKIT;
		
		if(ConfigHandler.lootRenderType == 2)
		{
			if(te.isInvalid() || te == null)
			{
				return;
			}
			
			if(te.getStackInSlot(slot).isEmpty())
			{
				return;
			}
			
			if(entityItem == null || entityItem.getItem().getItem() != te.getStackInSlot(slot).getItem())
			{
				entityItem.setItem(te.getStackInSlot(slot));
			}
	        
	        if(is3D)
	        {
	        	weaponRenderer(slot, te, x, y, z);
	        }
	        
	        else if(drinkable)
	        {
	        	boosterRenderer(slot, te, x, y, z);
	        }
	        
	        else if(firstAid)
	        {
	        	firstAidKitRenderer(slot, te, x, y, z);
	        }
	        
	        else if(medkit)
	        {
	        	medkitRenderer(slot, te, x, y, z);
	        }
	        
	        else
	        {
	            GlStateManager.pushMatrix();
	            GL11.glPushMatrix();
	            GL11.glDisable(GL11.GL_LIGHTING);

	            this.entityItem.hoverStart = 0.0F;
	            GlStateManager.translate((float) x + 0.5F, (float) y + 0.05F, (float) z + 0.5F);
	            GlStateManager.rotate(180, 0, 1, 1);
	            GlStateManager.scale(0.9F, 0.9F, 0.9F);
	            
	            Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0.0D, 0.0D, 0.1D, 0.0F, 0.0F, false);
	            
	            GL11.glDisable(GL11.GL_LIGHTING);
	            GL11.glPopMatrix();
	            GlStateManager.popMatrix();
	        }
		}
		
		else if(ConfigHandler.lootRenderType == 1)
		{
			if(te.isInvalid() || te == null || te.getStackInSlot(slot).isEmpty())
			{
				return;
			}
			
			if(itemRenderer == null)
			{
				itemRenderer = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem())
				{
					@Override
					protected int getModelCount(ItemStack stack)
					{
						return 1;
					}
					
					@Override
					public boolean shouldBob()
					{
						return false;
					}
					
					@Override
					public boolean shouldSpreadItems()
					{
						return false;
					}
				};
			}
			
			GlStateManager.pushMatrix();
			{
				entityItem.setItem(te.getStackInSlot(slot));
				entityItem.hoverStart = 0f;
				GlStateManager.translate(0.5, 0, 0.75);
				
				if(firstAid)
				{
					GlStateManager.translate(0, 0.3, 0);
				}
				
				else if(drinkable || medkit)
				{
					GlStateManager.translate(0, 0.2, 0);
				}
				
				itemRenderer.doRender(entityItem, x, y, z, 0f, ticks);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void medkitRenderer(int slot, TileEntityLootSpawner te, double x, double y, double z)
	{
        GlStateManager.pushMatrix();

        GlStateManager.disableLighting();

        this.entityItem.hoverStart = 0.0F;
        GlStateManager.translate((float) x + 0.1F, (float) y + 0.1F, (float) z + 0.7F);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.rotate(45, 0, 1, 0);
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
        
        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0.0D, 0.0D, 0.1D, 0.0F, 0.0F, false);

        GlStateManager.enableLighting();

        GlStateManager.popMatrix();
	}
	
	private void boosterRenderer(int slot, TileEntityLootSpawner te, double x, double y, double z)
	{
        GlStateManager.pushMatrix();

        GlStateManager.disableLighting();

        this.entityItem.hoverStart = 0.0F;
        GlStateManager.translate((float) x + 0.1F, (float) y + 0.1F, (float) z + 0.7F);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.scale(0.9F, 0.9F, 0.9F);
        
        if(te.getStackInSlot(slot).getItem() == PMCItems.PAINKILLERS)
        {
        	GlStateManager.translate(0f, 0.11f, 0f);
        	GlStateManager.scale(1f, 1.25f, 1f);
        }
        
        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0.0D, 0.0D, 0.1D, 0.0F, 0.0F, false);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}
	
	private void firstAidKitRenderer(int slot, TileEntityLootSpawner te, double x, double y, double z)
	{
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		
		this.entityItem.hoverStart = 0f;
		
		GlStateManager.translate(x + 0.5f, y + 0.1f, z + 0.5f);
		GlStateManager.rotate(90, 0, 1, 0);
		GlStateManager.scale(0.8f, 0.8f, 0.8f);
		
		Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0d, 0d, 0.1d, 0f, 0f, false);
		
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
	
	private void weaponRenderer(int slot, TileEntityLootSpawner te, double x, double y, double z)
	{
        GlStateManager.pushMatrix();
        
        GL11.glPushMatrix();
        //Use GL11 lighting since Minecraft makes it quite dark
        GL11.glDisable(GL11.GL_LIGHTING);

        this.entityItem.hoverStart = 0.0F;
        GlStateManager.translate(x + 0.3f, y + 0.18f, z + 0.8f);
        GlStateManager.rotate(180, 1f, 1f, 0f);
        GlStateManager.scale(0.6f, 0.6f, 0.6f);
        
        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0.0D, 0.0D, 0.1D, 0.0F, 0.0F, false);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        
        GlStateManager.popMatrix();
	}
}
