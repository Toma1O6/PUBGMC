package com.toma.pubgmc.client.util;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.util.ImageUtil;
import com.toma.pubgmc.util.recipes.PMCIngredient;
import com.toma.pubgmc.util.recipes.PMCRecipe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecipeButton extends GuiButton {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/recipebutton.png");
	public final ItemStack stackToDraw;
	public final PMCRecipe recipe;
	public double yTex, yTexE;
	private boolean hasIngredients = true;
	private int renderTime;
	public boolean active;
	private TileEntityGunWorkbench te;
	
	public RecipeButton(int id, int x, int y, PMCRecipe recipe, TileEntityGunWorkbench te) {
		super(id, x, y, 99, 16, "");
		this.recipe = recipe;
		this.stackToDraw = new ItemStack(recipe.result);
		this.te = te;
		this.performIngredientCheck();
	}

	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		this.updateButtonState();
		ImageUtil.drawImageWithUV(mc, TEXTURE, x, y, width, height, 0, yTex, 1, yTexE, false);
		mc.getRenderItem().renderItemIntoGUI(stackToDraw, x + 3, y - 1);
		mc.fontRenderer.drawStringWithShadow(stackToDraw.getDisplayName(), x + 21, y + 4, 0xFFFFFF);
		if(hovered) {
			if(renderTime > 0) {
				this.recipe.drawRecipe(mouseX, mouseY);
				--renderTime;
			}
		} else {
			renderTime = mc.getDebugFPS() * 2;
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return active && hovered;
	}
	
	public void performIngredientCheck() {
		hasIngredients = true;
		for(PMCIngredient ing : recipe.ingredients) {
			int amount = 0;
			for(ItemStack stack : te.getInventory()) {
				if(!stack.isEmpty() && stack.getItem() == ing.getIngredient().getItem()) {
					amount += stack.getCount();
				}
			}
			if(amount < ing.getIngredient().getCount()) {
				hasIngredients = false;
				break;
			}
		}
	}
	
	public void updateButtonState() {
		this.calculateTextureOffset(hasIngredients);
	}
	
	private void calculateTextureOffset(boolean ingredients) {
		if(ingredients) {
			active = true;
			if(hovered) {
				yTex = 1.0/3.0D;
				yTexE = 2.0/3.0D;
			} else {
				yTex = 0;
				yTexE = 1.0/3.0D;
			}
		} else {
			active = false;
			yTex = 2/3.0D;
			yTexE = 1.0;
		}
	}
}
