package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.api.capability.BoostStats;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPainkiller extends ItemHealing {

    public ItemPainkiller(String name) {
        super(name);
        setMaxStackSize(2);
    }

    @Override
    public int getUsageTime(ItemStack stack) {
        return 120;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
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
                stats.addBoost(180); // 60%
                data.sync();
            });
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        } else {
            entity.heal(4.0F);
            stack.shrink(1);
        }
    }
}
