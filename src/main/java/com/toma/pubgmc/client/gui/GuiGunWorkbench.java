package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.util.RecipeButton;
import com.toma.pubgmc.common.container.ContainerGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGunWorkbench extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/gun_workbench.png");
	private static final ResourceLocation INFO = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/info.png");
	private final InventoryPlayer player;
	private final TileEntityGunWorkbench tileentity;
	private int index;
	private CraftMode category;
	
	public GuiGunWorkbench(TileEntityGunWorkbench te, InventoryPlayer playerInv)
	{
		super(new ContainerGunWorkbench(te, playerInv));
		this.tileentity = te;
		this.player = playerInv;
		xSize = 176;
		ySize = 193;
	}
	
	@Override
	public void initGui()
	{	
		super.initGui();
		buttonList.add(new RecipeButton(0, guiLeft + 45, guiTop + 8, null, player));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{	
		mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
