package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_RequestDriveableEngineToggle implements IMessage {

    public C2S_RequestDriveableEngineToggle() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static final class Handler implements IMessageHandler<C2S_RequestDriveableEngineToggle, IMessage> {

        @Override
        public IMessage onMessage(C2S_RequestDriveableEngineToggle message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            playerMP.getServer().addScheduledTask(() -> {
                if (EntityDriveable.isDriver(playerMP)) {
                    EntityDriveable driveable = (EntityDriveable) playerMP.getRidingEntity();
                    driveable.toggleEngine();
                    driveable.sendClientData();
                }
            });
            return null;
        }
    }
}
