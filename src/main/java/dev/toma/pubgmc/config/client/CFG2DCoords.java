package dev.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public class CFG2DCoords {

    @Config.Name("The X position of object")
    public int x;

    @Config.Name("The Y position of object")
    public int y;

    public CFG2DCoords(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
