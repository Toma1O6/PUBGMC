package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketReload implements IMessage, IMessageHandler<PacketReload, IMessage> {
    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(PacketReload message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().player;

        player.getServer().addScheduledTask(() ->
        {
            GunBase gun = (GunBase) player.getHeldItemMainhand().getItem();
            gun.getReloadType().handleReload(player);
        });
        return null;
    }
}
