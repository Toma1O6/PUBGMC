package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketOpenPlayerEquipment implements IMessage {

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static final class Handler implements IMessageHandler<C2S_PacketOpenPlayerEquipment, IMessage> {

        @Override
        public IMessage onMessage(C2S_PacketOpenPlayerEquipment message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                if (player.isEntityAlive()) {
                    player.openGui(Pubgmc.instance, GuiHandler.GUI_PLAYER_EQUIPMENT, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
                }
            });
            return null;
        }
    }
}
