package com.toma.pubgmc.common.commands;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketChooseLocation;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

public class CommandGame extends CommandBase
{
	private static final String[] completions = {"help","stop","addLocation","clearLocations","setupMap","zone","removeLocation","locations","info"};
	
	@Override
	public String getName()
	{
		return "game";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/game";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		World world = sender.getEntityWorld();
		IGameData game = world.getCapability(GameDataProvider.GAMEDATA, null);
		
		if(args.length == 0) throw new WrongUsageException("Wrong command, use /game help", new Object[0]);
		
		if(args[0].equalsIgnoreCase("help"))
		{
			String[] gamehelp = 
			{
				"start >> starts the game",
				"stop >> stops the game",
				"reset >> resets the game",
				"addLocation [X,Y,Z,name] >> this will add a new spawn location to your map",
				"clearLocations >> removes all spawn locations from your map",
				"setupMap [mapCenterX, mapCenterZ, mapSize, zoneCount] >> border will be calculated based on these values",
				"zone >> Prints all zone parameters",
				"locations >> Prints all locations and their IDs",
				"removeLocation [ID] >> Removes location from map",
				"info >> All information about your map setup is here"
			};
			
			for(int i = 0; i < gamehelp.length; i++)
				sender.sendMessage(new TextComponentString(TextFormatting.GREEN + gamehelp[i]));
		}
		
		else if(args[0].equalsIgnoreCase("start"))
		{
			if(!game.isPlaying())
			{
				if(PUBGMCUtil.isMapSetupProperly(game))
				{
					game.setPlaying(true);
					startGame(game, world);
				}
				
				else sendWarningTo(sender, "Your map isn't setup properly, use /game setupMap [mapCenterX, mapCenterZ, mapSizeFromCenterToEdge, zoneCount]");
			}
			
			else sendWarningTo(sender, "Game is already running!");
		}
		
		else if(args[0].equalsIgnoreCase("stop"))
		{
			if(game.isPlaying())
			{
				game.setPlaying(false);
				stopGame(game, world);
				sendFeedback(world, sender, "Stopped the game");
			}
			
			else sendWarningTo(sender, "Game haven't started yet!");
		}
		
		else if(args[0].equalsIgnoreCase("reset"))
		{
			resetGame(game, world);
		}
		
		else if(args[0].equalsIgnoreCase("addLocation"))
		{
			if(args.length == 5 && PUBGMCUtil.isValidNumber(args[1]) && PUBGMCUtil.isValidNumber(args[2]) && PUBGMCUtil.isValidNumber(args[3]))
			{
				game.addSpawnLocation(new BlockPos(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])), args[4]);
				sendFeedback(world, sender, "Added location " + args[4] + " [" + args[1] + ", " + args[2] + ", " + args[3] + "]!");
			}
			
