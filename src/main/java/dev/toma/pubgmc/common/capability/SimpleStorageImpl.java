package dev.toma.pubgmc.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public final class SimpleStorageImpl<T extends INBTSerializable<NBTTagCompound>> implements Capability.IStorage<T> {

    private static final SimpleStorageImpl<?> INSTANCE = new SimpleStorageImpl<>();

    private SimpleStorageImpl() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends INBTSerializable<NBTTagCompound>> SimpleStorageImpl<T> instance() {
        return (SimpleStorageImpl<T>) INSTANCE;
    }

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt) {
        instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
    }
}
