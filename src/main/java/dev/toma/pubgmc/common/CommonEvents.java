package dev.toma.pubgmc.common;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.item.Backpack;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.client.content.ContentResult;
import dev.toma.pubgmc.client.content.ExternalLinks;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.throwables.EntityThrowableExplodeable;
import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import dev.toma.pubgmc.common.items.ItemExplodeable;
import dev.toma.pubgmc.common.items.MainHandOnly;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketAnimation;
import dev.toma.pubgmc.network.s2c.S2C_PacketNotifyRestoreConfig;
import dev.toma.pubgmc.network.s2c.S2C_PacketReceiveServerConfig;
import dev.toma.pubgmc.util.handlers.CustomDateEvents;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonEvents {

    public static final AttributeModifier PRONE_MODIFIER = new AttributeModifier(UUID.fromString("42b68862-2bdc-4df4-9fbe-4ad597cda211"), "Prone modifier", -0.7, 2);
    Map<UUID, Integer> selectedSlotCache = new HashMap<>();

    private static void handleUpdateResults(ForgeVersion.CheckResult result, EntityPlayer player) {
        ExternalLinks links = Pubgmc.getContentManager().getResultOptionally().map(ContentResult::getExternalLinks).orElse(ExternalLinks.DEFAULT);
        switch (result.status) {
            case AHEAD: {
                sendMessage(player, "[PUBGMC] It appears you're using early access version, bugs might occur. Report them please", TextFormatting.LIGHT_PURPLE);
                break;
            }
            case UP_TO_DATE: {
                sendMessage(player, "You have the newest version of PUBGMC!", TextFormatting.GREEN);
                TextComponentString discordNotification = new TextComponentString(TextFormatting.GREEN + "Join my official " + TextFormatting.AQUA + "DISCORD" + TextFormatting.GREEN + ". Click HERE");
                discordNotification.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, links.getDiscordLink())));
                player.sendMessage(discordNotification);
                break;
            }

            case OUTDATED:
            case BETA_OUTDATED: {
                sendMessage(player, "[PUBGMC] You are using old version! Get a new one.", TextFormatting.YELLOW);
                TextComponentString comp = new TextComponentString(TextFormatting.YELLOW + "New version is available! You can get it " + TextFormatting.ITALIC + "HERE");
                comp.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, links.getHomepageLink())));
                player.sendMessage(comp);
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

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityAIPlayer && !event.isCanceled()) {
            EntityAIPlayer aiPlayer = (EntityAIPlayer) event.getEntity();
            UUID gameId = GameHelper.getGameUUID(event.getWorld());
            if (gameId.equals(GameHelper.DEFAULT_UUID)) {
                LoadoutManager.apply(aiPlayer, EntityAIPlayer.DEFAULT_LOADOUT);
            }
        }
    }

    @SubscribeEvent
    public void livingChangeEquipment(LivingEquipmentChangeEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            AbstractAttributeMap map = player.getAttributeMap();
            ItemStack stack = event.getTo();
            IAttributeInstance instance = map.getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);
            instance.removeModifier(GunBase.EQUIP_MODIFIER_UID);
            if (stack.getItem() instanceof GunBase) {
                instance.applyModifier(((GunBase) stack.getItem()).getGunType().getModifier());
                int last = selectedSlotCache.getOrDefault(player.getUniqueID(), 0);
                if (last != player.inventory.currentItem) {
                    PacketHandler.sendToClient(new S2C_PacketAnimation(true, AnimationType.EQUIP_ANIMATION_TYPE), (EntityPlayerMP) player);
                }
            }
            if (event.getSlot() == EntityEquipmentSlot.MAINHAND) {
                selectedSlotCache.put(player.getUniqueID(), player.inventory.currentItem);
            }
        }
    }

    @SubscribeEvent
    public void attachWorldCapability(AttachCapabilitiesEvent<World> event) {
        World world = event.getObject();
        event.addCapability(Pubgmc.getResource("games"), new GameDataProvider(world));
        event.addCapability(Pubgmc.getResource("party"), new PartyDataProvider(world));
    }

    @SubscribeEvent
    public void attachChunkCapability(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(Pubgmc.getResource("chunk_data"), new ChunkGameBlockDataProvider());
    }

    @SubscribeEvent
    public void cancelKnockback(LivingKnockBackEvent event) {
        if (!ConfigPMC.common.players.knockbackEnabled.get()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onTick(PlayerTickEvent ev) {
        if (ev.phase == Phase.END)
            return;
        EntityPlayer player = ev.player;
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        data.tick();
        if ((!player.onGround || player.isSprinting() || player.isSneaking()) && data.isProne() && !player.world.isRemote) {
            data.setProne(false, true);
            data.sync();
        }
        if (!player.world.isRemote) {
            ItemStack offHandStack = player.getHeldItemOffhand();
            if (offHandStack.getItem() instanceof MainHandOnly) {
                ((MainHandOnly) offHandStack.getItem()).dropItemFromInvalidSlot(offHandStack, player);
            }
        }
    }

    //Once player logs in
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        if (player == null)
            return;
        if (ConfigPMC.client.other.messagesOnJoin.get()) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            handleUpdateResults(version, player);
        }
        selectedSlotCache.put(event.player.getUniqueID(), event.player.inventory.currentItem);
        PacketHandler.sendToClient(new S2C_PacketReceiveServerConfig(ConfigPMC.common.serializeNBT()), player);
        //We get the last player data and later sync it to client
        player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        data.getAimInfo().setAiming(false, 1.0F);
        //Sync some data from capability to client for overlay rendering
        PacketHandler.syncPlayerDataToClient(data, player);
        PacketHandler.syncGameDataToClient(player);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent e) {
        if (e.player instanceof EntityPlayerMP) {
            selectedSlotCache.remove(e.player.getUniqueID());
            IPlayerData data = PlayerDataProvider.get(e.player);
            data.getAimInfo().setAiming(false, 1.0F);
            data.sync();
            PacketHandler.sendToClient(new S2C_PacketNotifyRestoreConfig(), (EntityPlayerMP) e.player);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(PRONE_MODIFIER);
            if (data.isProne()) {
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(PRONE_MODIFIER);
            }
        }
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase || stack.getItem() instanceof ItemExplodeable) {
            event.setCanceled(true);
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
        //Ammo returning back to inventory
        if (itemEntity.getItem().getItem() instanceof GunBase) {
            ItemStack stack = itemEntity.getItem();

            if (stack.hasTagCompound() && !player.capabilities.isCreativeMode) {
                GunBase gun = (GunBase) stack.getItem();
                int ammo = stack.getTagCompound().getInteger("ammo");

                player.addItemStackToInventory(new ItemStack(gun.getAmmoType().ammo(), ammo));
                stack.getTagCompound().setInteger("ammo", 0);
            }
        } else if (itemEntity.getItem().getItem() instanceof ItemExplodeable) {
            ItemStack stack = itemEntity.getItem();
            ItemExplodeable explodeable = (ItemExplodeable) stack.getItem();
            if (explodeable.isCooking(stack)) {
                e.setCanceled(true);
                explodeable.getExplodeableItemAction().onRemoveFromInventory(stack, player.world, player, explodeable.getMaxFuse() - explodeable.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.SHORT);
            }
        }
    }

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItem().getItem();
        IPlayerData data = PlayerDataProvider.get(player);
        if (data == null) {
            return;
        }
        if (!GameHelper.hasRestrictedInventory(player.world)) {
            return;
        }
        ItemStack backpackStack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.BACKPACK);
        int slotCount = 9;
        if (!backpackStack.isEmpty() && backpackStack.getItem() instanceof Backpack) {
            Backpack backpack = (Backpack) backpackStack.getItem();
            slotCount += backpack.unlockSlotCount();
        }
        boolean canInsert = false;
        for (int i = 0; i < 36; i++) {
            ItemStack inventoryItem = player.inventory.getStackInSlot(i);
            if (i >= slotCount) {
                break;
            }
            if (inventoryItem.isEmpty()) {
                canInsert = true;
                break;
            }
            if (areItemsSameTypeAndNbt(stack, inventoryItem)) {
                if (inventoryItem.getCount() + stack.getCount() <= inventoryItem.getMaxStackSize()) {
                    canInsert = true;
                    break;
                }
            }
        }
        if (!canInsert) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void getVehicleCollisionBoxes(GetCollisionBoxesEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityDriveable) {
            EntityDriveable driveable = (EntityDriveable) entity;
            EntityVehiclePart[] parts = driveable.getParts();
            if (parts != null) {
                for (EntityVehiclePart part : parts) {
                    AxisAlignedBB collisionBox = part.getCollisionBoundingBox();
                    if (collisionBox != null) {
                        //event.getCollisionBoxesList().add(collisionBox);
                    }
                }
            }
        }
    }

    private static boolean areItemsSameTypeAndNbt(ItemStack stack1, ItemStack stack2) {
        if (!ItemStack.areItemsEqual(stack1, stack2)) {
            return false;
        }
        if (stack1.getTagCompound() == null && stack2.getTagCompound() != null) {
            return false;
        }
        return (stack1.getTagCompound() == null || stack1.getTagCompound().equals(stack2.getTagCompound())) && stack1.areCapsCompatible(stack2);
    }
}
