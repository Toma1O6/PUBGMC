package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.network.s2c.S2C_OpenGameMenuGUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;

public class C2S_RequestGameMenuGUI implements IMessage {

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<C2S_RequestGameMenuGUI, S2C_OpenGameMenuGUI> {

        @SuppressWarnings("ConstantValue")
        @Override
        public S2C_OpenGameMenuGUI onMessage(C2S_RequestGameMenuGUI message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            MinecraftServer server = player.getServer();
            boolean admin;
            if (server instanceof IntegratedServer) {
                IntegratedServer integratedServer = (IntegratedServer) server;
                admin = Objects.equals(integratedServer.getServerOwner(), player.getName());
            } else {
                UserListOpsEntry entry = server.getPlayerList().getOppedPlayers().getEntry(player.getGameProfile());
                admin = entry != null && entry.getPermissionLevel() >= 2;
            }
            return new S2C_OpenGameMenuGUI(admin);
        }
    }
}
