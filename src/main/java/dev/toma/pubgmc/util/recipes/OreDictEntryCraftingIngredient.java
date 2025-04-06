package dev.toma.pubgmc.util.recipes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictEntryCraftingIngredient implements CraftingIngredient {

    private final String entryKey;
    private final int count;
    private final boolean ignoreItemMetadata;

    public OreDictEntryCraftingIngredient(String entryKey, int count) {
        this(entryKey, count, false);
    }

    public OreDictEntryCraftingIngredient(String entryKey, int count, boolean ignoreItemMetadata) {
        this.entryKey = entryKey;
        this.count = count;
        this.ignoreItemMetadata = ignoreItemMetadata;
    }

    @Override
    public boolean isValidInput(ItemStack stack) {
        ItemStack match = this.findMatchingItem(stack);
        return !match.isEmpty();
    }

    @Override
    public int requiredResourceSize() {
        return this.count;
    }

    @Override
    public void renderIngredient(Minecraft client, int x, int y) {
        NonNullList<ItemStack> validItems = OreDictionary.getOres(this.entryKey);
        if (validItems.isEmpty())
            return;
        long itemDisplayInterval = 1000L;
        long fullIndex = System.currentTimeMillis() % itemDisplayInterval;
        int normalized = (int) (fullIndex % validItems.size());
        ItemStack itemStack = validItems.get(normalized);
        RenderItem renderer = client.getRenderItem();
        renderer.renderItemIntoGUI(itemStack, x, y);
    }

    public ItemStack findMatchingItem(ItemStack stack) {
        NonNullList<ItemStack> matchingItems = OreDictionary.getOres(this.entryKey);
        if (matchingItems == null || matchingItems.isEmpty())
            return ItemStack.EMPTY;
        for (ItemStack ingredient : matchingItems) {
            if (ingredient.getItem() == stack.getItem() && (this.ignoreItemMetadata || ingredient.getMetadata() == stack.getMetadata())) {
                return ingredient;
            }
        }
        return ItemStack.EMPTY;
    }
}
