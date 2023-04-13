package dev.toma.pubgmc.api.game.area;

import net.minecraft.nbt.NBTTagCompound;

public interface GameAreaSerializer<A extends GameArea> {

    NBTTagCompound serializeArea(A area);

    A deserializeArea(NBTTagCompound nbt);
}
