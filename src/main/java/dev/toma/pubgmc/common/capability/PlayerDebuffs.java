package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.api.entity.EntityDebuffs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerDebuffs implements EntityDebuffs, INBTSerializable<NBTTagCompound> {

    private int blindTime;
    private int deafTime;

    public void tick() {
        if (blindTime > 0) {
            --blindTime;
        }
        if (deafTime > 0) {
            --deafTime;
        }
    }

    @Override
    public boolean isBlind() {
        return blindTime > 0;
    }

    @Override
    public boolean isDeaf() {
        return deafTime > 0;
    }

    @Override
    public void clearBlindStatus() {
        blindTime = 0;
    }

    @Override
    public void clearDeafStatus() {
        deafTime = 0;
    }

    @Override
    public void setBlindTime(int time) {
        blindTime = time;
    }

    @Override
    public void setDeafTime(int time) {
        deafTime = time;
    }

    @Override
    public int getRemainingBlindTime() {
        return blindTime;
    }

    @Override
    public int getRemainingDeafTime() {
        return deafTime;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("blind", blindTime);
        compound.setInteger("deaf", deafTime);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        blindTime = nbt.getInteger("blind");
        deafTime = nbt.getInteger("deaf");
    }
}
