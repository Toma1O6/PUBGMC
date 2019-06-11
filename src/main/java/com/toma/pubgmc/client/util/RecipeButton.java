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
	public final PMCRecipe recipe;
	public final InventoryPlayer playerInv;
	public double yTex, yTexE;
	
	public RecipeButton(int id, int x, int y, PMCRecipe recipe, InventoryPlayer playerInv) {
		super(id, x, y, 99, 16, "");
		this.recipe = recipe;
		this.playerInv = playerInv;
	}

	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		this.updateButtonState(playerInv);
		ImageUtil.drawImageWithUV(mc, TEXTURE, x, y, width, height, 0, yTex, 1, yTexE, false);
	}
	
	private void updateButtonState(InventoryPlayer inv) {
		boolean hasIngredients = false;
		/*for(PMCIngredient ing : recipe.ingredients) {
			int amount = 0;
			for(ItemStack stack : inv.mainInventory) {
				if(!stack.isEmpty() && stack.getItem() == ing.getIngredient()) {
					amount += stack.getCount();
				}
			}
			if(amount < ing.count) {
				hasIngredients = false;
				break;
			}
		}*/
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
