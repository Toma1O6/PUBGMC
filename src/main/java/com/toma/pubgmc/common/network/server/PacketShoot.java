package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.items.guns.GunBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketShoot implements IMessage {

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PacketShoot, IMessage> {
        @Override
        public IMessage onMessage(PacketShoot message, MessageContext ctx) {
            World world = ctx.getServerHandler().player.world;
            EntityPlayer player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> handle(player, world));
            return null;
        }

        private void handle(EntityPlayer player, World world) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                ((GunBase) stack.getItem()).shoot(world, player, stack);
            }
        }
    }
}
