package com.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CommonConfig implements INBTSerializable<NBTTagCompound> {

    @Config.Name("World")
    public CFGWorld world = new CFGWorld();

    @Config.Name("Player")
    public CFGPlayer player = new CFGPlayer();

    @Config.Name("Items")
    public CFGItems items = new CFGItems();

    @Config.Name("Vehicles")
    public CFGVehicles vehicles = new CFGVehicles();

    @Config.Name("Weapons")
    public CFGWeapons weapons = new CFGWeapons();

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setTag("world", world.serializeNBT());
        c.setTag("player", player.serializeNBT());
        c.setTag("vehicles", vehicles.serializeNBT());
        c.setTag("weapons", weapons.serializeNBT());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        world.deserializeNBT(nbt.getCompoundTag("world"));
        player.deserializeNBT(nbt.getCompoundTag("player"));
        vehicles.deserializeNBT(nbt.getCompoundTag("vehicles"));
        weapons.deserializeNBT(nbt.getCompoundTag("weapons"));
    }
}
