package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class ItemGhillie extends PMCItem implements GhillieSuit {

    public static final int DEFAULT_COLOR = 0x52D900;

    public ItemGhillie(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(PMCTabs.TAB_ITEMS);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 25, true));
    }

    @Override
    public int getColor(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt != null && nbt.hasKey("ghillieColor", Constants.NBT.TAG_INT)
                ? nbt.getInteger("ghillieColor")
                : DEFAULT_COLOR;
    }

    public static void setFoliageColor(ItemStack stack, int color) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
        }
        compound.setInteger("ghillieColor", color);
    }
}
