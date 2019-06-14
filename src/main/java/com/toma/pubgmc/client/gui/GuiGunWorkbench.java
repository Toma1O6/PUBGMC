package com.toma.pubgmc.client.gui;

import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.util.RecipeButton;
import com.toma.pubgmc.common.container.ContainerGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.util.recipes.PMCRecipe;
import com.toma.pubgmc.util.recipes.RecipeRegistry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGunWorkbench extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/gun_workbench.png");
	private static final ResourceLocation INFO = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/info.png");
	private final InventoryPlayer player;
	private final TileEntityGunWorkbench tileentity;
	private int page = 0;
	
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
		buttonList.clear();
		List<PMCRecipe> list = tileentity.RECIPES.get(tileentity.selectedCat.ordinal());
		for(int i = page*4+4; i > page*4; i--) {
			if(i < list.size()) {
				this.buttonList.add(new RecipeButton(buttonList.size(), guiLeft + 45, guiTop + i*18 - 10, list.get(i), player));
			}
		}
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
