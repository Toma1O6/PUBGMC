package dev.toma.pubgmc.network.sp;

import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.PacketSaveConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketGetConfigFromServer implements IMessage {

    private NBTTagCompound nbt;

    public PacketGetConfigFromServer() {}

    public PacketGetConfigFromServer(NBTTagCompound nbt) {
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

    public static class Handler implements IMessageHandler<PacketGetConfigFromServer, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketGetConfigFromServer message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                PacketHandler.sendToServer(new PacketSaveConfig());
                ConfigPMC.common.deserializeNBT(message.nbt);
            });
            return null;
        }
    }
}
