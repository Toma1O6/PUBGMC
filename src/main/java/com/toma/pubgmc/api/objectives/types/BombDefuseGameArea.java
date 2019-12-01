package com.toma.pubgmc.api.objectives.types;

import net.minecraft.util.math.BlockPos;

public class BombDefuseGameArea extends GameArea {

    private final EnumAreaType type;

    public BombDefuseGameArea(final BlockPos center, final int radius, final EnumAreaType type) {
        super(center, radius);
        this.type = type;
    }

    public enum EnumAreaType {
        T_SPAWN,
        CT_SPAWN,
        BOMBSITE
    }
}
