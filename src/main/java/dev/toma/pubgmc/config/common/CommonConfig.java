package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.ObjectType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class CommonConfig extends ObjectType implements INBTSerializable<NBTTagCompound> {

    final ConfigPlugin plugin;
    public CFGWorld world;
    public CFGVehicles vehicles;
    public CFGWeapons weapons;
    public PlayerConfig players;

    public CommonConfig(ConfigPlugin plugin) {
        super("Common");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        world = configCreator.createObject(new CFGWorld(), plugin);
        vehicles = configCreator.createObject(new CFGVehicles(plugin), plugin);
        weapons = configCreator.createObject(new CFGWeapons(plugin), plugin);
        players = configCreator.createObject(new PlayerConfig(), plugin);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setTag("weapons", weapons.serializeNBT());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        weapons.deserializeNBT(nbt.getCompoundTag("weapons"));
    }
}
