package com.toma.pubgmc.common.items.heal;

import com.toma.pubgmc.config.ConfigPMC;
import net.minecraft.item.EnumAction;

public class ItemPainkiller extends ItemHealing {
    public ItemPainkiller(String name) {
        super(name);
        setMaxStackSize(ConfigPMC.common.items.painkillerLimit);
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
    public float getBoostAmount() {
        return 60f;
    }
}
