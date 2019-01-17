package com.toma.pubgmc.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandLootGenerate extends CommandBase
{
	private boolean airdropLoot = false, randomAmmoLoot = false, ammoLoot = true;
	private int weapons = 0;
	private double chance = 1d;
	
	@Override
	public String getName()
	{
		return "loot";
	}
	
	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "/loot";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(args.length == 0)
		{
			throw new WrongUsageException("You must specify operation. Use /loot help", new Object[0]);
		}
		
		else if(args.length == 1)
		{
			executeBasic(server, sender, args);
		}
	}
	
	private void executeBasic(MinecraftServer server, ICommandSender sender, String[] args)
	{
		if(args[0].equalsIgnoreCase("help"))
		{
			//TODO more
			String[] messages = {
					"- generate >> will generate loot with current command settings",
					"- info >> info about current loot setting",
					"- reset >> reset all current setting to default"
					};
			
			for(int i = 0; i < messages.length; i++)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + messages[i]));
			}
		}
		
		else if(args[0].equalsIgnoreCase("info"))
		{
			sender.sendMessage(new TextComponentString("Airdrop Loot: " + airdropLoot));
			sender.sendMessage(new TextComponentString("Ammo Loot: " + ammoLoot));
			sender.sendMessage(new TextComponentString("Random Ammo Count: " + randomAmmoLoot));
			sender.sendMessage(new TextComponentString("Weapons: " + weapons));
			sender.sendMessage(new TextComponentString("Chance Multiplier: " + "x" + chance));
		}
		
		else if(args[0].equalsIgnoreCase("reset"))
		{
			airdropLoot = false;
			ammoLoot = true;
			randomAmmoLoot = false;
			weapons = 0;
			chance = 1d;
			sender.sendMessage(new TextComponentString("Reseting all values..."));
		}
	}
}
