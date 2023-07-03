package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.common.capability.PlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerDataProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPlayerData.class)
    public static final Capability<IPlayerData> PLAYER_DATA = null;

    private final IPlayerData instance;

    public PlayerDataProvider() {
        this.instance = PLAYER_DATA.getDefaultInstance();
    }

    public PlayerDataProvider(EntityPlayer player) {
        this.instance = new PlayerData(player);
    }

    public static IPlayerData get(EntityPlayer player) {
        return player.getCapability(PLAYER_DATA, null);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == PLAYER_DATA;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == PLAYER_DATA ? PLAYER_DATA.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return PLAYER_DATA.getStorage().writeNBT(PLAYER_DATA, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        PLAYER_DATA.getStorage().readNBT(PLAYER_DATA, this.instance, null, nbt);
    }
}
