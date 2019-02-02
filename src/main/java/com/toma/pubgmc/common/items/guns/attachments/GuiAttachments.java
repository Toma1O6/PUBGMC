package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.ImageUtil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiAttachments extends GuiContainer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/attachmentInv.png");
	private InventoryAttachments inv;
	
	public GuiAttachments(InventoryPlayer inventory)
	{
		super(new ContainerAttachments(inventory));
		ContainerAttachments cont = new ContainerAttachments(inventory);
		this.inv = cont.getAttachmentInventory();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		for(FieldType type : FieldType.values())
		{
			fontRenderer.drawStringWithShadow(type.getName(), type.getX(), type.getY(), 0xFFFFFF);
		}
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		mc.renderEngine.bindTexture(TEXTURE);
		
		/*for(int i = 1; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.isEmpty())
			{
				for(FieldType type : FieldType.values())
				{
					ImageUtil.drawCustomSizedImage(mc, type.getIcon(), 0, 0, 0, 0, true);
				}
			}
		}*/
		
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
		BARREL(12, 20),
		GRIP(41, 69),
		MAGAZINE(70, 69),
		STOCK(118, 20),
		SCOPE(73, 2),
		DETACH(138, 73);
		
		private int x,y;
		private static final ResourceLocation[] ICONS = 
		{
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/barrel.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/grip.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/magazine.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/stock.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope.png"),
			new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/detach.png")
		};
		
		FieldType(int x, int y)
		{
			this.x = x;
			this.y = y;
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
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
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
