package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.common.capability.ExtendedChunkStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ChunkGameBlockDataProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ChunkGameBlockData.class)
    public static final Capability<ChunkGameBlockData> CHUNK_DATA = null;
    private final ChunkGameBlockData instance;

    public ChunkGameBlockDataProvider() {
        this.instance = new ExtendedChunkStorage();
    }

    public static Optional<ChunkGameBlockData> getChunkData(@Nullable Chunk chunk) {
        return chunk != null ? Optional.ofNullable(chunk.getCapability(CHUNK_DATA, null)) : Optional.empty();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CHUNK_DATA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.hasCapability(capability, facing) ? CHUNK_DATA.cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) CHUNK_DATA.getStorage().writeNBT(CHUNK_DATA, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CHUNK_DATA.getStorage().readNBT(CHUNK_DATA, this.instance, null, nbt);
    }
}
