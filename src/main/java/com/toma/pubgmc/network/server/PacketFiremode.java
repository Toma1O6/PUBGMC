package com.toma.pubgmc.network.server;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFiremode implements IMessage {
    private Firemode mode;

    public PacketFiremode() {
    }

    public PacketFiremode(Firemode mode) {
        this.mode = mode;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mode.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mode = Firemode.values()[buf.readInt()];
    }

    public static class Handler implements IMessageHandler<PacketFiremode, IMessage> {
        @Override
        public IMessage onMessage(PacketFiremode message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() ->
            {
                GunBase gun = (GunBase) player.getHeldItemMainhand().getItem();
                gun.setFiremode(message.mode);
            });
            return null;
        }
    }
}
