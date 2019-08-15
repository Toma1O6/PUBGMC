package com.toma.pubgmc.config.client;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public class ClientConfig implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Aim type")
    public CFGAimType aimType = CFGAimType.TOGGLE;

    @Config.Name("Overlays")
    @Config.Comment("Overlay rendering options")
    public CFGOverlaySettings overlays = new CFGOverlaySettings();

    @Config.Name("Other")
    public CFGOtherSettings other = new CFGOtherSettings();

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setInteger("aimType", aimType.ordinal());
        c.setTag("overlays", overlays.serializeNBT());
        c.setTag("other", other.serializeNBT());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        aimType = CFGAimType.values()[nbt.getInteger("aimType")];
        overlays.deserializeNBT(nbt.getCompoundTag("overlays"));
        other.deserializeNBT(nbt.getCompoundTag("other"));
    }
}
