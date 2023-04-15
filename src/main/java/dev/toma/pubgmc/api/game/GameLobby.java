package dev.toma.pubgmc.api.game;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public final class GameLobby {

    private final BlockPos center;
    private final int range;

    public GameLobby(BlockPos center, int range) {
        this.center = center;
        this.range = range;
    }

    public boolean isInLobby(EntityPlayer player) {
        return center.distanceSqToCenter(player.posX, player.posY, player.posZ) <= (range * range);
    }

    public void teleport(EntityPlayer player) {
        if (player.world.isRemote)
            return;
        player.attemptTeleport(center.getX() + 0.5, center.getY() + 1.0, center.getZ() + 0.5);
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("center", NBTUtil.createPosTag(center));
        nbt.setInteger("range", range);
        return nbt;
    }

    public static GameLobby deserialize(NBTTagCompound nbt) {
        BlockPos center = NBTUtil.getPosFromTag(nbt.getCompoundTag("center"));
        int range = nbt.getInteger("range");
        return new GameLobby(center, range);
    }
}
