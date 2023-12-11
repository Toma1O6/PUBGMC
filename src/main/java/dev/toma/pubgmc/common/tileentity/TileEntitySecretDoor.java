package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.blocks.BlockSecretDoor;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class TileEntitySecretDoor extends TileEntity implements GameObject, Predicate<UUID> {

    private UUID gameId = GameHelper.DEFAULT_UUID;
    private UUID assignedDoorKey;

    public void assignDoorKey(UUID doorKey) {
        this.assignedDoorKey = doorKey;
        markDirty();
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public boolean test(UUID uuid) {
        return Objects.equals(assignedDoorKey, uuid);
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
        markDirty();
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        assignGameId(newGameId);
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockSecretDoor) {
            ((BlockSecretDoor) state.getBlock()).toggleDoor(world, pos, false);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setUniqueId("gameId", gameId);
        if (assignedDoorKey != null)
            compound.setUniqueId("doorId", assignedDoorKey);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        gameId = compound.getUniqueId("gameId");
        if (compound.hasKey("doorIdMost"))
            assignedDoorKey = compound.getUniqueId("doorId");
    }
}