			else throw new WrongUsageException("You must specify the location position and name! /game addLocation [x,y,z,name]", new Object[0]);
		}
		
		else if(args[0].equalsIgnoreCase("clearLocations"))
		{
			game.getSpawnLocations().clear();
			game.setSpawnLocationCount(0);
			game.getLocationNames().clear();
			sendFeedback(world, sender, "Removed all locations from your map");
		}
		
		else if(args[0].equalsIgnoreCase("setupMap"))
		{
			if(args.length == 5 && PUBGMCUtil.isValidNumber(args[1]) && PUBGMCUtil.isValidNumber(args[2]) && PUBGMCUtil.isValidNumber(args[3]) && PUBGMCUtil.isValidNumber(args[4]))
			{
				game.setMapCenter(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
				sendFeedback(world, sender, TextFormatting.BOLD + "New map setup:");
				sendFeedback(world, sender, "Center: [" + args[1] + ", " + args[2] + "]");
				sendFeedback(world, sender, "Map size: " + args[3]);
				sendFeedback(world, sender, "Zone count: " + args[4]);
			}
			
			else throw new WrongUsageException("You must specify mapCenter and size! /game setupMap [posX, posZ, size, zoneCount]", new Object[0]);
		}
		
		else if(args[0].equalsIgnoreCase("zone"))
		{
			getZoneConfiguration(sender, game, world);
		}
		
		else if(args[0].equalsIgnoreCase("locations"))
		{
			if(game.getSpawnLocations().isEmpty())
			{
				sendWarningTo(sender, "There are no locations!");
				return;
			}
			
			for(int i = 0; i < game.getSpawnLocations().size(); i++)
			{
				BlockPos p = game.getSpawnLocations().get(i);
				sendFeedback(world, sender, "ID: " + i + "; Name: " + game.getLocationNames().get(i) + "; Position: " + p.getX() + "," + p.getY() + "," + p.getZ());
			}
		}
		
		else if(args[0].equalsIgnoreCase("removeLocation"))
		{
			if(args.length == 2 && PUBGMCUtil.isValidNumber(args[1]))
			{
				int ID = Integer.parseInt(args[1]);
				
				if(ID < game.getSpawnLocations().size())
				{
					String locName = game.getLocationNames().get(ID);
					BlockPos position = game.getSpawnLocations().get(ID);
					
					game.getSpawnLocations().remove(ID);
					game.setSpawnLocationCount(game.getSpawnLocationCount() - 1);
					game.getLocationNames().remove(ID);
					
					sendFeedback(world, sender, "Removed location " + locName + " with ID " + ID + " and position [" + position.getX() + ", " + position.getY() + ", " + position.getZ() + "] from this map");
				}
				
				else sendWarningTo(sender, "Location with ID " + ID + " doesn't exist!");
			}
			
			else throw new WrongUsageException("You must specify location ID!", new Object[0]);
		}
		
		else if(args[0].equalsIgnoreCase("info"))
		{
			sendMessage(sender, TextFormatting.GREEN, "Map information:");
			sendMessage(sender, TextFormatting.GREEN, "Map size: " + game.getMapSize());
			sendMessage(sender, TextFormatting.GREEN, "Map center: [x=" + game.getMapCenter().getX() + ", z=" + game.getMapCenter().getZ() + "]");
			sendMessage(sender, TextFormatting.GREEN, "Total zone phases: " + game.getZonePhaseCount());
		}
		
		else throw new WrongUsageException("Unknown operation! Try /game help for more info", new Object[0]);
	}
	
	private void getZoneConfiguration(ICommandSender sender, IGameData data, World world)
	{
		int zoneCount = data.getZonePhaseCount();
		int sizeMod = 100 / zoneCount;
		
		sendFeedback(world, sender, TextFormatting.GREEN + "Total zone phases: " + zoneCount);
		sendFeedback(world, sender, TextFormatting.GREEN + "====================================");
		
		for(int i = 1; i < zoneCount+1; i++)
		{
			double zoneArea = 100 - i*sizeMod + 5;
			sendFeedback(world, sender, TextFormatting.GREEN + "" + TextFormatting.BOLD + "Phase " + i + ":");
			sendFeedback(world, sender, TextFormatting.GREEN + "Final size : " + zoneArea + "%");
			sendFeedback(world, sender, TextFormatting.GREEN + "Map area: " + Math.abs(sqr(data.getMapSize()*2)) * (zoneArea/100) + " of " + sqr(data.getMapSize()*2) + " total blocks");
		}
	}
	
	private void sendWarningTo(ICommandSender commandSender, String message)
	{
		commandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Error: " + message));
	}
	
	private void sendFeedback(World world, ICommandSender sender, String feedback)
	{
		if(PUBGMCUtil.shouldSendCommandFeedback(world))
			sender.sendMessage(new TextComponentString(TextFormatting.GRAY + feedback));
	}
	
	private void sendMessage(ICommandSender sender, TextFormatting textFormat, String text)
	{
		sender.sendMessage(new TextComponentString(textFormat + text));
	}
	
	private void startGame(IGameData data, World world)
	{
		WorldBorder border = world.getWorldBorder();
		BlockPos center = data.getMapCenter();
		int size = data.getMapSize();
		EntityPlane plane = null;
		
		BlockPos zoneCenter = new BlockPos(generateZoneCenter(center, size, true), 256, generateZoneCenter(center, size, false));
		
		while(world.isAirBlock(zoneCenter))
		{
			zoneCenter = new BlockPos(zoneCenter.getX(), zoneCenter.getY() - 1, zoneCenter.getZ());
			if(world.getBlockState(zoneCenter).getMaterial().isLiquid())
			{
				Pubgmc.logger.info("Generating new zone center...");
				zoneCenter = new BlockPos(generateZoneCenter(center, size, true), 256, generateZoneCenter(center, size, false));
			}
		}
		
		border.setCenter(zoneCenter.getX() + 0.5, zoneCenter.getZ() + 0.5);
		
		int zoneSize = size*2;
		int rx = Math.abs(center.getX() - zoneCenter.getX());
		int rz = Math.abs(center.getZ() - zoneCenter.getZ());
		int bonus = (int) (rx > rz ? rx : rz);
		
		border.setTransition(zoneSize + bonus*2);
		border.setDamageBuffer(1);
		border.setDamageAmount((double)data.getCurrentZone() + 1 / (double)data.getZonePhaseCount());
		
		data.setTimer(0);
		
		if(PUBGMCUtil.isMapSetupProperly(data) && !data.getSpawnLocations().isEmpty())
			plane = spawnPlane(world, data);
		
		for(EntityPlayer player : world.playerEntities)
		{
			player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Available locations:"));
			if(data.getSpawnLocations().isEmpty()) player.sendMessage(new TextComponentString(TextFormatting.RED + "NONE"));
			
			for(int i = 0; i < data.getSpawnLocations().size(); i++)
			{
				BlockPos pos = data.getSpawnLocations().get(i);
				String locName = data.getLocationNames().get(i);
				
				if(!data.getSpawnLocations().isEmpty())
				{
					ITextComponent s = new TextComponentString(TextFormatting.GREEN + " - " + TextFormatting.YELLOW + locName + TextFormatting.GREEN + " [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]");
					Style style = s.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "")
					{
						@Override
						public Action getAction()
						{	
							for(int i = 0; i < 25; i++)
							{
								player.sendMessage(new TextComponentString(""));
							}
							
							PacketHandler.sendToServer(new PacketChooseLocation(pos, player.getRidingEntity().getEntityId()));
							return Action.RUN_COMMAND;
						}
					});
					
					style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(TextFormatting.YELLOW + locName + TextFormatting.GREEN + " [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]")));
					s.setStyle(style);
					player.sendMessage(s);
				}
			}
			
			if(!data.getSpawnLocations().isEmpty())
			{
				player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.PARACHUTE));
				int id = getClosestLocation(data, world);
				BlockPos zonePos = data.getSpawnLocations().get(id);
				player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Zone is closest to the " + data.getLocationNames().get(id) + " [" + zonePos.getX() + ", " + zonePos.getZ() + "]"));
			}
		}
		
		if(data.getZonePhaseCount() != 0)
		{
			data.setCurrentZone(0);
		}
		
		data.createGameID();
	}
	
	/**
	 * @param center - the map center
	 * @param size - the map size
	 * @param mode - TRUE == X Coord; FALSE == Z coord
	 * @return random number between map center and map edge
	 */
	private int generateZoneCenter(BlockPos center, int size, boolean mode)
	{
		final Random rand = new Random();
		return mode ? center.getX() + (rand.nextInt(size) - rand.nextInt(size)) : center.getZ() + (rand.nextInt(size) - rand.nextInt(size));
	}
	
	private void stopGame(IGameData data, World world)
	{
		WorldBorder border = world.getWorldBorder();
		border.setCenter(data.getMapCenter().getX() + 0.5, data.getMapCenter().getZ() + 0.5);
		border.setTransition(data.getMapSize()*2);
	}
	
	private void resetGame(IGameData data, World world)
	{
		Pubgmc.logger.info("Attempting game reset...");
		data.setPlaying(true);
		
		for(EntityPlayer player : world.playerEntities)
		{
			player.setHealth(20f);
			player.inventory.clear();
			player.sendMessage(new TextComponentString(TextFormatting.GRAY + "Game is being reset."));
			
			TextComponentString s = new TextComponentString(TextFormatting.GREEN + "Get back to lobby");
			Style style = new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/leave"));
			s.setStyle(style);
			
			player.sendMessage(s);
		}
		
		stopGame(data, world);
		data.setPlaying(false);
		Pubgmc.logger.info("Game has been restarted successfully.");
	}
	
	private EntityPlane spawnPlane(World world, IGameData data)
	{
		EntityPlane plane = new EntityPlane(world, data);
		
		for(EntityPlayer player : world.playerEntities)
		{
			player.setPositionAndUpdate(plane.getStartingPosition().getX(), 256, plane.getStartingPosition().getZ());
		}
		
		world.spawnEntity(plane);
		
		for(EntityPlayer player : world.playerEntities)
		{
			player.startRiding(plane);
			Pubgmc.logger.info("Added " + player.getName() + " on board of plane");
		}
		
		return plane;
	}
	
	private int getClosestLocation(IGameData data, World world)
	{
		BlockPos center = new BlockPos(world.getWorldBorder().getCenterX(), 10, world.getWorldBorder().getCenterZ());

		int id = 0;
		double dist = Double.MAX_VALUE;
		for(int i = 0; i < data.getSpawnLocations().size(); i++)
		{
			BlockPos pos = data.getSpawnLocations().get(i);
			if(PUBGMCUtil.getDistanceToBlockPos(pos, center) < dist)
			{
				dist = PUBGMCUtil.getDistanceToBlockPos(pos, center);
				id = i;
			}
		}
		
		return id;
	}
	
	private double sqr(double num)
	{
		return num*num;
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, completions) : Collections.EMPTY_LIST;
	}
}
