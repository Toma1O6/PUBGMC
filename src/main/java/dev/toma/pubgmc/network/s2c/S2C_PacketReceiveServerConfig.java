package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Optional;

public class S2C_PacketReceiveServerConfig implements IMessage {

    private NBTTagCompound nbt;

    public S2C_PacketReceiveServerConfig() {
    }

    public S2C_PacketReceiveServerConfig(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<S2C_PacketReceiveServerConfig, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketReceiveServerConfig message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                ClientProxy.CONFIG_STORE = Optional.of(ConfigPMC.common.serializeNBT());
                ConfigPMC.common.deserializeNBT(message.nbt);
            });
            return null;
        }
    }
}
