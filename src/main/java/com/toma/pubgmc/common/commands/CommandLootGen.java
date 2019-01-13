package com.toma.pubgmc.common.commands;

import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandLootGen extends CommandBase
{
	@Override
	public String getName() 
	{
		return "loot";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/loot <generate|remove> <airdroploot>";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		World world = sender.getEntityWorld();
			
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("generate"))
			{
				for(int i = 0; i < world.loadedTileEntityList.size(); i++)
				{
					TileEntity tee = world.loadedTileEntityList.get(i);
					if(tee instanceof TileEntityLootSpawner && !world.isRemote)
					{
						((TileEntityLootSpawner) tee).generateLoot(false, true, 0);
						world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
					}
				}
					
				if(!world.isRemote && sender instanceof EntityPlayer)
				{
					sender.sendMessage(new TextComponentString("Loot has been generated!"));
				}
			}
				
			else if(args[0].equalsIgnoreCase("clear"))
			{
				for(int i = 0; i < world.loadedTileEntityList.size(); i++)
				{
					TileEntity tee = world.loadedTileEntityList.get(i);
					if(tee instanceof TileEntityLootSpawner)
					{
						((TileEntityLootSpawner) tee).clear();
						world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
					}
				}
					
				if(!world.isRemote && sender instanceof EntityPlayer)
				{
					sender.sendMessage(new TextComponentString("Loot has been cleared!"));
				}
			}
			
			else if(args[0].equalsIgnoreCase("debug"))
			{
				//Command blocks cannot execute this, there's no need for that
				if(sender instanceof EntityPlayer)
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						
						if(tee instanceof TileEntityLootSpawner)
						{
							/*BlockPos pos = te.getPos();
							EntityItem itemEnt = new EntityItem(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(PMCItems.IBLOCK));
							itemEnt.motionX = 0;
							itemEnt.motionY = 0;
							itemEnt.motionZ = 0;
							world.spawnEntity(itemEnt);*/
							TileEntityLootSpawner te = (TileEntityLootSpawner)tee;
							
							for(int j = 0; j < te.getSizeInventory(); j++)
							{
								te.setInventorySlotContents(j, new ItemStack(PMCItems.IBLOCK));
							}
							
							IBlockState state = world.getBlockState(te.getPos());
							world.notifyBlockUpdate(te.getPos(), state, state, 3);
						}
					}
					sender.sendMessage(new TextComponentString("All loaded loot spawners has been marked"));
				}
			}
				
			else throw new WrongUsageException("Unknown command. Try /loot generate or /loot clear", new Object[0]);
		}
		
		if(args.length == 2)
		{
			if(args[0].equalsIgnoreCase("generate"))
			{
				if(args[1].equalsIgnoreCase("airdrop"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(true, true, 0);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Special loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("noammo"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, false, 0);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Special loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("pistol"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 1);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Pistol loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("shotgun"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 2);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Shotgun loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("smg"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 3);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("SMG loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("ar"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 4);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Assault rifle loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("dmr"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 5);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("DMR loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("bolt_action"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 6);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Bolt action SR loot has been generated"));
				}
				
				else if(args[1].equalsIgnoreCase("snipers"))
				{
					for(int i = 0; i < world.loadedTileEntityList.size(); i++)
					{
						TileEntity tee = world.loadedTileEntityList.get(i);
						if(tee instanceof TileEntityLootSpawner)
						{
							((TileEntityLootSpawner)tee).generateLoot(false, true, 7);
							world.notifyBlockUpdate(tee.getPos(), world.getBlockState(tee.getPos()), world.getBlockState(tee.getPos()), 3);
							
						}
					}
					
					sender.sendMessage(new TextComponentString("Sniper loot has been generated"));
				}
				
				else
				{
					throw new WrongUsageException("Unknown command. Try /loot generate <airdrop | noammo | pistol | shotgun | smg | ar | dmr | bolt_action | snipers>", new Object[0]);
				}
			}
			
			else
			{
				throw new WrongUsageException("Unknown command. Try /loot generate", new Object[0]);
			}
		}
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		if(args.length == 1)
		{
			return getListOfStringsMatchingLastWord(args, new String[] {"generate", "clear", "debug"});
		}
		
		else if(args.length == 2 && args[0].equalsIgnoreCase("generate"))
		{
			return getListOfStringsMatchingLastWord(args, new String[] {"airdrop", "noammo", "pistol", "shotgun", "smg", "ar", "dmr", "bolt_action", "snipers"});
		}
		
		else return Collections.emptyList();
	}
}
