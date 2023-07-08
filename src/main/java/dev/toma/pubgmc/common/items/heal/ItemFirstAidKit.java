package dev.toma.pubgmc.common.items.heal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;

public class ItemFirstAidKit extends ItemHealing {
    public ItemFirstAidKit(String name) {
        super(name, 7, 1);
        setMaxStackSize(1);
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

    @Override
    public float getAIHealAmount() {
        return 10.0F;
    }
}
