package com.toma.pubgmc.config.client;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public class CFG2DCoords implements INBTSerializable<NBTTagCompound> {

    @Config.Name("The X position of object")
    public int x;

    @Config.Name("The Y position of object")
    public int y;

    public CFG2DCoords(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setInteger("x", x);
        c.setInteger("y", y);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.x = nbt.hasKey("x") ? nbt.getInteger("x") : 0;
        this.y = nbt.hasKey("y") ? nbt.getInteger("y") : 0;
    }
}
