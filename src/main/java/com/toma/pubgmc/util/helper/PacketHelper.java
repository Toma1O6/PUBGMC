package com.toma.pubgmc.util.helper;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;

public class PacketHelper {

    public static void writeBlockPos(BlockPos pos, ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static BlockPos readBlockPos(ByteBuf buf) {
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        return pos;
    }
}
