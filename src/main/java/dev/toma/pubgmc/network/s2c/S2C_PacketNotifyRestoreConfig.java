package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2C_PacketNotifyRestoreConfig implements IMessage {

    public S2C_PacketNotifyRestoreConfig() {
    }

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<S2C_PacketNotifyRestoreConfig, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketNotifyRestoreConfig message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> ClientProxy.CONFIG_STORE.ifPresent(data -> ConfigPMC.common.deserializeNBT(data)));
            return null;
        }
    }
}
