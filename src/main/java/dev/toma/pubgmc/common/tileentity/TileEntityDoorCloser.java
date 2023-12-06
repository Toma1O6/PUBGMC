package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class TileEntityDoorCloser extends TileEntity implements RecoverableStateTile {

    private UUID gameId = GameHelper.DEFAULT_UUID;
    private int offset;

    @Override
    public void walk(int direction) {
        this.offset += direction;
        markDirty();
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public boolean canLink(IBlockState state) {
        return state.getBlock() instanceof BlockDoor;
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        assignGameId(newGameId);
        BlockPos offsetPos = pos.up(offset);
        IBlockState state = world.getBlockState(offsetPos);
        if (canLink(state)) {
            ((BlockDoor) state.getBlock()).toggleDoor(world, offsetPos, false);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setUniqueId("gameId", gameId);
        compound.setInteger("offset", offset);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        gameId = compound.getUniqueId("gameId");
        offset = compound.getInteger("offset");
    }
}
