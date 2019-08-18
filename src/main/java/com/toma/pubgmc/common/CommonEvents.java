package com.toma.pubgmc.common;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.capability.IWorldData.WorldDataProvider;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.items.ItemGrenade;
import com.toma.pubgmc.common.items.ItemMolotov;
import com.toma.pubgmc.common.items.ItemSmokeGrenade;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketClientCapabilitySync;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.event.LandmineExplodeEvent;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.CustomDateEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.Map;

public class CommonEvents {
    private double prevDiameter = 0;

    private static void handleUpdateResults(ForgeVersion.CheckResult result, EntityPlayer player) {
        switch (result.status) {
            case AHEAD: {
                sendMessage(player, "[PUBGMC] It appears you're using unofficial version, expect bugs ;)", TextFormatting.AQUA);
                break;
            }

            case UP_TO_DATE: {
                sendMessage(player, "You have the newest version of PUBGMC!", TextFormatting.GREEN);
                TextComponentString discordNotification = new TextComponentString(TextFormatting.GREEN + "Join my official " + TextFormatting.AQUA + "DISCORD" + TextFormatting.GREEN + ". Click HERE");
                discordNotification.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/njhduKq")));
                player.sendMessage(discordNotification);
                break;
            }

            case FAILED: {
                sendMessage(player, "[PUBGMC] Update check failed! Check your internet connection", TextFormatting.RED);
                break;
            }

            case OUTDATED: {
                sendMessage(player, "[PUBGMC] You are using old version! Get a new one.", TextFormatting.YELLOW);
                TextComponentString comp = new TextComponentString(TextFormatting.YELLOW + "New version is available! You can get it " + TextFormatting.ITALIC + "HERE");
                comp.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/pubgmc-mod/files")));
                player.sendMessage(comp);
                break;
            }

            case PENDING: {
                sendMessage(player, "[PUBGMC] Unable to check new version, check took too long!", TextFormatting.BLUE);
                break;
            }

            default:
                break;
        }

        // For handling specific events like christmas etc
        CustomDateEvents.handleDates(player);
    }

    private static void sendMessage(EntityPlayer player, String message, TextFormatting color) {
        player.sendMessage(new TextComponentString(color + message));
    }

    @SubscribeEvent
    public void landmineExploded(LandmineExplodeEvent e) {
        for (Entity entity : e.getAffectedEntities()) {
            if (entity instanceof EntityVehicle) {
                EntityVehicle car = (EntityVehicle) entity;
                double d0 = PUBGMCUtil.getDistanceToBlockPos3D(car.getPosition(), e.getExplosionPosition());
                float damage = 200f * (float) (1 - d0 / 10);
                car.health -= damage;
            }
        }
    }

    @SubscribeEvent
    public void onKnockback(LivingKnockBackEvent e) {
        // yes, it's named badly, I don't care :p
        if(ConfigPMC.common.player.knockbackEnabled)
            return;

        e.setCanceled(true);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load e) {
        World world = e.getWorld();
        IGameData data = world.getCapability(GameDataProvider.GAMEDATA, null);
        if (!data.isPlaying()) {
            return;
        }
        Chunk chunk = e.getChunk();
        Map<BlockPos, TileEntity> map = chunk.getTileEntityMap();
        IWorldData loot = world.getCapability(WorldDataProvider.WORLD_DATA, null);

        for (TileEntity tileEntity : map.values()) {
            if (tileEntity instanceof TileEntityLootSpawner) {
                TileEntityLootSpawner te = (TileEntityLootSpawner) tileEntity;

                if (!te.getGameID().equalsIgnoreCase(data.getGameID())) {
                    te.setGameID(data.getGameID());
                    te.generateLoot(loot.hasAirdropWeapons(), loot.isAmmoLootEnabled(), loot.isRandomAmmoCountEnabled(), loot.getLootChanceMultiplier(), loot.getWeaponList());
                }
            }
        }
    }

