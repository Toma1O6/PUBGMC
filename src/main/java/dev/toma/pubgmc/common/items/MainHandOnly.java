package dev.toma.pubgmc.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface MainHandOnly {

    void dropItemFromInvalidSlot(ItemStack stack, EntityPlayer player);
}
