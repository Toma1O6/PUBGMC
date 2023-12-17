package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketProneStatus implements IMessage {

    boolean prone;

    public C2S_PacketProneStatus() {
    }

    public C2S_PacketProneStatus(boolean prone) {
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

    public static class Handler implements IMessageHandler<C2S_PacketProneStatus, IMessage> {

        public static void handle(C2S_PacketProneStatus p, EntityPlayer player) {
            IPlayerData data = PlayerDataProvider.get(player);
            if (data != null) {
                data.setProne(p.prone, false);
                data.sync();
            }
        }

        @Override
        public IMessage onMessage(C2S_PacketProneStatus message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> handle(message, player));
            return null;
        }
    }
}
