package com.toma.pubgmc.common;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.capability.IWorldData.WorldDataProvider;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.entity.throwables.EntityThrowableExplodeable;
import com.toma.pubgmc.common.items.ItemExplodeable;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.event.LandmineExplodeEvent;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.network.sp.PacketGetConfigFromServer;
import com.toma.pubgmc.network.sp.PacketLoadConfig;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.CustomDateEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.HashMap;
import java.util.UUID;

public class CommonEvents {
    private double prevDiameter = 0;
    public static final HashMap<UUID, NBTTagCompound> CONFIGS = new HashMap<>();

    private static void handleUpdateResults(ForgeVersion.CheckResult result, EntityPlayer player) {
        switch (result.status) {
            case AHEAD: {
                sendMessage(player, "[PUBGMC] It appears you're using unofficial version, expect bugs ;)", TextFormatting.AQUA);
                break;
            }

            case UP_TO_DATE: {
                sendMessage(player, "You have the newest version of PUBGMC!", TextFormatting.GREEN);
                TextComponentString discordNotification = new TextComponentString(TextFormatting.GREEN + "Join my official " + TextFormatting.AQUA + "DISCORD" + TextFormatting.GREEN + ". Click HERE");
                discordNotification.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/WmdUKZz")));
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
                comp.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/pubgmc-mod/files")));
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
    public void attachWorldCapability(AttachCapabilitiesEvent<World> e) {
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":worldData"), new WorldDataProvider());
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":gameData"), new GameDataProvider());
    }

    //Tick event function which fires on all players
    @SubscribeEvent
    public void onTick(PlayerTickEvent ev) {
        EntityPlayer player = ev.player;
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        player.eyeHeight = player.getDefaultEyeHeight();
        if(data.isProning()) {
            AxisAlignedBB proneBB = new AxisAlignedBB(player.posX - 0.6, player.posY, player.posZ - 0.6, player.posX + 0.6, player.posY + 0.8, player.posZ + 0.6);
            player.setEntityBoundingBox(proneBB);
            player.height = 0.9F;
            player.eyeHeight = 0.6F;
        }
        //To prevent the method from being called multiple times at once
        if(ev.phase == Phase.START && (!player.onGround || player.isSprinting() || player.isSneaking()) && data.isProning()) {
            data.setProning(false);
            data.sync(player);
        }
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
        }
    }

    //Once player logs in
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent e) {
        if (ConfigPMC.client.other.messagesOnJoin) {
            if (e.player instanceof EntityPlayer) {
                ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
                handleUpdateResults(version, e.player);
            }
        }

        if (e.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) e.player;
            if (player != null && !player.world.isRemote) {

                PacketHandler.sendToClient(new PacketGetConfigFromServer(ConfigPMC.common.serializeNBT()), player);

                //We get the last player data and later sync it to client
                player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

                //Sync some data from capability to client for overlay rendering
                PacketHandler.syncPlayerDataToClient(data, player);

                IGameData gameData = player.world.getCapability(GameDataProvider.GAMEDATA, null);
                if(gameData != null) {
                    gameData.getCurrentGame().updateDataToClient(player.world, player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent e) {
        if(e.player instanceof EntityPlayerMP) {
            PacketHandler.sendToClient(new PacketLoadConfig(CONFIGS.get(e.player.getUniqueID())), (EntityPlayerMP)e.player);
            CONFIGS.remove(e.player.getUniqueID());
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            AttributeModifier modifier = new AttributeModifier(UUID.fromString("42b68862-2bdc-4df4-9fbe-4ad597cda211"), "Speed", data.isProning() ? -0.07D : 0D, 0);
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(modifier);
            if(!player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(modifier)) {
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(modifier);
            }

            if (data.getEquippedNV()) {
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

        if (heldStack.getItem() instanceof GunBase || heldStack.getItem() instanceof ItemExplodeable) {
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
        else if (itemEntity.getItem().getItem() instanceof GunBase) {
            ItemStack stack = itemEntity.getItem();

            if (stack.hasTagCompound() && !player.capabilities.isCreativeMode) {
                GunBase gun = (GunBase) stack.getItem();
                int ammo = stack.getTagCompound().getInteger("ammo");

                player.addItemStackToInventory(new ItemStack(gun.getAmmoType().ammo(), ammo));
                stack.getTagCompound().setInteger("ammo", 0);
            }
        } else if(itemEntity.getItem().getItem() instanceof ItemExplodeable) {
            ItemStack stack = itemEntity.getItem();
            ItemExplodeable explodeable = (ItemExplodeable) stack.getItem();
            if(explodeable.isCooking(stack)) {
                e.setCanceled(true);
                explodeable.getExplodeableItemAction().onRemoveFromInventory(stack, player.world, player, explodeable.getMaxFuse() - explodeable.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.SHORT);
            }
        }
    }

    private boolean isZoneShrinking(WorldBorder border) {
        return border.getDiameter() != prevDiameter;
    }
}
