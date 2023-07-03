package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.common.capability.GameDataImpl;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class GameDataProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(GameData.class)
    public static final Capability<GameData> GAME_DATA = null;

    private GameData instance;

    public GameDataProvider(World world) {
        this.instance = new GameDataImpl(world);
    }

    public static Optional<GameData> getGameData(World world) {
        return Optional.ofNullable(world.getCapability(GAME_DATA, null));
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == GAME_DATA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == GAME_DATA ? GAME_DATA.cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) GAME_DATA.getStorage().writeNBT(GAME_DATA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        GAME_DATA.getStorage().readNBT(GAME_DATA, instance, null, nbt);
    }
}
