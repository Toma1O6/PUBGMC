package com.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public final class CFG3DCoords extends CFG2DCoords {

    @Config.Name("The Z position of object")
    public int z;

    public CFG3DCoords(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
}
