package com.toma.pubgmc.world;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.util.game.ZoneSettings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class BlueZone implements INBTSerializable<NBTTagCompound> {

    private Game game;
    private ZoneSettings settings;

    public BlueZone(ZoneSettings settings, Game game) {
        this.settings = settings;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("settings", settings.serializeNBT());
        nbt.setTag("game", game.serializeNBT());
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        settings.deserializeNBT(nbt.getCompoundTag("settings"));
        game.deserializeNBT(nbt.getCompoundTag("game"));
    }
}
