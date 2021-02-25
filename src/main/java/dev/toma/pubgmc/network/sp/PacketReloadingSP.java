package dev.toma.pubgmc.network.sp;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketReloadingSP implements IMessage {
    private boolean reloading;

    public PacketReloadingSP() {

    }

    public PacketReloadingSP(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(reloading);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        reloading = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketReloadingSP, IMessage> {
        @Override
        public IMessage onMessage(PacketReloadingSP message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                data.setReloading(message.reloading);
            }

            return null;
        }
    }
}
