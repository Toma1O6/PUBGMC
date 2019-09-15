package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.entity.EntityParachute;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleParachuteInputs implements IMessage {
    private boolean pitchUp, pitchDown, right, left;

    public PacketHandleParachuteInputs() {
    }

    public PacketHandleParachuteInputs(boolean pitchUp, boolean pitchDown, boolean right, boolean left) {
        this.pitchDown = pitchDown;
        this.pitchUp = pitchUp;
        this.right = right;
        this.left = left;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(pitchUp);
        buf.writeBoolean(pitchDown);
        buf.writeBoolean(right);
        buf.writeBoolean(left);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pitchUp = buf.readBoolean();
        pitchDown = buf.readBoolean();
        right = buf.readBoolean();
        left = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketHandleParachuteInputs, IMessage> {
        @Override
        public IMessage onMessage(PacketHandleParachuteInputs message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;

            player.getServer().addScheduledTask(() -> {
                if (player.getRidingEntity() instanceof EntityParachute) {
                    EntityParachute chute = (EntityParachute) player.getRidingEntity();

                    chute.handleInputs(message.pitchDown, message.pitchUp, message.right, message.left);
                }
            });
            return null;
        }
    }
}
