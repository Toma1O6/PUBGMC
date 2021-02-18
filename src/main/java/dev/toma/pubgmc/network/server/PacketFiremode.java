package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFiremode implements IMessage {
    private GunBase.Firemode mode;

    public PacketFiremode() {
    }

    public PacketFiremode(GunBase.Firemode mode) {
        this.mode = mode;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mode.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mode = GunBase.Firemode.values()[buf.readInt()];
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