    //Attach capability to entity
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getObject();
            e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":playerdata"), new PlayerDataProvider());
        }
    }

    @SubscribeEvent
    public void attachWorldCapability(AttachCapabilitiesEvent<World> e) {
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":worldData"), new WorldDataProvider());
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":gameData"), new GameDataProvider());
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase == Phase.END && !e.world.isRemote) {
            if (e.world.hasCapability(GameDataProvider.GAMEDATA, null)) {
                IGameData game = e.world.getCapability(GameDataProvider.GAMEDATA, null);
                World world = e.world;
                if (game.isPlaying() && game.getZonePhaseCount() > 0 && (game.getCurrentZone() < game.getZonePhaseCount() || isZoneShrinking(world.getWorldBorder()))) {
                    final int phases = game.getZonePhaseCount();
                    final int phase = game.getCurrentZone();
                    final int diameter = game.getMapSize() * 2;
                    float zoneSwitchPoint = phase == 0 ? 2500 * (game.getMapSize() / 250 + 1) : 1200 * (game.getMapSize() / 250 + 1);
                    WorldBorder brd = world.getWorldBorder();
                    if (brd.getDamageAmount() != phase / (double) phases) {
                        double dmg = brd.getDamageAmount();
                        brd.setDamageAmount(phase / (double) phases);
                        brd.setDamageBuffer(0);
                        Pubgmc.logger.info("Changed world border damage to {} from {}.", brd.getDamageAmount(), dmg);
                    }
                    if (!isZoneShrinking(brd)) {
                        game.increaseTimer();
                        if (game.getTimer() >= zoneSwitchPoint) {
                            game.setTimer(0);
                            game.setCurrentZone(game.getCurrentZone() + 1);
                            double zoneArea = (100 - (phase + 1) * (100 / (phases)) + 5) / 100d;
                            brd.setTransition(brd.getDiameter(), brd.getDiameter() * zoneArea, (long) (1000 * (phases * 10 * (diameter / 250 + 1))));
                            brd.setDamageBuffer(0);
                            brd.setDamageAmount(phase / (double) phases);
                            world.playerEntities.forEach(player -> {
                                if (ConfigPMC.common.world.titleZoneNotifications) {
                                    SPacketTitle packet = new SPacketTitle(Type.TITLE, new TextComponentString(TextFormatting.YELLOW + "Zone is shrinking!"), 5, 80, 5);
                                    if (player instanceof EntityPlayerMP) {
                                        ((EntityPlayerMP) player).connection.sendPacket(packet);
                                    }
                                } else {
                                    player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Zone is shrinking!"));
                                }
                            });
                        }
                    }
                }

                if (game.isPlaying() || prevDiameter == 0) {
                    prevDiameter = world.getWorldBorder().getDiameter();
                }
            }
        }
    }

    //Tick event function which fires on all players
    @SubscribeEvent
    public void onTick(PlayerTickEvent ev) {
        EntityPlayer player = ev.player;
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        //To prevent the method from being called multiple times at once
        if (ev.phase == Phase.START && !player.world.isRemote) {
            if (player.getHeldItemOffhand().getItem() instanceof GunBase) {
                EntityItem item = new EntityItem(player.world, player.posX, player.posY + player.getEyeHeight(), player.posZ, player.getHeldItemOffhand());
                Vec3d vec = player.getLookVec();
                item.setVelocity(vec.x * 0.3, vec.y * 0.3, vec.z * 0.3);
                item.setPickupDelay(30);
                player.world.spawnEntity(item);
                player.inventory.offHandInventory.set(0, ItemStack.EMPTY);
            }

            //Inventory limit
            if (ConfigPMC.common.player.inventoryLimit) {
                for (int i = 9; i < 36; i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);

                    if (data.getBackpackLevel() == 0 && !player.capabilities.isCreativeMode) {
                        if (stack.getItem() != PMCRegistry.PMCItems.IBLOCK) {
                            player.inventory.setInventorySlotContents(i, new ItemStack(PMCRegistry.PMCItems.IBLOCK));
                        }
                    }
                }

                for (int i = 9; i < 27; i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);

                    if (data.getBackpackLevel() == 1 && !player.capabilities.isCreativeMode) {
                        if (stack.getItem() != PMCRegistry.PMCItems.IBLOCK) {
                            if (!player.world.isRemote && !stack.isEmpty()) {
                                EntityItem ent = new EntityItem(player.world, player.posX, player.posY, player.posZ, stack);
                                ent.setPickupDelay(50);
                                player.world.spawnEntity(ent);
                            }

                            player.inventory.setInventorySlotContents(i, new ItemStack(PMCRegistry.PMCItems.IBLOCK));
                        }
                    }
                }

                for (int i = 9; i < 18; i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);

                    if (data.getBackpackLevel() == 2 && !player.capabilities.isCreativeMode) {
                        if (stack.getItem() != PMCRegistry.PMCItems.IBLOCK) {
                            if (!player.world.isRemote && !stack.isEmpty()) {
                                EntityItem ent = new EntityItem(player.world, player.posX, player.posY, player.posZ, stack);
                                ent.setPickupDelay(50);
                                player.world.spawnEntity(ent);
                            }

                            player.inventory.setInventorySlotContents(i, new ItemStack(PMCRegistry.PMCItems.IBLOCK));
                        }
                    }
                }

                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (stack.getItem() == PMCRegistry.PMCItems.IBLOCK && data.getBackpackLevel() == 3) {
                        player.inventory.clearMatchingItems(PMCRegistry.PMCItems.IBLOCK, 0, player.inventory.getSizeInventory() * 64, null);
                    }
                }
            }

            /** BOOST **/
            /**===================================================================================================================**/

            //If player's boost value is above 50% he gets speed bonus
            if (player != null && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null) {
                if (data.getBoost() >= 50) {
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 0, false, false));
                }

                //Healing the player based on boost value
                if (data.getBoost() > 0) {
                    //Check if the timer has run to 0
                    if (data.getTimer() <= 0) {
                        //Do the healing
                        if (data.getBoost() >= 50) {
                            player.heal(2f);
                        } else {
                            player.heal(1f);
                        }

                        //reset the timer
                        data.setTimer(400);
                    }

                    //decrease the timer
                    data.setTimer(data.getTimer() - 1);
                }

                //if player is not boosted his boost timer gets reset to 0 to enable quick first heal after he applies the boost
                else {
                    if (data.getTimer() > 0) {
                        data.setTimer(0);
                    }
                }
            }

            /** Grenades **/
            /**===================================================================================================================**/

            //If the grenade has started the cooking process
            if (data.isGrenadeCooking()) {
                //Increase the cooking timer every tick
                data.setCookingTime(data.getCookingTime() + 1);
            }

            //If player is cooking the grenade for 4 secs
            if (data.getCookingTime() >= 80) {
                //Reset the timer and cooking
                data.setCookingTime(0);
                data.setGrenadeCooking(false);

                //Spawn new grenade entity with 0 fuse so it will explode instantly
                player.world.spawnEntity(new EntityGrenade(player.world, player, 0));

                //If player is in survival mode
                if (!player.capabilities.isCreativeMode) {
                    //Get all inventory slots
                    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                        //Get itemstacks from all slots
                        ItemStack stack = player.inventory.getStackInSlot(i);

                        //check all itemstacks if they are the grenade items
                        if (stack.getItem() == PMCRegistry.PMCItems.GRENADE) {
                            //clear 1 grenade item
                            stack.shrink(1);
                        }

                        //if player has no grenades in inventory it has to mean he dropped it so we have to check it too
                        else {
                            //Get all loaded entities
                            for (int j = 0; j < player.world.loadedEntityList.size(); j++) {
                                Entity entity = player.world.loadedEntityList.get(j);

                                //Check if the entity is item entity
                                if (entity instanceof EntityItem) {
                                    EntityItem item = (EntityItem) entity;

                                    //we get the itemstack from the entity and then the item from the itemstack
                                    if (item.getItem().getItem() == PMCRegistry.PMCItems.GRENADE) {
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
    public void onPlayerLoggedIn(PlayerLoggedInEvent e) {
        if (ConfigPMC.client.other.messagesOnJoin) {
            if (e.player instanceof EntityPlayer && e.player != null) {
                ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
                handleUpdateResults(version, e.player);
            }
        }

        if (e.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) e.player;
            if (player != null && !player.world.isRemote) {
                // TODO
                //PacketHandler.sendToClient(new PacketUpdateConfig(), player);

                //We get the last player data and later sync it to client
                player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

                //Sync some data from capability to client for overlay rendering
                PacketHandler.syncPlayerDataToClient(data, player);
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

            if (data.getEquippedNV() && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null) {
                //no need to sync anything since it runs server side
                if (data.isUsingNV() && !data.getEquippedNV()) {
                    data.setNV(false);
                }

                //NV stuff
                if (data.isUsingNV()) {
                    if (!player.world.isRemote) {
                        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
                    }
                } else {
                    if (!player.world.isRemote) {
                        //Remove potion effect when player turns off night vision
                        if (player.isPotionActive(MobEffects.NIGHT_VISION)) {
                            player.removePotionEffect(MobEffects.NIGHT_VISION);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            BlockPos p = player.getPosition();
            World world = player.world;
            //crate position logic
            if (!world.isAirBlock(p)) {
                //check if the position is suitable for crate to spawn
                if (world.isAirBlock(new BlockPos(p.getX(), p.getY() + 1, p.getZ()))) {
                    p = new BlockPos(p.getX(), p.getY() + 1, p.getZ());
                }

                //if not, this checks all blocks horizontally around the player
                else {
                    if (world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ()))) {
                        p = new BlockPos(p.getX() + 1, p.getY(), p.getZ());
                    } else if (world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ()))) {
                        p = new BlockPos(p.getX() - 1, p.getY(), p.getZ());
                    } else if (world.isAirBlock(new BlockPos(p.getX(), p.getY(), p.getZ() + 1))) {
                        p = new BlockPos(p.getX(), p.getY(), p.getZ() + 1);
                    } else if (world.isAirBlock(new BlockPos(p.getX(), p.getY(), p.getZ() - 1))) {
                        p = new BlockPos(p.getX(), p.getY(), p.getZ() - 1);
                    } else if (world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1))) {
                        p = new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1);
                    } else if (world.isAirBlock(new BlockPos(p.getX() + 1, p.getY(), p.getZ() - 1))) {
                        p = new BlockPos(p.getX() + 1, p.getY(), p.getZ() - 1);
                    } else if (world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ() + 1))) {
                        p = new BlockPos(p.getX() - 1, p.getY(), p.getZ() + 1);
                    } else if (world.isAirBlock(new BlockPos(p.getX() - 1, p.getY(), p.getZ() - 1))) {
                        p = new BlockPos(p.getX() - 1, p.getY(), p.getZ() - 1);
                    }

                    //If there's no empty space, we set the pos to null.
                    //Since blockpos cannot be null we have to run check later to prevent crashes
                    //Also this allows us to not spawn the crate at all when there's no space for the crate
                    else {
                        p = null;
                    }
                }
            }

            if (p != null) {
                //This is here to prevent floating crates
                while (world.isAirBlock(new BlockPos(p.getX(), p.getY() - 1, p.getZ()))) {
                    p = new BlockPos(p.getX(), p.getY() - 1, p.getZ());
                }
            }

            //Here we check if the position has been found for the crate to spawn and if player has empty space in inventory
            if (p != null && !player.inventory.isEmpty() && player.getCapability(PlayerDataProvider.PLAYER_DATA, null) != null && !player.world.getGameRules().getBoolean("keepInventory")) {
                //get the player capability and place the block
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                world.setBlockState(p, PMCRegistry.PMCBlocks.PLAYER_CRATE.getDefaultState(), 3);

                //get the tileentity of the block
                TileEntity tileentity = world.getTileEntity(p);

                //if the tileentity is the tileentity we need
                //It is not really necessary to have it here, since it will always return true
                if (tileentity instanceof TileEntityPlayerCrate) {
                    TileEntityPlayerCrate te = (TileEntityPlayerCrate) tileentity;

                    //We get all player inventory slots
                    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                        //Get the itemstacks from all slots
                        ItemStack stack = player.inventory.getStackInSlot(i);

                        //Clear the unwanted items used for filling the inventory
                        player.inventory.clearMatchingItems(PMCRegistry.PMCItems.IBLOCK, 0, 250, null);

                        //fill the inventory with all items from slots
                        te.setInventorySlotContents(i, stack);

                        //Add backpacks to the crate if player has equipped one and reset the player capability
                        if (data.getBackpackLevel() != 0) {
                            switch (data.getBackpackLevel()) {
                                case 1:
                                    te.setInventorySlotContents(41, new ItemStack(PMCRegistry.PMCItems.BACKPACK1));
                                case 2:
                                    te.setInventorySlotContents(41, new ItemStack(PMCRegistry.PMCItems.BACKPACK2));
                                case 3:
                                    te.setInventorySlotContents(41, new ItemStack(PMCRegistry.PMCItems.BACKPACK3));
                            }

                            data.setBackpackLevel(0);
                        }

                        //Same as above but just for the night vision googles
                        if (data.getEquippedNV()) {
                            te.setInventorySlotContents(42, new ItemStack(PMCRegistry.PMCItems.NV_GOGGLES));

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
     * Event which is responsible for getting the player into the safe zone when they die
     */
    @SubscribeEvent
    public void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;
        if (world.hasCapability(GameDataProvider.GAMEDATA, null)) {
            IGameData game = world.getCapability(GameDataProvider.GAMEDATA, null);

            if (game.isPlaying()) {
                BlockPos tpPos = new BlockPos(game.getMapCenter().getX(), 256, game.getMapCenter().getZ());
                while (world.isAirBlock(tpPos)) {
                    tpPos = new BlockPos(tpPos.getX(), tpPos.getY() - 1, tpPos.getZ());
                }

                player.attemptTeleport(tpPos.getX() + 0.5, tpPos.getY() + 1, tpPos.getZ() + 0.5);
                player.setGameType(GameType.SPECTATOR);
            }
        }
        PacketHandler.sendToClient(new PacketClientCapabilitySync(player.getCapability(PlayerDataProvider.PLAYER_DATA, null)), (EntityPlayerMP)player);
    }

    /**
     * Event used for disabling block destruction when player is
     * holding weapon/grenade
     *
     * @param e - event
     */
    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent e) {
        EntityPlayer player = e.getPlayer();
        ItemStack heldStack = player.getHeldItemMainhand();

        if (heldStack.getItem() instanceof GunBase || heldStack.getItem() instanceof ItemGrenade || heldStack.getItem() instanceof ItemSmokeGrenade || heldStack.getItem() instanceof ItemMolotov) {
            e.setCanceled(true);
        }
    }

    /**
     * Event which is used for disabling the inventory block icon drop
     * It will be removed from the inventory, but no item entity will spawn
     *
     * @param e - event
     */
    @SubscribeEvent
    public void onItemDrop(ItemTossEvent e) {
        EntityPlayer player = e.getPlayer();
        EntityItem itemEntity = e.getEntityItem();

        if (itemEntity.getItem().getItem() == PMCRegistry.PMCItems.IBLOCK) {
            if (!player.capabilities.isCreativeMode) {
                e.setCanceled(true);
            }
        }

        //Ammo returning back to inventory
        if (itemEntity.getItem().getItem() instanceof GunBase) {
            ItemStack stack = itemEntity.getItem();

            if (stack.hasTagCompound() && !player.capabilities.isCreativeMode) {
                GunBase gun = (GunBase) stack.getItem();
                int ammo = stack.getTagCompound().getInteger("ammo");

                player.addItemStackToInventory(new ItemStack(gun.getAmmoType().ammo(), ammo));
                stack.getTagCompound().setInteger("ammo", 0);
            }
        }
    }

    private boolean isZoneShrinking(WorldBorder border) {
        return border.getDiameter() != prevDiameter;
    }
}
