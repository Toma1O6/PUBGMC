package dev.toma.pubgmc.network.sp;

import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.util.PUBGMCUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCreateNBT implements IMessage {
    private int ammo;

    public PacketCreateNBT() {
    }

    public PacketCreateNBT(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ammo);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketCreateNBT, IMessage> {
        @Override
        public IMessage onMessage(PacketCreateNBT message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Minecraft mc = Minecraft.getMinecraft();
                EntityPlayerSP player = mc.player;
                mc.addScheduledTask(() -> {
                    ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
                    if (!heldItem.hasTagCompound() && heldItem.getItem() instanceof GunBase) {
                        PUBGMCUtil.createWeaponNBT(heldItem, message.ammo);
                    }
                });
            }
            return null;
        }
    }
}
