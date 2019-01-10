package com.toma.pubgmc.common.commands;

import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandClearPlayerCrates extends CommandBase
{
	@Override
	public String getName()
	{
		return "clearplayercrates";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/clearplayercrates";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(args.length == 0)
		{
			World world = sender.getEntityWorld();
			
			for(int i = 0; i < world.loadedTileEntityList.size(); i++)
			{
				TileEntity te = world.loadedTileEntityList.get(i);
				
				if(te instanceof TileEntityPlayerCrate)
				{
					world.setBlockToAir(te.getPos());
				}
			}
			
			sender.sendMessage(new TextComponentString("Crates has been removed!"));
		}
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
}
