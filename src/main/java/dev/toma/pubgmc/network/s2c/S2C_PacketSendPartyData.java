package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.api.capability.PartyData;
import dev.toma.pubgmc.api.capability.PartyDataProvider;
import dev.toma.pubgmc.api.client.DataListenerGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Optional;

public class S2C_PacketSendPartyData implements IMessage {

    private NBTTagCompound tag;

    public S2C_PacketSendPartyData(NBTTagCompound tag) {
        this.tag = tag;
    }

    public S2C_PacketSendPartyData() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tag = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<S2C_PacketSendPartyData, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendPartyData message, MessageContext ctx) {
            Minecraft client = Minecraft.getMinecraft();
            client.addScheduledTask(() -> {
                Optional<PartyData> optional = PartyDataProvider.getPartyData(client.world);
                optional.ifPresent(data -> {
                    data.deserializeNBT(message.tag);
                    GuiScreen screen = client.currentScreen;
                    if (screen instanceof DataListenerGui) {
                        ((DataListenerGui) screen).onPartyDataReceived(data);
                    }
                });
            });
            return null;
        }
    }
}
