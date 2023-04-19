package dev.toma.pubgmc.api.game;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public final class GameLobby {

    private final BlockPos center;

    public GameLobby(BlockPos center) {
        this.center = center;
    }

    public void teleport(EntityPlayer player) {
        if (player.world.isRemote)
            return;
        player.setPositionAndUpdate(center.getX() + 0.5, center.getY() + 1.0, center.getZ() + 0.5);
    }

    public BlockPos get() {
        return center;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("center", NBTUtil.createPosTag(center));
        return nbt;
    }

    public static GameLobby deserialize(NBTTagCompound nbt) {
        BlockPos center = NBTUtil.getPosFromTag(nbt.getCompoundTag("center"));
        return new GameLobby(center);
    }
}
