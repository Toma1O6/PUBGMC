package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.map.GameDoor;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class TileEntityGameDoor extends TileEntity implements GameDoor {

    private UUID gameId = GameHelper.DEFAULT_UUID;

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
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
        setDoorState(getDefaultDoorState());
    }

    @Override
    public void setDoorState(boolean doorState) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockDoor) {
            ((BlockDoor) state.getBlock()).toggleDoor(world, pos, doorState);
        }
    }

    protected boolean getDefaultDoorState() {
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setUniqueId("gameId", gameId);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        gameId = compound.getUniqueId("gameId");
    }
}
