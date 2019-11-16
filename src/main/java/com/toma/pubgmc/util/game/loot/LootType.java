package com.toma.pubgmc.util.game.loot;

import com.toma.pubgmc.util.math.IWeight;

public enum LootType implements IWeight {
    GUN(0),
    AMMO(0),
    ATTACHMENT(0),
    ARMOR(0),
    HEAL(0);

    private final int weight;

    LootType(final int categoryWeight) {
        this.weight = categoryWeight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
