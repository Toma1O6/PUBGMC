package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.CommonEvents;
import dev.toma.pubgmc.config.ConfigPMC;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSaveConfig implements IMessage {

    private NBTTagCompound nbt;

    public PacketSaveConfig() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, ConfigPMC.common.serializeNBT());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<PacketSaveConfig, IMessage> {
        @Override
        public IMessage onMessage(PacketSaveConfig message, MessageContext ctx) {
            ctx.getServerHandler().player.getServer().addScheduledTask(() -> CommonEvents.CONFIGS.put(ctx.getServerHandler().player.getUniqueID(), message.nbt));
            return null;
        }
    }
}
