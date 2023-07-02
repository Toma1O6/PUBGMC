package dev.toma.pubgmc.api.game.playzone;

import net.minecraft.nbt.NBTTagCompound;

public interface PlayzoneSerializer<P extends Playzone> {

    NBTTagCompound serializePlayzone(P playzone);

    P deserializePlayzone(NBTTagCompound nbt);
}
