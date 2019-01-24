package com.toma.pubgmc.common.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.capability.IWorldData.WorldDataProvider;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class CommandLootGenerate extends CommandBase
{
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
		IWorldData worldData = null;
		
		if(sender.getEntityWorld().hasCapability(WorldDataProvider.WORLD_DATA, null))
		{
			worldData = sender.getEntityWorld().getCapability(WorldDataProvider.WORLD_DATA, null);
			//if(worldData.getWeaponList().isEmpty()) worldData.resetWeaponLootGeneration();
		}
		
		if(args.length == 0)
		{
			throw new WrongUsageException("You must specify operation. Use /loot help", new Object[0]);
		}
		
		else if(args.length > 0)
		{
			executeBasic(server, sender, args, worldData);
		}
	}
	
	private void executeBasic(MinecraftServer server, ICommandSender sender, String[] args, IWorldData data)
	{
		World world = sender.getEntityWorld();
		if(args[0].equalsIgnoreCase("help"))
		{
			String[] messages = {
					"- generate >> will generate loot with current command settings",
					"- clear >> clear all loaded loot spawners",
					"- show >> fills all loaded loot spawners with loot to make it easier for spotting",
					"- info >> info about current loot setting",
					"- reset >> reset all current setting to default",
					"- set >> here you can set multiple things for loot generation"
					};
			
			for(int i = 0; i < messages.length; i++)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + messages[i]));
			}
		}
		
		else if(args[0].equalsIgnoreCase("info"))
		{
			sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Airdrop Weapons: ") + getTextColorFormatting(data.hasAirdropWeapons())));
			sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Ammo Loot: ") + getTextColorFormatting(data.isAmmoLootEnabled())));
			sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Random Ammo Count: ") + getTextColorFormatting(data.isRandomAmmoCountEnabled())));
			sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Weapons: ") + getColorBasedOnList(data.getWeaponList())));
			sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Chance Multiplier: ") + getNumberFormatting(data.getLootChanceMultiplier()) + "x"));
		}
		
		else if(args[0].equalsIgnoreCase("reset"))
		{
			data.toggleAirdropWeapons(false);
			data.toggleAmmoLoot(true);
			data.toggleRandomAmmoCount(false);
			resetWeapons(data);
			data.setLootChanceMultiplier(1d);
			if(shouldSendCommandFeedback(world.getGameRules()))
				sender.sendMessage(new TextComponentString("Reseting all values..."));
		}
		
		else if(args[0].equalsIgnoreCase("generate"))
		{
			int count = 0;
			for(TileEntity te : sender.getEntityWorld().loadedTileEntityList)
			{
				if(te instanceof TileEntityLootSpawner)
				{
					count++;
					
					((TileEntityLootSpawner)te).generateLoot(data.hasAirdropWeapons(), data.isAmmoLootEnabled(), data.isRandomAmmoCountEnabled(), data.getLootChanceMultiplier(), data.getWeaponList());
					world.notifyBlockUpdate(te.getPos(), world.getBlockState(te.getPos()), world.getBlockState(te.getPos()), 3);
				}
			}
			
			if(shouldSendCommandFeedback(world.getGameRules()))
			{
				if(count == 0) sender.sendMessage(new TextComponentString(TextFormatting.RED + "Couldn't locate any loot spawners, try again."));
				else sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Generated loot inside " + TextFormatting.YELLOW + count + TextFormatting.GREEN + " loot spawners"));
			}
		}
		
		else if(args[0].equalsIgnoreCase("clear"))
		{
			for(TileEntity te : sender.getEntityWorld().loadedTileEntityList)
			{
				if(te instanceof TileEntityLootSpawner)
				{
					((TileEntityLootSpawner)te).clear();
					world.notifyBlockUpdate(te.getPos(), world.getBlockState(te.getPos()), world.getBlockState(te.getPos()), 3);
				}
			}
			
			if(shouldSendCommandFeedback(world.getGameRules()))
				sender.sendMessage(new TextComponentString("Cleared loot inside all loaded loot spawners"));
		}
		
		else if(args[0].equalsIgnoreCase("show"))
		{
			int counter = 0;
			for(TileEntity te : sender.getEntityWorld().loadedTileEntityList)
			{
				if(te instanceof TileEntityLootSpawner)
				{
					counter++;
					for(int i = 0; i < ((TileEntityLootSpawner)te).getSizeInventory(); i++)
					{
						((TileEntityLootSpawner)te).setInventorySlotContents(i, new ItemStack(PMCItems.IBLOCK));
						world.notifyBlockUpdate(te.getPos(), world.getBlockState(te.getPos()), world.getBlockState(te.getPos()), 3);
					}
				}
			}
			
			if(shouldSendCommandFeedback(world.getGameRules()))
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Currently showing " + TextFormatting.YELLOW + counter + TextFormatting.GREEN + " loot spawners"));
		}
		
		else if(args[0].equalsIgnoreCase("set"))
		{
			if(args.length < 2)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set help"));
				return;
			}
			
			if(args[1].equalsIgnoreCase("help"))
			{
				String[] help = {
					"airdropLoot >> Enable/Disable airdrop weapons in loot spawners",
					"randomAmmoCount >> Enable/Disable random ammo ammount in loot spawners",
					"ammoLoot >> Enable/Disable generating ammo for spawned weapon",
					"chance [chanceAsNumber] >> Chance multiplier for better weapons, 1 = default",
					"weapon >> use '/loot set weapon help' for more info"
				};
				
				for(int i = 0; i < help.length; i++)
				{
					sender.sendMessage(new TextComponentString(TextFormatting.GREEN + help[i]));
				}
			}
			
			else if(args[1].equalsIgnoreCase("airdropLoot"))
			{
				data.toggleAirdropWeapons(!data.hasAirdropWeapons());
				if(shouldSendCommandFeedback(world.getGameRules()))
					sender.sendMessage(new TextComponentString("Airdrop loot: " + data.hasAirdropWeapons()));
			}
			
			else if(args[1].equalsIgnoreCase("randomAmmoCount"))
			{
				data.toggleRandomAmmoCount(!data.isRandomAmmoCountEnabled());
				if(shouldSendCommandFeedback(world.getGameRules()))
					sender.sendMessage(new TextComponentString("Random ammo count: " + data.isRandomAmmoCountEnabled()));
			}
			
			else if(args[1].equalsIgnoreCase("ammoLoot"))
			{
				data.toggleAmmoLoot(!data.isAmmoLootEnabled());
				if(shouldSendCommandFeedback(world.getGameRules()))
					sender.sendMessage(new TextComponentString("Ammo loot: " + data.isAmmoLootEnabled()));
			}
			
			else if(args[1].equalsIgnoreCase("chance"))
			{
				if(args.length < 3)
				{
					sender.sendMessage(new TextComponentString(TextFormatting.RED + "You must enter valid chance value!"));
					return;
				}
				
				if(!isStringDouble(args[2]))
				{
					sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid value!"));
				}
				
				else 
				{
					data.setLootChanceMultiplier(Double.parseDouble(args[2]));
					if(shouldSendCommandFeedback(world.getGameRules()))
						sender.sendMessage(new TextComponentString("Chance multiplier: " + data.getLootChanceMultiplier()));
				}
			}
			
			else if(args[1].equalsIgnoreCase("weapon"))
			{
				handleWeaponLoot(args, server, sender, data);
			}
			
			else
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set help"));
			}
		}
		
		else
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot help"));
		}
	}
	
	private void handleWeaponLoot(String[] args, MinecraftServer server, ICommandSender sender, IWorldData data)
	{
		if(args.length < 3)
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set weapon help"));
			return;
		}
		
		else if(args[2].equalsIgnoreCase("help"))
		{
			String[] help = 
			{
				"- get >> Returns all weapon types which will spawn",
				"- add [weaponType] >> Adds weapon type into loot generation",
				"- remove [weaponType] >> Removes weapon type from loot generation",
				"- reset >> Adds all weapons into loot generation - Default setting"
			};
			
			for(int i = 0; i < help.length; i++)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + help[i]));
			}
		}
		
		else if(args[2].equalsIgnoreCase("add"))
		{
			if(args.length < 4)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set weapon help"));
				return;
			}
			
			switch(args[3])
			{
				case "pistol":
				{
					if(!data.getWeaponList().contains(GunType.PISTOL)) 
					{
						data.addWeaponTypeToLootGeneration(GunType.PISTOL);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Added pistols into loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is already registered in loot generation!"));
					break;
				}
				
				case "smg":
				{
					if(!data.getWeaponList().contains(GunType.SMG))
					{
						data.addWeaponTypeToLootGeneration(GunType.SMG);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Added SMGs into loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is already registered in loot generation!"));
					break;
				}
				
				case "shotgun":
				{
					if(!data.getWeaponList().contains(GunType.SHOTGUN))
					{
						data.addWeaponTypeToLootGeneration(GunType.SHOTGUN);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Added shotguns into loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is already registered in loot generation!"));
					break;
				}
				
				case "ar":
				{
					if(!data.getWeaponList().contains(GunType.AR)) 
					{
						data.addWeaponTypeToLootGeneration(GunType.AR);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Added assault rifles into loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is already registered in loot generation!"));
					break;
				}
				
				case "sniper":
				{
					if(!data.getWeaponList().contains(GunType.SNIPER))
					{
						data.addWeaponTypeToLootGeneration(GunType.SNIPER);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Added sniper rifles into loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is already registered in loot generation!"));
					break;
				}
				
				default:
				{
					sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown weapon type! Valid weapons types: [pistol, shotgun, smg, ar, sniper]."));
					break;
				}
			}
		}
		
		else if(args[2].equalsIgnoreCase("remove"))
		{
			if(args.length < 4)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set weapon help"));
				return;
			}
			
			switch(args[3])
			{
				case "pistol":
				{
					if(data.getWeaponList().contains(GunType.PISTOL)) 
					{
						data.removeWeaponTypeFromLootGeneration(GunType.PISTOL);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed pistols from loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is not registered in loot generation!"));
					break;
				}
				
				case "smg":
				{
					if(data.getWeaponList().contains(GunType.SMG))
					{
						data.removeWeaponTypeFromLootGeneration(GunType.SMG);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed SMGs from loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is not registered in loot generation!"));
					break;
				}
				
				case "shotgun":
				{
					if(data.getWeaponList().contains(GunType.SHOTGUN))
					{
						data.removeWeaponTypeFromLootGeneration(GunType.SHOTGUN);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed shotguns from loot generation!"));
					} 
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is not registered in loot generation!"));
					break;
				}
				
				case "ar":
				{
					if(data.getWeaponList().contains(GunType.AR)) 
					{
						data.removeWeaponTypeFromLootGeneration(GunType.AR);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed assault rifles from loot generation!"));
					}
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is not registered in loot generation!"));
					break;
				}
				
				case "sniper":
				{
					if(data.getWeaponList().contains(GunType.SNIPER)) 
					{
						data.removeWeaponTypeFromLootGeneration(GunType.SNIPER);
						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed sniper rifles from loot generation!"));
					} 
					else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Weapon type is not registered in loot generation!"));
					break;
				}
				
				default:
				{
					sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown weapon type! Valid weapons types: [pistol, shotgun, smg, ar, sniper]."));
					break;
				}
			}
		}
		
		else if(args[2].equalsIgnoreCase("get"))
		{
			sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Weapon types:"));
			for(GunType type : data.getWeaponList())
			{
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + " - " + type.name()));
			}
		}
		
		else if(args[2].equalsIgnoreCase("reset"))
		{
			data.resetWeaponLootGeneration();
			if(shouldSendCommandFeedback(sender.getEntityWorld().getGameRules()))
				sender.sendMessage(new TextComponentString("Resetting all weapons..."));
		}
		
		else
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot set weapon help"));
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		if(args.length == 1)
			return getListOfStringsMatchingLastWord(args, new String[] {"help","info","clear","generate","set","reset"});
		
		else if(args.length == 2)
		{
			if(args[0].equalsIgnoreCase("set"))
			{
				return getListOfStringsMatchingLastWord(args, new String[] {"airdropLoot","ammoLoot","randomAmmoCount","chance","weapon","help"});
			}
		}
		
		else if(args.length == 3)
		{
			if(args[1].equalsIgnoreCase("chance"))
			{
				return getListOfStringsMatchingLastWord(args, new String[] {"0.25","0.5","0.75","1","1.25","1.5","1.75"});
			}
			
			else if(args[1].equalsIgnoreCase("weapon"))
			{
				return getListOfStringsMatchingLastWord(args, new String[] {"help","get","add","remove","reset"});
			}
		}
		
		else if(args.length == 4)
		{
			if(args[2].equalsIgnoreCase("add") || args[2].equalsIgnoreCase("remove"))
			{
				return getListOfStringsMatchingLastWord(args, new String[] {"pistol","shotgun","smg","ar","sniper"});
			}
		}
		
		return Collections.EMPTY_LIST;
	}
	
	private boolean isStringDouble(String text)
	{
		char[] c = text.toCharArray();
		boolean valid = true;
		boolean alreadyUsedDot = false;
		for(int i = 0; i < c.length; i++)
		{
			if(Character.isDigit(c[i]) || c[i] == '.')
			{	
				if(alreadyUsedDot && c[i] == '.')
				{
					valid = false;
				}
				
				if(c[i] == '.' && !alreadyUsedDot)
				{
					alreadyUsedDot = true;
				}
				
				continue;
			}
			
			else
			{
				valid = false;
			}
		}
		
		return valid;
	}
	
	private void resetWeapons(IWorldData data)
	{
		data.resetWeaponLootGeneration();
	}
	
	private List<GunType> getWeaponsFromList(List<Integer> list)
	{
		List<GunType> wep = new ArrayList<GunType>();
		for(int i = 0; i < list.size(); i++)
		{
			int j = list.get(i);
			wep.add(GunType.values()[j]);
		}
		
		return wep;
	}
	
	private boolean shouldSendCommandFeedback(GameRules rules)
	{
		return rules.getBoolean("sendCommandFeedback");
	}
	
	private String getTextColorFormatting(boolean input)
	{
		return input ? TextFormatting.GREEN + "" + input : TextFormatting.RED + "" + input;
	}
	
	private String getNumberFormatting(double input)
	{
		if(input <= 0)
			return TextFormatting.RED + "" + input;
		else if(input > 0 && input < 1.5)
			return TextFormatting.YELLOW + "" + input;
		else if(input >= 1.5 && input < 5)
			return TextFormatting.GREEN + "" + input;
		else
			return TextFormatting.DARK_GREEN + "" + input;
	}
	
	private String getColorBasedOnList(List<GunType> list)
	{
		//Max size == GunType.values().lenght !!
		
		switch(list.size())
		{
			case 0: return TextFormatting.DARK_RED + "" + list;
			case 1: return TextFormatting.RED + "" + list;
			case 2: return TextFormatting.GOLD + "" + list;
			case 3: return TextFormatting.YELLOW + "" + list;
			case 4: return TextFormatting.GREEN + "" + list;
			default: return TextFormatting.DARK_GREEN + "" + list;
		}
	}
	
	private String getDefaultInfoFormat(String textToShow)
	{
		return TextFormatting.GRAY + textToShow;
	}
}
