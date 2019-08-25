package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.gui.GuiLootSetup;
import com.toma.pubgmc.common.capability.IWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDisplayGuiScreen implements IMessage {

    private int i;

    public PacketDisplayGuiScreen() {}

    public PacketDisplayGuiScreen(int i) {
        this.i = i;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(i);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        i = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketDisplayGuiScreen, IMessage> {
        @Override
        public IMessage onMessage(PacketDisplayGuiScreen message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.addScheduledTask(() -> {
                    ModContainer container = FMLCommonHandler.instance().findContainerFor(Pubgmc.instance);
                    Object obj = NetworkRegistry.INSTANCE.getLocalGuiContainer(container, mc.player, message.i, mc.world, 0, 0, 0);
                    if (obj instanceof GuiScreen) {
                        mc.displayGuiScreen((GuiScreen) obj);
                    }
                });
            }
            return null;
        }
    }
}
