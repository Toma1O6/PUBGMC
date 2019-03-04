package com.toma.pubgmc.common;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.capability.IWorldData.WorldDataProvider;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.items.ItemGrenade;
import com.toma.pubgmc.common.items.ItemMolotov;
import com.toma.pubgmc.common.items.ItemSmokeGrenade;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSyncConfig;
import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import com.toma.pubgmc.init.PMCBlocks;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CommonEvents
{
	private double prevDiameter = 0;
	
	//Attach capability to entity
	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent<Entity> e)
	{
		if(e.getObject() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) e.getObject();
			e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":playerdata"), new PlayerDataProvider());
		}
	}
	
	@SubscribeEvent
	public void attachWorldCapability(AttachCapabilitiesEvent<World> e)
	{
		e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":worldData"), new WorldDataProvider());
		e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":gameData"), new GameDataProvider());
	}
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent e)
	{
		if(e.phase == Phase.END && !e.world.isRemote)
		{
			if(e.world.hasCapability(GameDataProvider.GAMEDATA, null))
			{
				IGameData game = e.world.getCapability(GameDataProvider.GAMEDATA, null);
				World world = e.world;
				
				if(game.isPlaying() && game.getZonePhaseCount() > 0 && game.getCurrentZone() < game.getZonePhaseCount() && !isZoneShrinking(world.getWorldBorder()))
				{
					final int phases = game.getZonePhaseCount();
					final int phase = game.getCurrentZone();
					final int diameter = game.getMapSize() * 2;
					float zoneSwitchPoint = phase == 0 ? 2000*(game.getMapSize()/250 + 1) : 1200*(game.getMapSize()/250 + 1);
					
					game.increaseTimer();
					
					if(game.getTimer() >= zoneSwitchPoint)
					{
						game.setTimer(0);
						game.setCurrentZone(game.getCurrentZone() + 1);
						WorldBorder brd = world.getWorldBorder();
						double zoneArea = (100 - (phase+1)*(100/(phases)) + 5)/100d;
						brd.setTransition(brd.getDiameter(), brd.getDiameter() * zoneArea, (long)(1000 * (phases * 10 * (diameter / 250 + 1))));
						brd.setDamageBuffer(1);
						brd.setDamageAmount(phase/phases);
						
						for(EntityPlayer p : world.playerEntities)
						{
							p.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Zone is shrinking!"));
						}
					}
				}
				
				if(game.isPlaying() || prevDiameter == 0)
				{
					prevDiameter = world.getWorldBorder().getDiameter();
				}
			}
		}
	}
	
	//Tick event function which fires on all players
	@SubscribeEvent
	public void onTick(PlayerTickEvent ev)
	{
		EntityPlayer player = ev.player;
		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

		//To prevent the method from being called multiple times at once
		if(ev.phase == Phase.START && !player.world.isRemote)
		{
			//Inventory limit
			if(ConfigHandler.enableInventoryLimit)
			{
				for(int i = 9; i < 36; i++)
				{
					ItemStack stack = player.inventory.getStackInSlot(i);
					
					if(data.getBackpackLevel() == 0 && !player.capabilities.isCreativeMode)
					{
						if(stack.getItem() != PMCItems.IBLOCK)
						{
							player.inventory.setInventorySlotContents(i, new ItemStack(PMCItems.IBLOCK));
						}
					}
				}
				
				for(int i = 9; i < 27; i++)
				{
					ItemStack stack = player.inventory.getStackInSlot(i);
					
					if(data.getBackpackLevel() == 1 && !player.capabilities.isCreativeMode)
					{
						if(stack.getItem() != PMCItems.IBLOCK)
						{
							if(!player.world.isRemote && !stack.isEmpty())
							{
								EntityItem ent = new EntityItem(player.world, player.posX, player.posY, player.posZ, stack);
								ent.setPickupDelay(50);
								player.world.spawnEntity(ent);
							}
							
							player.inventory.setInventorySlotContents(i, new ItemStack(PMCItems.IBLOCK));
						}
					}
				}
				
				for(int i = 9; i < 18; i++)
				{
					ItemStack stack = player.inventory.getStackInSlot(i);
					
					if(data.getBackpackLevel() == 2 && !player.capabilities.isCreativeMode)
					{
						if(stack.getItem() != PMCItems.IBLOCK)
						{
							if(!player.world.isRemote && !stack.isEmpty())
							{
								EntityItem ent = new EntityItem(player.world, player.posX, player.posY, player.posZ, stack);
								ent.setPickupDelay(50);
								player.world.spawnEntity(ent);
							}
							
							player.inventory.setInventorySlotContents(i, new ItemStack(PMCItems.IBLOCK));
						}
					}
				}
				
				for(int i = 0; i < player.inventory.getSizeInventory(); i++)
				{
					ItemStack stack = player.inventory.getStackInSlot(i);
					if(stack.getItem() == PMCItems.IBLOCK && data.getBackpackLevel() == 3)
					{
						player.inventory.clearMatchingItems(PMCItems.IBLOCK, 0, player.inventory.getSizeInventory() * 64, null);
					}
				}
			}
			
			/** BOOST **/
			/**===================================================================================================================**/
			
			//If player's boost value is above 50% he gets speed bonus
			if(player != null && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null)
			{
	        	if(data.getBoost() >= 50)
	        	{
	        		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 0, false, false));
	        	}
	        	
	        	//Healing the player based on boost value
	    		if(data.getBoost() > 0)
	    		{
	    			//Check if the timer has run to 0
	    			if(data.getTimer() <= 0)
	    			{
	    				//Do the healing
	    				if(data.getBoost() >= 50)
	    				{
	    					player.heal(2f);
	    				}
	    					
	    				else
	    				{
	    					player.heal(1f);
	    				}
	    				
	    				//reset the timer
	    				data.setTimer(400);
	    			}
	    			
	    			//decrease the timer
	    			data.setTimer(data.getTimer() - 1);
	    		}
	    		
	    		//if player is not boosted his boost timer gets reset to 0 to enable quick first heal after he applies the boost
	    		else
	    		{
	    			if(data.getTimer() > 0)
	    			{
	    				data.setTimer(0);
	    			}
	        	}
			}
			
			/** Grenades **/
			/**===================================================================================================================**/
			
			//If the grenade has started the cooking process
			if(data.isGrenadeCooking())
			{
				//Increase the cooking timer every tick
				data.setCookingTime(data.getCookingTime() + 1);
			}
			
			//If player is cooking the grenade for 4 secs
			if(data.getCookingTime() >= 80)
			{
				//Reset the timer and cooking
				data.setCookingTime(0);
				data.setGrenadeCooking(false);
				
				//Spawn new grenade entity with 0 fuse so it will explode instantly
				player.world.spawnEntity(new EntityGrenade(player.world, player, 0));
				
				//If player is in survival mode
				if(!player.capabilities.isCreativeMode)
				{
					//Get all inventory slots
					for(int i = 0; i < player.inventory.getSizeInventory(); i++)
					{
						//Get itemstacks from all slots
						ItemStack stack = player.inventory.getStackInSlot(i);
						
						//check all itemstacks if they are the grenade items
						if(stack.getItem() == PMCItems.GRENADE)
						{
							//clear 1 grenade item
							stack.shrink(1);
						}
						
						//if player has no grenades in inventory it has to mean he dropped it so we have to check it too
						else
						{
							//Get all loaded entities
							for(int j = 0; j < player.world.loadedEntityList.size(); j++)
							{
								Entity entity = player.world.loadedEntityList.get(j);
								
								//Check if the entity is item entity
								if(entity instanceof EntityItem)
								{
									EntityItem item = (EntityItem)entity;
									
									//we get the itemstack from the entity and then the item from the itemstack
									if(item.getItem().getItem() == PMCItems.GRENADE)
									{
										//remove the entity
										item.setDead();
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//Once player logs in
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent e)
	{
		if(ConfigHandler.enableMessagesSentOnJoin)
		{
			if(!e.player.world.isRemote && e.player instanceof EntityPlayer && e.player != null)
			{
				EntityPlayer player = e.player;
				TextComponentString urlMessage = new TextComponentString(TextFormatting.GREEN + "Make sure you're playing the newest version. New versions can be found " + TextFormatting.BOLD + "HERE");
				urlMessage.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/pubgmc-mod/files"));
				player.sendMessage(urlMessage);
				player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Current version:" + TextFormatting.BOLD + " " + Pubgmc.VERSION + TextFormatting.RESET + ", " + TextFormatting.AQUA + "author:" + TextFormatting.BOLD + " Toma1O6"));
			}
		}
		
		if(e.player instanceof EntityPlayerMP)
		{
			//Sync config with server
			PacketHandler.sendToClient(new PacketSyncConfig(), (EntityPlayerMP)e.player);
			
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if(player != null && !player.world.isRemote)
			{
				//We get the last player data and later sync it to client
				player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
				IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
				
				//Sync some data from capability to client for overlay rendering 
				PacketHandler.syncPlayerDataToClient(data, player);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e)
	{
		Entity entity = e.getEntity();
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.getEntity();
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        	
	        if(data.getEquippedNV() && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null)
	        {
	        	//no need to sync anything since it runs server side
	        	if(data.isUsingNV() && !data.getEquippedNV())
	        	{
	        		data.setNV(false);
	        	}
	        	
	        	//NV stuff
	        	if(data.isUsingNV())
	        	{
	        		if(!player.world.isRemote)
	        		{
		        		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
	        		}
	        	}
	        	else
	        	{
	        		if(!player.world.isRemote)
	        		{
	        			//Remove potion effect when player turns off night vision 
		        		if(player.isPotionActive(MobEffects.NIGHT_VISION))
		        		{
		        			player.removePotionEffect(MobEffects.NIGHT_VISION);
		        		}
	        		}
	        	}
	        }
		}
	}
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent e)
	{
		if(e.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.getEntity();
			BlockPos p = player.getPosition();
			World world = player.world;
			boolean continueSearch = true;
					
			//crate position logic
			if(!world.isAirBlock(p))
			{
				//check if the position is suitable for crate to spawn
				if(world.isAirBlock(new BlockPos(p.getX(), p.getY() + 1, p.getZ())))
				{
					p = new BlockPos(p.getX(), p.getY() + 1, p.getZ());
				}
				
				//if not, this checks all blocks horizontally around the player
				else
				{
					if(world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ())))
					{
						p = new BlockPos(p.getX() + 1, p.getY(), p.getZ());
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ())))
					{
						p = new BlockPos(p.getX() - 1, p.getY(), p.getZ());
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX(), p.getY(), p.getZ() + 1)))
					{
						p = new BlockPos(p.getX(), p.getY(), p.getZ() + 1);
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX(), p.getY(), p.getZ() - 1)))
					{
						p = new BlockPos(p.getX(), p.getY(), p.getZ() - 1);
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1)))
					{
						p = new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1);
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ() - 1)))
					{
						p = new BlockPos(p.getX() + 1, p.getY(), p.getZ() - 1);
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ() + 1)))
					{
						p = new BlockPos(p.getX() - 1, p.getY(), p.getZ() + 1);
					}
					
					else if(world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ() - 1)))
					{
						p = new BlockPos(p.getX() - 1, p.getY(), p.getZ() - 1);
					}
					
					//If there's no empty space, we set the pos to null.
					//Since blockpos cannot be null we have to run check later to prevent crashes
					//Also this allows us to not spawn the crate at all when there's no space for the crate
					else
					{
						p = null;
					}
				}
			}
			
			if(p != null)
			{
				//This is here to prevent floating crates
				while(world.isAirBlock(new BlockPos(p.getX(), p.getY() - 1, p.getZ())))
				{
					p = new BlockPos(p.getX(), p.getY() - 1, p.getZ());
				}
			}
			
			//Here we check if the position has been found for the crate to spawn and if player has empty space in inventory
			if(p != null && !player.inventory.isEmpty() && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null && !player.world.getGameRules().getBoolean("keepInventory"))
			{
				//get the player capability and place the block
				IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
				world.setBlockState(p, PMCBlocks.PLAYER_CRATE.getDefaultState(), 3);
				
				//get the tileentity of the block
				TileEntity tileentity = world.getTileEntity(p);
				
				//if the tileentity is the tileentity we need
				//It is not really necessary to have it here, since it will always return true
				if(tileentity instanceof TileEntityPlayerCrate)
				{
					TileEntityPlayerCrate te = (TileEntityPlayerCrate)tileentity;
					
					//We get all player inventory slots
					for(int i = 0; i < player.inventory.getSizeInventory(); i++)
					{
						//Get the itemstacks from all slots
						ItemStack stack = player.inventory.getStackInSlot(i);
						
						//Clear the unwanted items used for filling the inventory
						player.inventory.clearMatchingItems(PMCItems.IBLOCK, 0, 250, null);
						
						//fill the inventory with all items from slots
						te.setInventorySlotContents(i, stack);
						
						//Add backpacks to the crate if player has equipped one and reset the player capability
						if(data.getBackpackLevel() != 0)
						{
							switch(data.getBackpackLevel())
							{
								case 1: te.setInventorySlotContents(41, new ItemStack(PMCItems.BACKPACK1));
								case 2: te.setInventorySlotContents(41, new ItemStack(PMCItems.BACKPACK2));
								case 3: te.setInventorySlotContents(41, new ItemStack(PMCItems.BACKPACK3));
							}
							
							data.setBackpackLevel(0);
						}
						
						//Same as above but just for the night vision googles
						if(data.getEquippedNV())
						{
							te.setInventorySlotContents(42, new ItemStack(PMCItems.NV_GOGGLES));
							
							data.hasEquippedNV(false);
						}
					}
					
					//now clear player inventory to prevent item drops from the player
					player.inventory.clear();
					data.setBoost(0);
				}
			}
		}
	}
	
	/**
	 *  Event which is responsible for getting the player into the safe zone when they die
	 */
	@SubscribeEvent
	public void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.world;
		if(world.hasCapability(GameDataProvider.GAMEDATA, null))
		{
			IGameData game = world.getCapability(GameDataProvider.GAMEDATA, null);
			
			if(game.isPlaying())
			{
				BlockPos tpPos = new BlockPos(game.getMapCenter().getX(), 256, game.getMapCenter().getZ());
				while(world.isAirBlock(tpPos))
				{
					tpPos = new BlockPos(tpPos.getX(), tpPos.getY() - 1, tpPos.getZ());
				}
				
				player.attemptTeleport(tpPos.getX() + 0.5, tpPos.getY() + 1, tpPos.getZ() + 0.5);
				player.setGameType(GameType.SPECTATOR);
			}
		}
	}
	
	/**
	 * Event used for disabling block destruction when player is
	 * holding weapon/grenade
	 * @param e - event
	 */
	@SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent e)
	{
		EntityPlayer player = e.getPlayer();
		ItemStack heldStack = player.getHeldItemMainhand();
		
		if(heldStack.getItem() instanceof GunBase || heldStack.getItem() instanceof ItemGrenade || heldStack.getItem() instanceof ItemSmokeGrenade || heldStack.getItem() instanceof ItemMolotov)
		{
			e.setCanceled(true);
		}
	}
	
	/**
	 * Event which is used for disabling the inventory block icon drop
	 * It will be removed from the inventory, but no item entity will spawn
	 * @param e - event
	 */
	@SubscribeEvent
	public void onItemDrop(ItemTossEvent e)
	{
		EntityPlayer player = e.getPlayer();
		EntityItem itemEntity = e.getEntityItem();
		
		if(itemEntity.getItem().getItem() == PMCItems.IBLOCK)
		{
			if(!player.capabilities.isCreativeMode)
			{
				e.setCanceled(true);
			}
		}
		
		//Ammo returning back to inventory
		if(itemEntity.getItem().getItem() instanceof GunBase)
		{
			ItemStack stack = itemEntity.getItem();
			
			if(stack.hasTagCompound() && !player.capabilities.isCreativeMode)
			{
				GunBase gun = (GunBase)stack.getItem();
				int ammo = stack.getTagCompound().getInteger("ammo");
				
				player.addItemStackToInventory(new ItemStack(gun.getAmmoType().ammo(), ammo));
				stack.getTagCompound().setInteger("ammo", 0);
			}
		}
	}
	
	private boolean isZoneShrinking(WorldBorder border)
	{
		return border.getDiameter() != prevDiameter;
	}
}
