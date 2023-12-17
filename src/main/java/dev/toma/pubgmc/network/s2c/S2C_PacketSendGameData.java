package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Optional;

public class S2C_PacketSendGameData implements IMessage {

    private NBTTagCompound nbt;

    public S2C_PacketSendGameData() {
    }

    public S2C_PacketSendGameData(NBTTagCompound nbt) {
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

    public static final class Handler implements IMessageHandler<S2C_PacketSendGameData, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendGameData message, MessageContext ctx) {
            Minecraft client = Minecraft.getMinecraft();
            WorldClient worldClient = client.world;
            client.addScheduledTask(() -> {
                Optional<GameData> optional = GameDataProvider.getGameData(worldClient);
                optional.ifPresent(data -> data.deserializeNBT(message.nbt));
            });
            return null;
        }
    }
}
