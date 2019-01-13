package com.toma.pubgmc.common.commands;

import org.apache.logging.log4j.Level;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketUpdatePlayerData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextComponentString;

public class CommandResetPlayerData extends CommandBase
{
	@Override
	public String getName()
	{
		return "resetplayerdata";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/resetplayerdata";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(args.length == 0)
		{
			PlayerList players = server.getPlayerList();
			for(int i = 0; i < players.getPlayers().size(); i++)
			{
				EntityPlayerMP player = players.getPlayers().get(i);
				
				if(player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
				{
					IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
					
					data.setBackpackLevel(0);
					data.hasEquippedNV(false);
					data.setBoost(0f);
					data.setCookingTime(0);
					data.setGrenadeCooking(false);
					
					PacketHandler.INSTANCE.sendTo(new PacketUpdatePlayerData(), player);
					System.out.println(data.isUsingNV() + " ");
					
					Pubgmc.logger.log(Level.INFO, "Removed data for " + player.getName());
				}
				
				else Pubgmc.logger.log(Level.WARN, "Couldn't find data for" + player.getName());
			}
			
			sender.sendMessage(new TextComponentString("Data for players has been reset!"));
		}
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
		return 2;
	}
}
