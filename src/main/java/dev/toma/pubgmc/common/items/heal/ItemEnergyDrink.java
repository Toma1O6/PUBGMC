package dev.toma.pubgmc.common.items.heal;

import net.minecraft.item.EnumAction;

public class ItemEnergyDrink extends ItemHealing {
    public ItemEnergyDrink(String name) {
        super(name, 15, 1);
        setMaxStackSize(5);
    }

    @Override
    public Action getAction() {
        return Action.BOOST;
    }

    @Override
    public EnumAction getUseAction() {
        return EnumAction.DRINK;
    }

    @Override
    public int getUseTime() {
        return 80;
    }

    @Override
    public int getBoostAmount() {
        return 8;
    }
}
