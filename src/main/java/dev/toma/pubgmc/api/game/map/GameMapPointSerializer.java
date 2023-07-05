package dev.toma.pubgmc.api.game.map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface GameMapPointSerializer<P extends GameMapPoint> {

    NBTTagCompound serializePointData(P point);

    P deserializePointData(BlockPos pointPosition, NBTTagCompound nbt);

    P createDefaultInstance(BlockPos pos, World world, GameMap map);
}
