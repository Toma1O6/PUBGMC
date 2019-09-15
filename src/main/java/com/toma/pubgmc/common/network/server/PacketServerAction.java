package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketServerAction implements IMessage {

    private ServerAction action;

    public PacketServerAction() {}

    public PacketServerAction(ServerAction action) {
        this.action = action;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(action.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        action = ServerAction.values()[buf.readInt()];
    }

    public static class Handler implements IMessageHandler<PacketServerAction, IMessage> {
        @Override
        public IMessage onMessage(PacketServerAction message, MessageContext ctx) {
            ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
                switch (message.action) {
                    case AIM: {
                        IPlayerData data = ctx.getServerHandler().player.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
                        data.setAiming(!data.isAiming());
                        break;
                    }

                    case RELOAD: {
                        EntityPlayer player = ctx.getServerHandler().player;
                        GunBase gun = player.getHeldItemMainhand().getItem() instanceof GunBase ? (GunBase)player.getHeldItemMainhand().getItem() : null;
                        if(gun != null) {
                            gun.getReloadType().handleReload(player);
                        }
                        break;
                    }

                    case NIGHT_VISION: {
                        IPlayerData data = ctx.getServerHandler().player.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
                        data.setNV(!data.isUsingNV());
                        break;
                    }
                }
            });
            return null;
        }
    }

    public enum ServerAction {
        AIM,
        RELOAD,
        NIGHT_VISION
    }
}
