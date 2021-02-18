package dev.toma.pubgmc.network.sp;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncEntity implements IMessage {

    private int entityID;
    private NBTTagCompound nbt;

    public PacketSyncEntity() {

    }

    public PacketSyncEntity(Entity entity) {
        this.entityID = entity.getEntityId();
        this.nbt = entity.writeToNBT(new NBTTagCompound());
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

    public static class Handler implements IMessageHandler<PacketSyncEntity, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncEntity message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    WorldClient client = Minecraft.getMinecraft().world;
                    Entity entity = client.getEntityByID(message.entityID);
                    if(entity != null) {
                        entity.readFromNBT(message.nbt);
                    }
                });
            }
            return null;
        }
    }
}
