package dev.toma.pubgmc.network.client;

import dev.toma.pubgmc.client.RenderHandler;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
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

// TODO improve player serialization
public class PacketClientCapabilitySync implements IMessage {

    private EntityPlayer player;
    private NBTTagCompound nbt;

    public PacketClientCapabilitySync() {

    }

    public PacketClientCapabilitySync(EntityPlayer player, NBTTagCompound nbt) {
        this.player = player;
        this.nbt = nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
        ByteBufUtils.writeUTF8String(buf, player.getGameProfile().getId().toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (Minecraft.getMinecraft().player == null)
            return;
        nbt = ByteBufUtils.readTag(buf);
        player = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(UUID.fromString(ByteBufUtils.readUTF8String(buf)));
    }

    public static class Handler implements IMessageHandler<PacketClientCapabilitySync, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketClientCapabilitySync m, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                IPlayerData data = PlayerData.get(m.player);
                data.deserializeNBT(m.nbt);

                if (!data.isReloading()) {
                    AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                }
                if (!data.getAimInfo().isAiming()) {
                    mc.gameSettings.fovSetting = RenderHandler.fovBackup;
                    mc.gameSettings.mouseSensitivity = RenderHandler.sensBackup;
                }
            });
            return null;
        }
    }
}
