package com.toma.pubgmc.common.items.heal;

import com.toma.pubgmc.config.ConfigPMC;
import net.minecraft.item.EnumAction;

public class ItemAdrenalineSyringe extends ItemHealing {
    public ItemAdrenalineSyringe(String name) {
        super(name);
        setMaxStackSize(ConfigPMC.common.items.adrenalineSyringeLimit);
    }

    @Override
    public Action getAction() {
        return Action.BOOST;
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
    public float getBoostAmount() {
        return 100f;
    }
}
