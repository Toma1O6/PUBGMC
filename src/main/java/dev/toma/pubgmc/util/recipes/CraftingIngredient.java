package dev.toma.pubgmc.util.recipes;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface CraftingIngredient {

    boolean isValidInput(ItemStack stack);

    int requiredResourceSize();

    @SideOnly(Side.CLIENT)
    void renderIngredient(Minecraft client, int x, int y);
}
