package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.entity.EntityDebuffs;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketClientCapabilitySync;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerData implements IPlayerData {

    private final InventoryBasic inventory;
    private final EntityPlayer player;
    private final BoostStats boostStats;
    private final AimInfo aimInfo;
    private final ReloadInfo reloadInfo;
    private final PlayerDebuffs debuffs;
    private boolean isProne;
    private boolean areNightVisionGogglesActive;

    public PlayerData() {
        this(null);
    }

    public PlayerData(EntityPlayer owner) {
        this.player = owner;
        this.boostStats = new BoostStats(this);
        this.aimInfo = new AimInfo(this);
        this.reloadInfo = new ReloadInfo();
        this.inventory = new InventoryBasic("pubgmc.equipment_inventory", false, SpecialEquipmentSlot.values().length);
        this.debuffs = new PlayerDebuffs();
    }

    @Override
    public void tick() {
        boostStats.onTick(player);
        aimInfo.onTick();
        reloadInfo.tick(this);
        debuffs.tick();
    }

    @Override
    public BoostStats getBoostStats() {
        return boostStats;
    }

    @Override
    public AimInfo getAimInfo() {
        return aimInfo;
    }

    @Override
    public ReloadInfo getReloadInfo() {
        return reloadInfo;
    }

    @Override
    public EntityPlayer getPlayer() {
        return player;
    }

    @Override
    public IInventory getEquipmentInventory() {
        return inventory;
    }

    @Override
    public ItemStack getSpecialItemFromSlot(SpecialEquipmentSlot slot) {
        return inventory.getStackInSlot(slot.ordinal());
    }

    @Override
    public EntityDebuffs getDebuffs() {
        return debuffs;
    }

    @Override
    public void setSpecialItemToSlot(SpecialEquipmentSlot slot, ItemStack stack) {
        inventory.setInventorySlotContents(slot.ordinal(), stack);
    }

    @Override
    public void setNightVisionActive(boolean status) {
        if (!getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION).isEmpty()) {
            areNightVisionGogglesActive = status;
        } else {
            areNightVisionGogglesActive = false;
        }
    }

    @Override
    public boolean isNightVisionActive() {
        return !getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION).isEmpty() && areNightVisionGogglesActive;
    }

    @Override
    public boolean isProne() {
        return isProne;
    }

    @Override
    public void setProne(boolean proning) {
        this.isProne = proning;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setTag("boostStats", boostStats.serializeNBT());
        c.setTag("aimInfo", aimInfo.serializeNBT());
        c.setTag("reloadInfo", reloadInfo.serializeNBT());
        c.setTag("inventory", SerializationHelper.inventoryToNbt(inventory));
        c.setTag("debuffs", debuffs.serializeNBT());
        c.setBoolean("activeNightVision", areNightVisionGogglesActive);
        c.setBoolean("prone", this.isProne);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        boostStats.deserializeNBT(nbt.getCompoundTag("boostStats"));
        aimInfo.deserializeNBT(nbt.getCompoundTag("aimInfo"));
        reloadInfo.deserializeNBT(nbt.getCompoundTag("reloadInfo"));
        debuffs.deserializeNBT(nbt.getCompoundTag("debuffs"));
        SerializationHelper.inventoryFromNbt(inventory, nbt.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
        areNightVisionGogglesActive = nbt.getBoolean("activeNightVision");
        isProne = nbt.getBoolean("prone");
    }

    @Override
    public void sync() {
        if (!player.world.isRemote) {
            PacketHandler.sendToAllClients(new PacketClientCapabilitySync(player.getUniqueID(), this.serializeNBT()));
        }
    }

    @Mod.EventBusSubscriber
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> e) {
            if (e.getObject() instanceof EntityPlayer) {
                e.addCapability(new ResourceLocation(Pubgmc.MOD_ID, "playerdata"), new PlayerDataProvider((EntityPlayer) e.getObject()));
            }
        }

        @SubscribeEvent
        public static void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
            IPlayerData data = getCap(e.player);
            data.getEquipmentInventory().clear();
            data.sync();
        }

        @SubscribeEvent
        public static void clone(net.minecraftforge.event.entity.player.PlayerEvent.Clone e) {
            Capability.IStorage<IPlayerData> storage = PlayerDataProvider.PLAYER_DATA.getStorage();
            IPlayerData oldData = getCap(e.getOriginal());
            IPlayerData newData = getCap(e.getEntityPlayer());
            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(PlayerDataProvider.PLAYER_DATA, oldData, null);
            storage.readNBT(PlayerDataProvider.PLAYER_DATA, newData, null, nbt);
            getCap(e.getEntityPlayer()).sync();
        }

        @SubscribeEvent
        public static void onDimensionChanged(PlayerEvent.PlayerChangedDimensionEvent e) {
            getCap(e.player).sync();
        }

        @SubscribeEvent
        public static void onStartTracking(net.minecraftforge.event.entity.player.PlayerEvent.StartTracking event) {
            if (event.getTarget() instanceof EntityPlayerMP) {
                EntityPlayer player = (EntityPlayer) event.getTarget();
                PacketHandler.sendToClient(new PacketClientCapabilitySync(player.getUniqueID(), PlayerDataProvider.get(player).serializeNBT()), (EntityPlayerMP) event.getEntityPlayer());
            }
        }

        public static IPlayerData getCap(EntityPlayer p) {
            if (p.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                return p.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            } else throw new IllegalStateException("[PUBGMC] Couldn't get player data for " + p.getName());
        }
    }
}
