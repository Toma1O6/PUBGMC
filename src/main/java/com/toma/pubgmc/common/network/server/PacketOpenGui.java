package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.Pubgmc;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenGui implements IMessage {
    private int guiID;

    public PacketOpenGui() {
    }

    public PacketOpenGui(int guiID) {
        this.guiID = guiID;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(guiID);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        guiID = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketOpenGui, IMessage> {
        @Override
        public IMessage onMessage(PacketOpenGui message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> player.openGui(Pubgmc.instance, message.guiID, player.world, (int) player.posX, (int) player.posY, (int) player.posZ));
            return null;
        }
    }
}
