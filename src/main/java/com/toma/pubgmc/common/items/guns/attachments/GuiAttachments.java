package com.toma.pubgmc.common.items.guns.attachments;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.ImageUtil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class GuiAttachments extends GuiContainer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/attachmentInv.png");
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public GuiAttachments(InventoryPlayer inventory, EntityPlayer player)
	{
		super(new ContainerAttachments(inventory, player));
		xSize = 176;
		ySize = 170;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		for(FieldType type : FieldType.values())
		{
			fontRenderer.drawStringWithShadow(type.getName(), type.getTextX(), type.getTextY(), 0xFFFFFF);
			
			if(ITEMS.size() > 6)
			{
				switch(type.ordinal())
				{
					case 0:
					{
						if(ITEMS.get(2) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					case 1:
					{
						if(ITEMS.get(3) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					case 2:
					{
						if(ITEMS.get(4) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					case 3:
					{
						if(ITEMS.get(5) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					case 4:
					{
						if(ITEMS.get(1) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					case 5:
					{
						if(ITEMS.get(6) == Items.AIR) ImageUtil.drawCustomSizedImage(mc, type.getIcon(), type.getImgX(), type.getImgY(), 16, 16, true);
						break;
					}
					
					default: break;
				}
			}
		}
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		mc.renderEngine.bindTexture(TEXTURE);
		
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private enum FieldType
	{
		BARREL(12, 20, 20, 31),
		GRIP(41, 69, 42, 50),
		MAGAZINE(70, 69, 80, 50),
		STOCK(118, 20, 124, 31),
		SCOPE(73, 2, 80, 12),
		DETACH(138, 73, 152, 55);
		
		private int x,y,x1,y1;
		private static final ResourceLocation[] ICONS = 
		{
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/barrel.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/grip.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/magazine.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/stock.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/detach.png")
		};
		
		FieldType(int x, int y, int slotX, int slotY)
		{
			this.x = x;
			this.y = y;
			this.x1 = slotX;
			this.y1 = slotY;
		}
		
		public String getName()
		{
			switch(ordinal())
			{
				case 0: return "Barrel";
				case 1: return "Grip";
				case 2: return "Magazine";
				case 3: return "Stock";
				case 4: return "Scope";
				case 5: return "Detach";
				default: return "";
			}
		}
		
		public int getTextX()
		{
			return x;
		}
		
		public int getTextY()
		{
			return y;
		}
		
		public int getImgX()
		{
			return x1;
		}
		
		public int getImgY()
		{
			return y1;
		}
		
		public void setXY(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public ResourceLocation getIcon()
		{
			return ICONS[ordinal()];
		}
	}
}
