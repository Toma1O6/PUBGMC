package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.common.entity.vehicles.EntityVehicle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_RequestVehicleEngineToggle implements IMessage {

    public C2S_RequestVehicleEngineToggle() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static final class Handler implements IMessageHandler<C2S_RequestVehicleEngineToggle, IMessage> {

        @Override
        public IMessage onMessage(C2S_RequestVehicleEngineToggle message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            playerMP.getServer().addScheduledTask(() -> {
                if (EntityVehicle.isDriver(playerMP)) {
                    EntityVehicle driveable = (EntityVehicle) playerMP.getRidingEntity();
                    driveable.toggleEngine();
                    driveable.sendClientData();
                }
            });
            return null;
        }
    }
}
