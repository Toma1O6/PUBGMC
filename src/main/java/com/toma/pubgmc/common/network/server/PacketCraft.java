package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.util.PacketHelper;
import com.toma.pubgmc.util.recipes.RecipeRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCraft implements IMessage {

    BlockPos pos;
    int id;

    public PacketCraft() {
    }

    public PacketCraft(BlockPos pos, int id) {
        this.pos = pos;
        this.id = id;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketHelper.writeBlockPos(pos, buf);
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = PacketHelper.readBlockPos(buf);
        id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketCraft, IMessage> {

        @Override
        public IMessage onMessage(PacketCraft message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                TileEntity te = player.world.getTileEntity(message.pos);
                if (te instanceof TileEntityGunWorkbench) {
                    ((TileEntityGunWorkbench) te).craft(RecipeRegistry.RECIPES.get(message.id));
                }
            });
            return null;
        }
    }
}
