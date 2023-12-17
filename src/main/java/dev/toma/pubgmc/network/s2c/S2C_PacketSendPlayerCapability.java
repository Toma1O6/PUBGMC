package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.client.RenderHandler;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationType;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class S2C_PacketSendPlayerCapability implements IMessage {

    private UUID playerId;
    private NBTTagCompound nbt;

    public S2C_PacketSendPlayerCapability() {

    }

    public S2C_PacketSendPlayerCapability(UUID playerId, NBTTagCompound nbt) {
        this.playerId = playerId;
        this.nbt = nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
        buf.writeLong(playerId.getMostSignificantBits());
        buf.writeLong(playerId.getLeastSignificantBits());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
        playerId = new UUID(buf.readLong(), buf.readLong());
    }

    public static class Handler implements IMessageHandler<S2C_PacketSendPlayerCapability, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketSendPlayerCapability m, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                EntityPlayer target = mc.world.getPlayerEntityByUUID(m.playerId);
                if (target == null)
                    return;
                EntityPlayer self = mc.player;
                if (self == target) {
                    handleMyPlayerData(target, m.nbt);
                } else {
                    handleOtherPlayerData(target, m.nbt);
                }
            });
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handleOtherPlayerData(EntityPlayer player, NBTTagCompound nbtData) {
            PlayerDataProvider.getOptional(player).ifPresent(data -> data.deserializeNBT(nbtData));
        }

        @SideOnly(Side.CLIENT)
        private void handleMyPlayerData(EntityPlayer player, NBTTagCompound nbtData) {
            PlayerDataProvider.getOptional(player).ifPresent(data -> {
                boolean oldAim = data.getAimInfo().isAiming();
                boolean oldReload = data.getReloadInfo().isReloading();
                data.deserializeNBT(nbtData);
                boolean aim = data.getAimInfo().isAiming();
                boolean reload = data.getReloadInfo().isReloading();

                if (oldReload != reload && !reload) {
                    AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                }
                if (oldAim && !aim) {
                    RenderHandler.restore();
                }
            });
        }
    }
}
