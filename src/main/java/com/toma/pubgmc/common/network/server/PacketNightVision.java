package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketNightVision implements IMessage, IMessageHandler<PacketNightVision, IMessage> {
    private boolean nv;

    public PacketNightVision() {

    }

    public PacketNightVision(boolean nv) {
        this.nv = nv;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(nv);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nv = buf.readBoolean();
    }

    @Override
    public IMessage onMessage(PacketNightVision message, MessageContext ctx) {
        IPlayerData data = ctx.getServerHandler().player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        data.setNV(message.nv);
        return null;
    }
}
