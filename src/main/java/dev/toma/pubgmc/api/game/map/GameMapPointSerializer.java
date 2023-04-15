package dev.toma.pubgmc.api.game.map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public interface GameMapPointSerializer<P extends GameMapPoint> {

    void serializePointData(P point, NBTTagCompound nbt);

    P deserializePointData(BlockPos pointPosition, NBTTagCompound nbt);
}
