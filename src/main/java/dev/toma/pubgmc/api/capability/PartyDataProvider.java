package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.common.capability.GamePartyData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class PartyDataProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(PartyData.class)
    public static final Capability<PartyData> PARTY_DATA = null;
    private final PartyData instance;

    public PartyDataProvider(World world) {
        this.instance = new GamePartyData(world);
    }

    public static Optional<PartyData> getPartyData(@Nullable World world) {
        return world != null ? Optional.ofNullable(world.getCapability(PARTY_DATA, null)) : Optional.empty();
    }

    @SuppressWarnings("ConstantValue")
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == PARTY_DATA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.hasCapability(capability, facing) ? PARTY_DATA.cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) PARTY_DATA.getStorage().writeNBT(PARTY_DATA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        PARTY_DATA.getStorage().readNBT(PARTY_DATA, instance, null, nbt);
    }
}
