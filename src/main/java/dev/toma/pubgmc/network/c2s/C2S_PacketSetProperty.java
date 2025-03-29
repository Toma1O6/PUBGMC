package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.api.capability.AimInfo;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.capability.ReloadInfo;
import dev.toma.pubgmc.common.capability.PlayerAimInfo;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.init.PMCSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.function.BiConsumer;

public class C2S_PacketSetProperty implements IMessage {

    private Action action;
    private boolean data;

    public C2S_PacketSetProperty() {
    }

    public C2S_PacketSetProperty(boolean data, Action action) {
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

    public static class Handler implements IMessageHandler<C2S_PacketSetProperty, IMessage> {
        @Override
        public IMessage onMessage(C2S_PacketSetProperty message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> message.action.handle(player, message.data));
            return null;
        }
    }

    public enum Action {
        AIM((player, aBoolean) -> {
            IPlayerData data = PlayerDataProvider.get(player);
            AimInfo info = data.getAimInfo();
            float speed = PlayerAimInfo.STOP_AIMING_SPEED;
            if (aBoolean) {
                ItemStack stack = player.getHeldItemMainhand();
                if (stack.getItem() instanceof GunBase) {
                    GunBase gunBase = (GunBase) stack.getItem();
                    speed *= gunBase.getAimSpeedMultiplier(stack);
                }
            }
            info.setAiming(aBoolean, speed);
            data.sync();
        }),
        RELOAD((player, aBoolean) -> {
            IPlayerData data = PlayerDataProvider.get(player);
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                ReloadInfo reloadInfo = data.getReloadInfo();
                GunBase gun = (GunBase) stack.getItem();
                if (aBoolean) {
                    reloadInfo.startReload(player, gun, stack);
                    SoundEvent event = gun.getWeaponReloadSound();
                    //TODO refactor getWeaponReloadSound
                    if (gun == PMCItems.KAR98K) {
                        int ammo = gun.getAmmo(stack);
                        if (ammo == 0) {
                            event = PMCSounds.reload_kar98k;
                        }
                    }
                    player.world.playSound(null, player.posX, player.posY + 1, player.posZ, event, SoundCategory.MASTER, 1.0F, 1.0F);
                } else {
                    reloadInfo.interrupt();
                }
            }
        }),
        NIGHT_VISION((player, aBoolean) -> {
            IPlayerData data = PlayerDataProvider.get(player);
            data.setNightVisionActive(aBoolean);
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
