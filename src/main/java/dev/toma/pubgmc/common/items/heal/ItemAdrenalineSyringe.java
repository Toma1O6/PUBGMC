package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.api.capability.BoostStats;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAdrenalineSyringe extends ItemHealing {

    public ItemAdrenalineSyringe(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 120;
    }

    @Override
    public boolean canHeal(EntityLivingBase entity, ItemStack stack) {
        return true;
    }

    @Override
    public void heal(EntityLivingBase entity, ItemStack stack, World world) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            PlayerDataProvider.getOptional(player).ifPresent(data -> {
                BoostStats stats = data.getBoostStats();
                stats.add(20);
                data.sync();
            });
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        } else {
            entity.heal(6.0F);
            stack.shrink(1);
        }
    }
}
