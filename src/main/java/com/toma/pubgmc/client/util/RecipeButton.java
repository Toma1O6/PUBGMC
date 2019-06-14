package com.toma.pubgmc.client.util;

import com.toma.pubgmc.Pubgmc;
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
	
	public RecipeButton(int id, int x, int y, PMCRecipe recipe, InventoryPlayer playerInv) {
		super(id, x, y, 99, 16, "");
		this.recipe = recipe;
		this.stackToDraw = new ItemStack(recipe.result);
		this.performIngredientCheck(playerInv);
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
	
	private void performIngredientCheck(InventoryPlayer inv) {
		hasIngredients = true;
		for(PMCIngredient ing : recipe.ingredients) {
			int amount = 0;
			for(ItemStack stack : inv.mainInventory) {
				if(!stack.isEmpty() && stack.getItem() == ing.getIngredient().getItem()) {
					amount += stack.getCount();
				}
			}
			if(amount < ing.count) {
				hasIngredients = false;
				break;
			}
		}
	}
	
	private void updateButtonState() {
		this.calculateTextureOffset(hasIngredients);
	}
	
	private void calculateTextureOffset(boolean ingredients) {
		if(ingredients) {
			if(hovered) {
				yTex = 1.0/3.0D;
				yTexE = 2.0/3.0D;
			} else {
				yTex = 0;
				yTexE = 1.0/3.0D;
			}
		} else {
			yTex = 2/3.0D;
			yTexE = 1.0;
		}
	}
}
