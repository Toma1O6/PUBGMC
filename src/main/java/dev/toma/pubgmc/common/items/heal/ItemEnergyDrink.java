package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.api.capability.BoostStats;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnergyDrink extends ItemHealing {

    public ItemEnergyDrink(String name) {
        super(name);
        setMaxStackSize(5);
    }

    @Override
    public int getUsageTime(ItemStack stack) {
        return 80;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
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
                stats.add(8);
                data.sync();
            });
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        } else {
            entity.heal(2.0F);
            stack.shrink(1);
        }
    }
}
