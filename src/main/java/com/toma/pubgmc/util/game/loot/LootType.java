package com.toma.pubgmc.util.game.loot;

import com.toma.pubgmc.util.math.IWeight;

public enum LootType implements IWeight {
    GUN(10),
    AMMO(20),
    ATTACHMENT(25),
    ARMOR(25),
    HEAL(15);

    private final int weight;

    LootType(final int categoryWeight) {
        this.weight = categoryWeight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
