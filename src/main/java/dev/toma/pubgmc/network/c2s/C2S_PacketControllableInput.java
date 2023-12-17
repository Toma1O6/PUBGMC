package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.api.entity.IControllable;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketControllableInput implements IMessage {

    int entity;
    int rawInput;

    public C2S_PacketControllableInput() {

    }

    public <T extends Entity & IControllable> C2S_PacketControllableInput(T controllableEntity, int rawInput) {
        this.entity = controllableEntity.getEntityId();
        this.rawInput = rawInput;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entity);
        buf.writeInt(rawInput);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entity = buf.readInt();
        rawInput = buf.readInt();
    }

    public static class Handler implements IMessageHandler<C2S_PacketControllableInput, IMessage> {

        @Override
        public IMessage onMessage(C2S_PacketControllableInput message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                WorldServer world = player.getServerWorld();
                Entity entity = world.getEntityByID(message.entity);
                if (entity.getControllingPassenger() == player && entity instanceof IControllable) {
                    ((IControllable) entity).handle((byte) message.rawInput);
                }
            });
            return null;
        }
    }
}
