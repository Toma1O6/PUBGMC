package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2C_PacketSendEntityData implements IMessage {

    private int entityID;
    private NBTTagCompound nbt;

    public S2C_PacketSendEntityData() {

    }

    public <T extends Entity & SynchronizableEntity> S2C_PacketSendEntityData(T entity) {
        this.entityID = entity.getEntityId();
        this.nbt = entity.encodeNetworkData();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeTag(buf, nbt);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        this.nbt = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<S2C_PacketSendEntityData, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendEntityData message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient client = Minecraft.getMinecraft().world;
                Entity entity = client.getEntityByID(message.entityID);
                if (entity instanceof SynchronizableEntity) {
                    ((SynchronizableEntity) entity).decodeNetworkData(message.nbt);
                }
            });
            return null;
        }
    }
}
