package com.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGPlayer implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Enable knockback")
    public boolean knockbackEnabled = false;

    @Config.Name("Allow third person")
    @Config.RequiresWorldRestart
    public boolean tppAllowed = true;

    @Config.Name("Render nametags")
    @Config.RequiresWorldRestart
    public boolean renderNames = false;

    @Config.Name("Limited inventory")
    public boolean inventoryLimit = true;

    @Config.Name("Force brightness")
    @Config.RequiresWorldRestart
    public boolean brightnessForced = false;

    @Config.Name("Brightness amount")
    @Config.Comment("Requires the \"Force Brightness\" field to be true!")
    @Config.RequiresWorldRestart
    public int brightnessValue = 25;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setBoolean("knockback", knockbackEnabled);
        c.setBoolean("tpp", tppAllowed);
        c.setBoolean("names", renderNames);
        c.setBoolean("invLimit", inventoryLimit);
        c.setBoolean("brightnessForced", brightnessForced);
        c.setInteger("brightness", brightnessValue);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        knockbackEnabled = nbt.getBoolean("knockback");
        tppAllowed = nbt.getBoolean("tpp");
        renderNames = nbt.getBoolean("names");
        inventoryLimit = nbt.getBoolean("invLimit");
        brightnessForced = nbt.getBoolean("brightnessForced");
        brightnessValue = nbt.getInteger("brightness");
    }
}
