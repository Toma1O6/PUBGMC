package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPacketFiremode implements IMessage {

    public SPacketFiremode() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<SPacketFiremode, IMessage> {
        @Override
        public IMessage onMessage(SPacketFiremode message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                ItemStack stack = player.getHeldItemMainhand();
                if (stack.getItem() instanceof GunBase) {
                    GunBase gun = (GunBase) stack.getItem();
                    gun.setFiremode(stack, gun.switchFiremode(stack));
                }
            });
            return null;
        }
    }
}
