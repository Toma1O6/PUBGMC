package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.config.ConfigPMC;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketLoadConfig implements IMessage {

    private NBTTagCompound nbt;

    public PacketLoadConfig() {}

    public PacketLoadConfig(NBTTagCompound nbt) {
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

    public static class Handler implements IMessageHandler<PacketLoadConfig, IMessage> {

        @Override
        public IMessage onMessage(PacketLoadConfig message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                Minecraft.getMinecraft().addScheduledTask(() -> ConfigPMC.common.deserializeNBT(message.nbt));
            }
            return null;
        }
    }
}
