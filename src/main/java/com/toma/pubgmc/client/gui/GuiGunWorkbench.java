package com.toma.pubgmc.client.gui;

import java.io.IOException;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.container.ContainerGunWorkbench;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketCraft;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.util.ICraftable;
import com.toma.pubgmc.util.ImageUtil;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiGunWorkbench extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/gun_workbench.png");
	private static final ResourceLocation INFO = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/info.png");
	private final InventoryPlayer player;
	private final TileEntityGunWorkbench tileentity;
	private int id;
	private int maxID;
	private int infoTimer;
	private boolean info = false;
	
	public GuiGunWorkbench(TileEntityGunWorkbench te, InventoryPlayer playerInv)
	{
		super(new ContainerGunWorkbench(te, playerInv));
		this.tileentity = te;
		this.player = playerInv;
		
		xSize = 223;
		ySize = 193;
	}
	
	@Override
	public void initGui()
	{	
		super.initGui();
		
		this.buttonList.add(new GuiButton(0, guiLeft + 150, guiTop + 23, 16, 20, ">"));
		this.buttonList.add(new GuiButton(1, guiLeft + 86, guiTop + 23, 16, 20, "<"));
		this.buttonList.add(new GuiButton(2, guiLeft + 106, guiTop + 23, 40, 20, "Mode"));
		this.buttonList.add(new GuiButton(3, guiLeft + 7, guiTop + 73, 53, 20, "Craft"));
		this.buttonList.add(new GuiButton(4, guiLeft + 63, guiTop + 73, 16, 20, "?"));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{	
		mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		CraftMode mode = tileentity.getCurrentCraftingMode();
		String item = new ItemStack(this.tileentity.getItemByID(id)).getDisplayName();
		
		this.fontRenderer.drawString(mode.toString() + ": " + item, 8, 7, 0x00000);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{	
		if(id > maxID)
		{
			id = maxID;
		}
		
		if(tileentity.getCurrentCraftingMode() == null)
		{
			tileentity.setCraftMode(CraftMode.Ammo);
		}
		
		if(tileentity.getCurrentCraftingMode() == CraftMode.Gun)
		{
			maxID = 35;
			RenderHelper.enableGUIStandardItemLighting();
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
			RenderHelper.disableStandardItemLighting();
		}
		
		else if(tileentity.getCurrentCraftingMode() == CraftMode.Ammo)
		{
			maxID = 6;
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
		}
		
		else if(tileentity.getCurrentCraftingMode() == CraftMode.Atachment)
		{
			maxID = 29;
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
		}
		
		else if(tileentity.getCurrentCraftingMode() == CraftMode.Clothing)
		{
			maxID = 11;
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
		}
		
		else if(tileentity.getCurrentCraftingMode() == CraftMode.Healing)
		{
			maxID = 5;
			RenderHelper.enableGUIStandardItemLighting();
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
			RenderHelper.disableStandardItemLighting();
		}
		
		else if(tileentity.getCurrentCraftingMode() == CraftMode.Throwables)
		{
			maxID = 2;
			RenderHelper.enableGUIStandardItemLighting();
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(this.tileentity.getItemByID(id)), guiLeft + 117, guiTop + 50);
			RenderHelper.disableStandardItemLighting();
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
		
		drawIngredients();
		
		if(info)
		{
			infoTimer++;
			drawInfo();
			
			if(infoTimer >= 300)
			{
				switchInfoMode();
			}
		}
		
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch(button.id)
		{
			case 0: this.nextID(); break;
			case 1: this.prevID(); break;
			case 2: tileentity.nextMode(); break;
			case 3: PacketHandler.INSTANCE.sendToServer(new PacketCraft(id, tileentity.getPos().getX(), tileentity.getPos().getY(), tileentity.getPos().getZ(), tileentity.getCurrentCraftingMode())); break;
			case 4:
			{
				infoTimer = 0;
				this.switchInfoMode();
				id = 0;
				break;
			}
		}
		
		super.actionPerformed(button);
	}
	
	private int nextID()
	{
		if(id > maxID)
		{
			id = maxID;
		}
		
		return id < maxID ? id++ : id;
	}
	
	public int getID()
	{
		return id;
	}
	
	private int prevID()
	{
		if(id > maxID)
		{
			id = maxID;
		}
		
		return id > 0 ? id-- : id;
	}
	
	private boolean switchInfoMode()
	{
		return info = !info;
	}
	
	private void drawInfo()
	{
		ImageUtil.drawCustomSizedImage(mc, INFO, guiLeft + 5, guiTop + 60, 160, 45, false);
		this.fontRenderer.drawStringWithShadow("To craft put the right amount", guiLeft + 7, guiTop + 65, 0xFFFFFF);
		this.fontRenderer.drawStringWithShadow("of ingredients into slots above", guiLeft + 7, guiTop + 75, 0xFFFFFF);
		this.fontRenderer.drawStringWithShadow("and then click Craft.", guiLeft + 7, guiTop + 85, 0xFFFFFF);
	}
	
	public void drawIngredients()
	{
		if(tileentity.getItemByID(id) instanceof ICraftable)
		{
			List<ItemStack> recipe = ((ICraftable)tileentity.getItemByID(id)).getCraftingRecipe(tileentity.getItemByID(id));
			for(int i = 0; i < recipe.size(); i++)
			{
				ItemStack stack = recipe.get(i);
				int count = stack.getCount();
				
				RenderHelper.enableGUIStandardItemLighting();
				this.itemRender.renderItemIntoGUI(stack, guiLeft + 180, guiTop + 5 + i*18);
				RenderHelper.disableStandardItemLighting();
				this.fontRenderer.drawString("" + count, guiLeft + 200, guiTop + 9 + i*18, 0x00000);
			}
		}
	}
}
