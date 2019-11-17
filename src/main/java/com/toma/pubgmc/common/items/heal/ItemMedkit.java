package com.toma.pubgmc.common.items.heal;

import com.toma.pubgmc.config.ConfigPMC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;

public class ItemMedkit extends ItemHealing {
    public ItemMedkit(String name) {
        super(name, 2, 1);
        setMaxStackSize(ConfigPMC.common.items.medkitLimit);
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
        return 160;
    }

    @Override
    public boolean canPlayerHeal(EntityPlayer player) {
        return player.getHealth() < 20f;
    }

    @Override
    public float getHealAmount(EntityPlayer player) {
        return 20 - player.getHealth();
    }
}
