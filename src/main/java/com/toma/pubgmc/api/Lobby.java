package com.toma.pubgmc.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public final class Lobby {

    public final BlockPos center;
    public final int radius;

    public Lobby(BlockPos pos, int size) {
        this.center = pos;
        this.radius = size;
    }

    public boolean isInLobby(EntityPlayer player) {
        BlockPos playerPos = player.getPosition();
        return Math.abs(center.getX() - playerPos.getX()) >= radius && Math.abs(center.getZ() - playerPos.getZ()) >= radius;
    }

    public static NBTTagCompound toNBT(Lobby lobby) {
        if(lobby == null) {
            return new NBTTagCompound();
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("pos", NBTUtil.createPosTag(lobby.center));
        nbt.setInteger("rad", lobby.radius);
        return nbt;
    }

    public static Lobby fromNBT(NBTTagCompound nbt) {
        BlockPos center = NBTUtil.getPosFromTag(nbt.getCompoundTag("pos"));
        int range = nbt.getInteger("rad");
        if(center.getY() < 1 && range < 1) {
            return null;
        }
        return new Lobby(center, range);
    }
}
