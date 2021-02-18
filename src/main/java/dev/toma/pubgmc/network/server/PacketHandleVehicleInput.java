package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.EntityVehicle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleVehicleInput implements IMessage {
    boolean forward, back, right, left;
    int id;

    public PacketHandleVehicleInput() {
    }

    public PacketHandleVehicleInput(boolean forward, boolean back, boolean right, boolean left, int entityID) {
        this.forward = forward;
        this.back = back;
        this.right = right;
        this.left = left;
        this.id = entityID;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(forward);
        buf.writeBoolean(back);
        buf.writeBoolean(right);
        buf.writeBoolean(left);
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        forward = buf.readBoolean();
        back = buf.readBoolean();
        right = buf.readBoolean();
        left = buf.readBoolean();
        id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketHandleVehicleInput, IMessage> {
        @Override
        public IMessage onMessage(PacketHandleVehicleInput message, MessageContext ctx) {
            ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
                World world = ctx.getServerHandler().player.world;
                if (world.getEntityByID(message.id) instanceof EntityVehicle) {
                    EntityPlayer player = ctx.getServerHandler().player;
                    EntityVehicle car = (EntityVehicle) world.getEntityByID(message.id);
                    car.handleInputs(message.forward, message.back, message.right, message.left, player);
                } else
                    Pubgmc.logger.warn("Received packet for EntityVehicle, id {}, but couldn't locate the entity.", message.id);
            });
            return null;
        }
    }
}
