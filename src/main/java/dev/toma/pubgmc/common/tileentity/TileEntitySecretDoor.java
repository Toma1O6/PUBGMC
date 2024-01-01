package dev.toma.pubgmc.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class TileEntitySecretDoor extends TileEntityGameDoor implements Predicate<UUID> {

    private UUID assignedDoorKey;

    public void assignDoorKey(UUID doorKey) {
        this.assignedDoorKey = doorKey;
        markDirty();
    }

    @Override
    public void setDoorState(boolean doorState) {
        // won't be affected by game states
    }

    @Override
    public boolean test(UUID uuid) {
        return Objects.equals(assignedDoorKey, uuid);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (assignedDoorKey != null)
            compound.setUniqueId("doorId", assignedDoorKey);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("doorIdMost"))
            assignedDoorKey = compound.getUniqueId("doorId");
    }
}
