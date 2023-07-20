package dev.toma.pubgmc.api.game.map;

import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public final class GameLobby implements Bounds2 {

    public static final int DEFAULT_RADIUS = 5;

    private final BlockPos center;
    private final int radius;

    public GameLobby(BlockPos center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public void teleport(EntityPlayer player) {
        if (player.world.isRemote)
            return;
        GameHelper.teleport(player, center.getX() + 0.5, center.getY() + 1.0, center.getZ() + 0.5);
    }

    public BlockPos get() {
        return center;
    }

    @Override
    public boolean isWithin(double x, double z) {
        return Math.abs(x - center.getX()) <= radius && Math.abs(z - center.getZ()) <= radius;
    }

    @Override
    public boolean isWithin(Entity entity) {
        return isWithin(entity.getPosition());
    }

    @Override
    public boolean isWithin(BlockPos pos) {
        int x = Math.abs(center.getX() - pos.getX());
        int y = Math.abs(center.getY() - pos.getY());
        int z = Math.abs(center.getZ() - pos.getZ());
        return x <= radius && y <= radius && z <= radius;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("center", NBTUtil.createPosTag(center));
        nbt.setInteger("rad", radius);
        return nbt;
    }

    public static GameLobby deserialize(NBTTagCompound nbt) {
        BlockPos center = NBTUtil.getPosFromTag(nbt.getCompoundTag("center"));
        int radius = nbt.hasKey("rad") ? Math.max(0, nbt.getInteger("rad")) : DEFAULT_RADIUS;
        return new GameLobby(center, radius);
    }
}
