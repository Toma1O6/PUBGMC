package com.toma.pubgmc.common.items.heal;

import com.toma.pubgmc.config.ConfigPMC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;

public class ItemBandage extends ItemHealing {
    public ItemBandage(String name) {
        super(name);
        setMaxStackSize(ConfigPMC.common.items.bandageLimit);
    }

    @Override
    public Action getAction() {
        return Action.HEAL;
    }

    @Override
    public EnumAction getUseAction() {
        return EnumAction.NONE;
    }

    @Override
    public int getUseTime() {
        return 80;
    }

    @Override
    public float getHealAmount(EntityPlayer player) {
        return canPlayerHeal(player) ? player.getHealth() == 14 ? 1f : 2f : 0f;
    }

    @Override
    public boolean canPlayerHeal(EntityPlayer player) {
        return player.getHealth() < 15f;
    }
}
