package dev.toma.pubgmc.network.client;

import dev.toma.pubgmc.api.capability.IPlayerData;
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

public class PacketClientCapabilitySync implements IMessage {

    private UUID player;
    private NBTTagCompound nbt;

    public PacketClientCapabilitySync() {

    }

    public PacketClientCapabilitySync(UUID player, NBTTagCompound nbt) {
        this.player = player;
        this.nbt = nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
        ByteBufUtils.writeUTF8String(buf, player.toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
        player = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    public static class Handler implements IMessageHandler<PacketClientCapabilitySync, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketClientCapabilitySync m, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                EntityPlayer target = mc.world.getPlayerEntityByUUID(m.player);
                if (target == null)
                    return;
                IPlayerData data = PlayerDataProvider.get(target);
                boolean oldAim = data.getAimInfo().isAiming();
                boolean oldReload = data.getReloadInfo().isReloading();
                data.deserializeNBT(m.nbt);
                boolean newAim = data.getAimInfo().isAiming();
                boolean newReload = data.getReloadInfo().isReloading();

                if (oldReload != newReload && !newReload) {
                    AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                }
                if (oldAim && !newAim) {
                    RenderHandler.restore();
                }
            });
            return null;
        }
    }
}
