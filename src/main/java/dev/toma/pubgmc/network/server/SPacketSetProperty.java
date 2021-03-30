package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.capability.player.AimInfo;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.ReloadInfo;
import dev.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.function.BiConsumer;

public class SPacketSetProperty implements IMessage {

    private Action action;
    private boolean data;

    public SPacketSetProperty() {}

    public SPacketSetProperty(boolean data, Action action) {
        this.data = data;
        this.action = action;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(data);
        buf.writeInt(action.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = buf.readBoolean();
        action = Action.values()[buf.readInt()];
    }

    public static class Handler implements IMessageHandler<SPacketSetProperty, IMessage> {
        @Override
        public IMessage onMessage(SPacketSetProperty message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> message.action.handle(player, message.data));
            return null;
        }
    }

    public enum Action {
        AIM((player, aBoolean) -> {
            IPlayerData data = PlayerData.get(player);
            AimInfo info = data.getAimInfo();
            info.setAiming(aBoolean, AimInfo.STOP_AIMING_SPEED);
            data.sync();
        }),
        RELOAD((player, aBoolean) -> {
            IPlayerData data = PlayerData.get(player);
            ItemStack stack = player.getHeldItemMainhand();
            if(stack.getItem() instanceof GunBase) {
                ReloadInfo reloadInfo = data.getReloadInfo();
                GunBase gun = (GunBase) stack.getItem();
                if(aBoolean) {
                    reloadInfo.startReload(player, gun, stack);
                } else {
                    reloadInfo.interrupt(data);
                }
            }
        }),
        NIGHT_VISION((player, aBoolean) -> {
            IPlayerData data = PlayerData.get(player);
            data.setNV(aBoolean);
            data.sync();
        });

        final BiConsumer<EntityPlayer, Boolean> action;

        Action(BiConsumer<EntityPlayer, Boolean> action) {
            this.action = action;
        }

        public void handle(EntityPlayer player, boolean data) {
            action.accept(player, data);
        }
    }
}
