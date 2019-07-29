package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetScopeVariants implements IMessage, IMessageHandler<PacketSetScopeVariants, IMessage> {
    private int scopetype, scopecolor;

    public PacketSetScopeVariants() {

    }

    public PacketSetScopeVariants(int scopeType, int scopecolor) {
        this.scopetype = scopeType;
        this.scopecolor = scopecolor;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(scopetype);
        buf.writeInt(scopecolor);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        scopetype = buf.readInt();
        scopecolor = buf.readInt();
    }

    @Override
    public IMessage onMessage(PacketSetScopeVariants message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        player.getServer().addScheduledTask(() ->
        {
            if (player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                data.setScopeType(message.scopetype);
                data.setScopeColor(message.scopecolor);
            }
        });
        return null;
    }
}
