package dev.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGPlayer implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Enable knockback")
    public boolean knockbackEnabled = false;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setBoolean("knockback", knockbackEnabled);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        knockbackEnabled = nbt.getBoolean("knockback");
    }
}
