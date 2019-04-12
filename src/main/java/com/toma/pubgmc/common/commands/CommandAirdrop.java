package com.toma.pubgmc.common.commands;

import java.util.Random;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CommandAirdrop extends CommandBase
{
	/** Pure RNG **/
	private final Random rand = new Random();
	
	/** Used for stopping while loop after the block is spawned **/
	private boolean spawned;
	
	/** Number of spawn attempts. This is used to prevent the code from running when there are no suitable players (saving performance) **/
	private int spawnAttempts;
	
	@Override
	public String getName()
	{
		return "airdrop";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/airdrop  ... This will spawn airdrop on random player/s";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		spawned = false;
		spawnAttempts = 0;
		int playerCount = server.getPlayerList().getCurrentPlayerCount();
		int randomPlayer = rand.nextInt(playerCount);
		EntityPlayerMP player = server.getPlayerList().getPlayers().get(randomPlayer);
		World world = player.world;
		
		while(!spawned && spawnAttempts <= 10)
		{
			//Increase the spawn attempt
			spawnAttempts++;
			
			if(!player.capabilities.isCreativeMode && !player.isSpectator())
			{
				BlockPos pos = player.getPosition();
				
				//Bit of RNG for random position
				int xPos = rand.nextInt(10);
				int zPos = rand.nextInt(10);
				
				//50% for each coord to be negative. That means the airdrop can spawn anywhere around the player
				if(Math.random() * 100 <= 50)
				{
					xPos = -xPos;
				}
				if(Math.random() * 100 <= 50)
				{
					zPos = -zPos;
				}
				
				//Calculate the position where the block will be spawned
				pos = new BlockPos((int)player.posX + xPos, (int)player.posY + 80, (int)player.posZ + zPos);
				
				//Spawn the block entity
				if(!world.isRemote)
				{
					//+0.5 so the block won't get stuck between 2 blocks
					EntityFallingBlock efb = new EntityFallingBlock(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, PMCRegistry.Blocks.AIRDROP.getDefaultState());
					efb.fallTime = 1;
					world.spawnEntity(efb);
				}
				
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PMCSounds.airdrop_plane_fly_by, SoundCategory.MASTER, 15f, 1f);
				
				//Tell the player about the drop
				if(PUBGMCUtil.shouldSendCommandFeedback(world))
					player.sendMessage(new TextComponentString(TextFormatting.RED + "Airdrop is nearby. Go for it"));
				
				//Small chance to spawn multiple airdrop, but never more than 10
				//Also this will get another player, so it will be rare if you play with others and get multiple drops
				if(Math.random() * 100 <= 2)
				{
					spawned = false;
					randomPlayer = rand.nextInt(playerCount);
					player = server.getPlayerList().getPlayers().get(randomPlayer);
				}
				//To stop the while loop
				else spawned = true;
				
				sender.sendMessage(new TextComponentString("Airdrop has been sucessfully spawned"));
			}
		}
		
		//Tell the player that no blocks has been spawned
		if(spawnAttempts > 10)
		{
			sender.sendMessage(new TextComponentString("No suitable players found"));
		}
		
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
}
