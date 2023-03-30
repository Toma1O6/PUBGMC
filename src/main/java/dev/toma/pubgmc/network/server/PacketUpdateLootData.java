package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.capability.IWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateLootData implements IMessage {

    private NBTTagCompound nbt;

    public PacketUpdateLootData() {}

    public PacketUpdateLootData(NBTTagCompound nbt) {
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

    public static class Handler implements IMessageHandler<PacketUpdateLootData, IMessage> {
        @Override
        public IMessage onMessage(PacketUpdateLootData message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                if (player.getServer().getPlayerList().canSendCommands(player.getGameProfile())) {
                    player.world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null).deserializeNBT(message.nbt);
                }
            });
            return null;
        }
    }
}
