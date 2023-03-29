package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.common.capability.IGameData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CommandLeave extends CommandBase {

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Teleports you back to lobby.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 0) {
            World world = sender.getEntityWorld();
            Entity e = sender.getCommandSenderEntity();
            if (e instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e;
                Lobby lobby = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getLobby();
                BlockPos pos = null;
                if (lobby == null) {
                    warnPlayer(player);
                    pos = lobby.center;
                } else if (world.getSpawnPoint() == null) {
                    warnPlayer(player);
                    return;
                }
                if (pos == null) pos = world.getSpawnPoint();
                player.attemptTeleport(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                player.sendMessage(new TextComponentString(TextFormatting.GREEN + "You have been teleported back to lobby."));
            }

        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    private void warnPlayer(EntityPlayer player) {
        player.sendMessage(new TextComponentString(TextFormatting.RED + "Unable to locate lobby/world spawn. Create new: /setworldspawn!"));
    }
}
