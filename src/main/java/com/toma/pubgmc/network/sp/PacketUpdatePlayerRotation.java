package com.toma.pubgmc.network.sp;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatePlayerRotation implements IMessage {
    float rotYaw;

    public PacketUpdatePlayerRotation() {

    }

    public PacketUpdatePlayerRotation(float rotYaw) {
        this.rotYaw = rotYaw;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(rotYaw);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        rotYaw = buf.readFloat();
    }

    public static class Handler implements IMessageHandler<PacketUpdatePlayerRotation, IMessage> {
        @Override
        public IMessage onMessage(PacketUpdatePlayerRotation message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayerSP player = Minecraft.getMinecraft().player;
                    player.rotationYaw = message.rotYaw;
                });
            }
            return null;
        }
    }
}
