package dev.toma.pubgmc.common.items.heal;

import net.minecraft.item.EnumAction;

public class ItemPainkiller extends ItemHealing {
    public ItemPainkiller(String name) {
        super(name, 12, 1);
        setMaxStackSize(4);
    }

    @Override
    public Action getAction() {
        return Action.BOOST;
    }

    @Override
    public EnumAction getUseAction() {
        return EnumAction.EAT;
    }

    @Override
    public int getUseTime() {
        return 120;
    }

    @Override
    public int getBoostAmount() {
        return 12;
    }
}
