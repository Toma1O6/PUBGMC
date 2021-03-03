package dev.toma.pubgmc.network.sp;

import dev.toma.pubgmc.util.handlers.FlashHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketUpdateFlashStatus implements IMessage {

    boolean blind;

    public PacketUpdateFlashStatus() {
    }

    public PacketUpdateFlashStatus(boolean blind) {
        this.blind = blind;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(blind);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blind = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketUpdateFlashStatus, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketUpdateFlashStatus message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> FlashHandler.ClientHandler.update(message.blind));
            return null;
        }
    }
}
