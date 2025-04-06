package dev.toma.pubgmc.client.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.recipes.CraftingIngredient;
import dev.toma.pubgmc.util.recipes.WorkbenchRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecipeButton extends GuiButton {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/recipebutton.png");
    public final ItemStack stackToDraw;
    public final WorkbenchRecipe recipe;
    public double yTex, yTexE;
    public boolean active;
    private boolean hasIngredients = true;
    private int renderTime;
    private TileEntityGunWorkbench te;

    public RecipeButton(int id, int x, int y, WorkbenchRecipe recipe, TileEntityGunWorkbench te) {
        super(id, x, y, 99, 16, "");
        this.recipe = recipe;
        this.stackToDraw = recipe.result;
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
        if (hovered) {
            if (renderTime > 0) {
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
        for (CraftingIngredient craftingIngredient : recipe.ingredients) {
            int amount = 0;
            for (ItemStack stack : te.getInventory()) {
                if (craftingIngredient.isValidInput(stack)) {
                    amount += stack.getCount();
                }
            }
            int requiredResourceSize = craftingIngredient.requiredResourceSize();
            if (amount < requiredResourceSize) {
                this.hasIngredients = false;
                break;
            }
        }
    }

    public void updateButtonState() {
        this.calculateTextureOffset(hasIngredients);
    }

    private void calculateTextureOffset(boolean ingredients) {
        if (ingredients) {
            active = true;
            if (hovered) {
                yTex = 1.0 / 3.0D;
                yTexE = 2.0 / 3.0D;
            } else {
                yTex = 0;
                yTexE = 1.0 / 3.0D;
            }
        } else {
            active = false;
            yTex = 2 / 3.0D;
            yTexE = 1.0;
        }
    }
}
