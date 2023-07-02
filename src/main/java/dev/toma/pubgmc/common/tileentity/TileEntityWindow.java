package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.blocks.BlockWindow;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class TileEntityWindow extends TileEntity implements GameObject {

    private UUID gameId = GameHelper.DEFAULT_UUID;

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
        if (state.getBlock() instanceof BlockWindow) {
            if (state.getValue(BlockWindow.BROKEN)) {
                world.scheduleBlockUpdate(pos, state.getBlock(), 5, 0);
            }
        }
    }
}
