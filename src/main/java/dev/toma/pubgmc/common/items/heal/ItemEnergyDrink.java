package dev.toma.pubgmc.common.items.heal;

import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.item.EnumAction;

public class ItemEnergyDrink extends ItemHealing {
    public ItemEnergyDrink(String name) {
        super(name, 15, 1);
        setMaxStackSize(ConfigPMC.common.items.energyDrinkLimit);
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
    public float getBoostAmount() {
        return 40f;
    }
}
