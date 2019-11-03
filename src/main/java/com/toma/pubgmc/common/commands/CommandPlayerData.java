package com.toma.pubgmc.common.commands;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class CommandPlayerData extends CommandBase {
    static final String[] subCmd =
            {
                    "backpackLevel",
                    "boost",
                    "nightVision",
                    "resetAll"
            };

    @Override
    public String getName() {
        return "playerdata";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/playerdata [player/all/me]" + subCmd[0] + " <value>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0)
            throw new WrongUsageException("Unknown operation, use /playerdata [player] <data> <value>", new Object[0]);

        else if (args.length > 1) {
            if (args[0].equalsIgnoreCase("me")) {
                if (sender instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) sender;

                    if (player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                        updateData(server, args, player, data, sender, true);
                    } else Pubgmc.logger.warn("Couldn't modify data of {}, no data found!", player.getName());
                }
            } else if (args[0].equalsIgnoreCase("all")) {
                for (EntityPlayer player : sender.getEntityWorld().playerEntities) {
                    if (player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                        updateData(server, args, player, data, sender, false);
                    } else Pubgmc.logger.warn("Couldn't modify data of {}, no data found!", player.getName());
                }
            } else if (server.getPlayerList().getPlayerByUsername(args[0]) != null) {
                EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);

                if (player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                    IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                    updateData(server, args, player, data, sender, true);
                } else Pubgmc.logger.warn("Couldn't modify data of {}, no data found!", player.getName());
            } else throw new WrongUsageException("Unknown target, try /playerdata <me,all,playerName>", new Object[0]);
        }
		
		/*for(String s : args)
		{
			sendFeedback(sender, s);
		}*/
    }

    private void updateData(MinecraftServer server, String[] args, EntityPlayer player, IPlayerData data, ICommandSender sender, boolean feedback) throws CommandException {
        switch (args[1]) {
            case "backpackLevel": {
                if (args.length > 1) {
                    if (args.length > 2 && PUBGMCUtil.isValidNumber(args[2])) {
                        final int num = Integer.parseInt(args[2]);

                        if (num >= 0 && num < 4) {
                            data.setBackpackLevel(num);
                            if (feedback) sendFeedback(sender, "Updated backpack level of " + player.getName());
                        } else if (feedback) {
                            sendFeedback(sender, TextFormatting.RED + "Number must be in range 0-3!");
                        }
                    } else if (feedback)
                        sendFeedback(sender, player.getName() + " has backpack level = " + data.getBackpackLevel());
                } else if (feedback)
                    sendFeedback(sender, player.getName() + " has backpack level = " + data.getBackpackLevel());
                break;
            }

            case "boost": {
                if (args.length > 1) {
                    if (args.length > 2 && PUBGMCUtil.isValidNumber(args[2])) {
                        final int num = Integer.parseInt(args[2]);

                        if (num >= 0 && num <= 100) {
                            data.setBoost(num);
                            if (feedback) sendFeedback(sender, "Updated boost level of " + player.getName());
                        } else if (feedback)
                            sendFeedback(sender, TextFormatting.RED + "Number must be in range 0-100!");
                    } else if (feedback)
                        sendFeedback(sender, player.getName() + " has boost level = " + data.getBoost());
                } else if (feedback) sendFeedback(sender, player.getName() + " has boost level = " + data.getBoost());
                break;
            }

            case "nightVision": {
                if (args.length > 1) {
                    if (args.length > 2 && (args[2].equalsIgnoreCase("false") || args[2].equalsIgnoreCase("true"))) {
                        boolean b = Boolean.parseBoolean(args[2]);

                        data.hasEquippedNV(b);
                        if (feedback)
                            sendFeedback(sender, "Updated state of night vision of " + player.getName() + " to " + b);
                    } else if (feedback) sendFeedback(sender, "Equipped NV: " + data.getEquippedNV());
                } else if (feedback) sendFeedback(sender, "Equipped NV: " + data.getEquippedNV());
                break;
            }

            case "resetAll": {
                data.setBackpackLevel(0);
                data.setBoost(0f);
                data.hasEquippedNV(false);

                if (feedback) sendFeedback(sender, "Data of " + player.getName() + " has been reset");
            }
        }

        //sync everything
        if (player instanceof EntityPlayerMP) {
            PacketHandler.syncPlayerDataToClient(data, (EntityPlayerMP) player);
        }
    }

    private void sendFeedback(ICommandSender sender, String message) {
        sender.sendMessage(new TextComponentString(message));
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }

        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, subCmd);
        }

        if (args.length == 3 && args[1].equalsIgnoreCase(subCmd[2])) {
            return getListOfStringsMatchingLastWord(args, "true", "false");
        }

        return Collections.EMPTY_LIST;
    }
}
