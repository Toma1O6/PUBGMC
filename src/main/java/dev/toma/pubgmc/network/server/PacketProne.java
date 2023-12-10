package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketProne implements IMessage {

    boolean prone;

    public PacketProne() {
    }

    public PacketProne(boolean prone) {
        this.prone = prone;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(prone);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        prone = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketProne, IMessage> {

        public static void handle(PacketProne p, EntityPlayer player) {
            IPlayerData data = PlayerDataProvider.get(player);
            if (data != null) {
                data.setProne(p.prone, false);
                data.sync();
            }
        }

        @Override
        public IMessage onMessage(PacketProne message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> handle(message, player));
            return null;
        }
    }
}
