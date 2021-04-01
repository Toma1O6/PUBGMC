package dev.toma.pubgmc.common;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.entity.throwables.EntityThrowableExplodeable;
import dev.toma.pubgmc.common.items.ItemExplodeable;
import dev.toma.pubgmc.common.items.MainHandOnly;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.event.LandmineExplodeEvent;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.CPacketAnimation;
import dev.toma.pubgmc.network.client.PacketGetConfigFromServer;
import dev.toma.pubgmc.network.client.PacketLoadConfig;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.handlers.CustomDateEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonEvents {

    public static final HashMap<UUID, NBTTagCompound> CONFIGS = new HashMap<>();
    public static final AttributeModifier PRONE_MODIFIER = new AttributeModifier(UUID.fromString("42b68862-2bdc-4df4-9fbe-4ad597cda211"), "Prone modifier", -0.7, 2);
    Map<UUID, Integer> selectedSlotCache = new HashMap<>();

    private static void handleUpdateResults(ForgeVersion.CheckResult result, EntityPlayer player) {
        switch (result.status) {
            case AHEAD: {
                sendMessage(player, "[PUBGMC] It appears you're using early access version, bugs might occur. Report them please", TextFormatting.LIGHT_PURPLE);
                break;
            }
            case UP_TO_DATE: {
                sendMessage(player, "You have the newest version of PUBGMC!", TextFormatting.GREEN);
                TextComponentString discordNotification = new TextComponentString(TextFormatting.GREEN + "Join my official " + TextFormatting.AQUA + "DISCORD" + TextFormatting.GREEN + ". Click HERE");
                discordNotification.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/WmdUKZz")));
                player.sendMessage(discordNotification);
                break;
            }

            case OUTDATED: case BETA_OUTDATED: {
                sendMessage(player, "[PUBGMC] You are using old version! Get a new one.", TextFormatting.YELLOW);
                TextComponentString comp = new TextComponentString(TextFormatting.YELLOW + "New version is available! You can get it " + TextFormatting.ITALIC + "HERE");
                comp.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/pubgmc-mod/files")));
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

    @SubscribeEvent
    public void livingChangeEquipment(LivingEquipmentChangeEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            AbstractAttributeMap map = player.getAttributeMap();
            ItemStack stack = event.getTo();
            IAttributeInstance instance = map.getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);
            instance.removeModifier(GunBase.EQUIP_MODIFIER_UID);
            if(stack.getItem() instanceof GunBase) {
                instance.applyModifier(((GunBase) stack.getItem()).getGunType().getModifier());
                int last = selectedSlotCache.getOrDefault(player.getUniqueID(), 0);
                if(last != player.inventory.currentItem) {
                    PacketHandler.sendToClient(new CPacketAnimation(true, AnimationType.EQUIP_ANIMATION_TYPE), (EntityPlayerMP) player);
                }
            }
            if(event.getSlot() == EntityEquipmentSlot.MAINHAND) {
                selectedSlotCache.put(player.getUniqueID(), player.inventory.currentItem);
            }
        }
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
    public void attachWorldCapability(AttachCapabilitiesEvent<World> e) {
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":worldData"), new IWorldData.WorldDataProvider());
        e.addCapability(new ResourceLocation(Pubgmc.MOD_ID + ":gameData"), new IGameData.GameDataProvider());
    }

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
        if(ev.phase == Phase.END)
            return;
        data.tick();
        if((!player.onGround || player.isSprinting() || player.isSneaking()) && data.isProning() && !player.world.isRemote) {
            data.setProning(false);
            data.sync();
        }
        if (!player.world.isRemote) {
            ItemStack offHandStack = player.getHeldItemOffhand();
            if (offHandStack.getItem() instanceof MainHandOnly) {
                ((MainHandOnly) offHandStack.getItem()).block(offHandStack, player);
            }
        }
    }

    //Once player logs in
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent e) {
        if (ConfigPMC.client.other.messagesOnJoin.get()) {
            if (e.player instanceof EntityPlayer) {
                ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
                handleUpdateResults(version, e.player);
            }
        }
        selectedSlotCache.put(e.player.getUniqueID(), e.player.inventory.currentItem);

        if (e.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) e.player;
            if (player != null && !player.world.isRemote) {
                PacketHandler.sendToClient(new PacketGetConfigFromServer(ConfigPMC.common.serializeNBT()), player);
                //We get the last player data and later sync it to client
                player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                data.getAimInfo().setAiming(false, 1.0F);
                //Sync some data from capability to client for overlay rendering
                PacketHandler.syncPlayerDataToClient(data, player);

                IGameData gameData = player.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
                if(gameData != null) {
                    gameData.getCurrentGame().updateDataToClient(player.world, player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent e) {
        if(e.player instanceof EntityPlayerMP) {
            selectedSlotCache.remove(e.player.getUniqueID());
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
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(PRONE_MODIFIER);
            if(data.isProning()) {
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(PRONE_MODIFIER);
            }

            if (data.getEquippedNV()) {
                if (data.isUsingNV() && !data.getEquippedNV()) {
                    data.setNV(false);
                }

                if (data.isUsingNV()) {
                    if (!player.world.isRemote) {
                        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
                    }
                } else {
                    if (!player.world.isRemote) {
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

        if (itemEntity.getItem().getItem() == PMCItems.IBLOCK) {
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
}
