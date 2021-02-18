package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;

public class ItemFirstAidKit extends ItemHealing {
    public ItemFirstAidKit(String name) {
        super(name, 7, 1);
        setMaxStackSize(ConfigPMC.common.items.firstAidLimit);
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
        return 120;
    }

    @Override
    public boolean canPlayerHeal(EntityPlayer player) {
        return player.getHealth() < 15f;
    }

    @Override
    public float getHealAmount(EntityPlayer player) {
        return 15 - player.getHealth();
    }
}
