package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleSoundController;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.IntConsumer;

public class S2C_VehicleSoundEvent implements IMessage {

    private boolean playing;
    private int vehicle;
    private int event;

    public S2C_VehicleSoundEvent() {
    }

    public S2C_VehicleSoundEvent(boolean playing, int vehicle, int event) {
        this.playing = playing;
        this.vehicle = vehicle;
        this.event = event;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.playing);
        buf.writeInt(this.vehicle);
        buf.writeInt(this.event);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playing = buf.readBoolean();
        this.vehicle = buf.readInt();
        this.event = buf.readInt();
    }

    public static final class Handler implements IMessageHandler<S2C_VehicleSoundEvent, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(S2C_VehicleSoundEvent message, MessageContext ctx) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                Entity entity = minecraft.world.getEntityByID(message.vehicle);
                if (entity instanceof EntityDriveable) {
                    EntityDriveable driveable = (EntityDriveable) entity;
                    VehicleSoundController controller = driveable.getSoundController();
                    IntConsumer event = message.playing ? controller::play : controller::stop;
                    event.accept(message.event);
                }
            });
            return null;
        }
    }
}
