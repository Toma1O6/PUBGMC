package dev.toma.pubgmc.api.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface HealingItem {

    int getMaxItemUseDuration(ItemStack stack);

    boolean canHeal(EntityLivingBase entity, ItemStack stack);

    void heal(EntityLivingBase entity, ItemStack stack, World world);
}
