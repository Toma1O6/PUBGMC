package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.client.gui.ExternalGuiEventListener;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2C_PacketSendExternalGuiEvent implements IMessage {

    public S2C_PacketSendExternalGuiEvent() {}

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static final class Handler implements IMessageHandler<S2C_PacketSendExternalGuiEvent, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendExternalGuiEvent message, MessageContext ctx) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                GuiScreen screen = minecraft.currentScreen;
                if (screen instanceof ExternalGuiEventListener) {
                    ((ExternalGuiEventListener) screen).handleUpdate();
                }
            });
            return null;
        }
    }
}
