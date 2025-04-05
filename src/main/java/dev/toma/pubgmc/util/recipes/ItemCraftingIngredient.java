package dev.toma.pubgmc.util.recipes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCraftingIngredient implements CraftingIngredient {

    private final ItemStack ingredient;

    public ItemCraftingIngredient(ItemStack ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean isValidInput(ItemStack stack) {
        return this.ingredient.getItem() == stack.getItem() && this.ingredient.getMetadata() == stack.getMetadata();
    }

    @Override
    public int requiredResourceSize() {
        return this.ingredient.getCount();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderIngredient(Minecraft client, int x, int y) {
        RenderItem renderer = client.getRenderItem();
        renderer.renderItemIntoGUI(this.ingredient, x, y);
    }
}
