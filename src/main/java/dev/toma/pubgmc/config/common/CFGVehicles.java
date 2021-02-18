package dev.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGVehicles implements INBTSerializable<NBTTagCompound> {

    @Config.Name("UAZ")
    public CFGVehicle uaz = new CFGVehicle(250F, 1.6F, 3.0F, 0.015F, 0.3F);

    @Config.Name("Dacia")
    public CFGVehicle dacia = new CFGVehicle(200F, 2.35F, 3.3F, 0.01F, 0.3f);

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setTag("uaz", uaz.serializeNBT());
        c.setTag("dacia", dacia.serializeNBT());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        uaz.deserializeNBT(nbt.getCompoundTag("uaz"));
        dacia.deserializeNBT(nbt.getCompoundTag("dacia"));
    }
}
