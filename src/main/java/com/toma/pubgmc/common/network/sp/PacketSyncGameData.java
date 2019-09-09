package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.common.capability.IGameData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncGameData implements IMessage {

    private NBTTagCompound data;

    public PacketSyncGameData() {

    }

    public PacketSyncGameData(IGameData gameData) {
        this.data = gameData != null ? gameData.serializeNBT() : new NBTTagCompound();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<PacketSyncGameData, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncGameData message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        mc.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).deserializeNBT(message.data);
                    }
                });
            }
            return null;
        }
    }
}
