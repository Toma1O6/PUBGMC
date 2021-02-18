package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.api.interfaces.IGameTileEntity;
import dev.toma.pubgmc.common.blocks.BlockWindow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TileEntityWindow extends TileEntity implements IGameTileEntity {

    private String hash = "null";

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("hash", hash);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        hash = compound.getString("hash");
    }

    @Override
    public void setGameHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String getGameHash() {
        return hash;
    }

    @Nullable
    @Override
    public void onLoaded() {
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof BlockWindow) {
            if(state.getValue(BlockWindow.BROKEN)) {
                world.scheduleBlockUpdate(pos, state.getBlock(), 5, 0);
            }
        }
    }
}
