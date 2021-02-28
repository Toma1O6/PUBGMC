package dev.toma.pubgmc.common.capability.player;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.sp.PacketClientCapabilitySync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerData implements IPlayerData {

    final EntityPlayer player;
    final BoostStats boostStats;
    final AimInfo aimInfo;

    private boolean reloading;
    private boolean nv;
    private int reloading_time;

    private boolean isProne;

    private int level;
    private boolean eqNV;

    private int scopetype;
    private int scopecolor;

    private double dist;

    public PlayerData() {
        this(null);
    }

    public PlayerData(EntityPlayer owner) {
        this.player = owner;
        this.boostStats = new BoostStats(this);
        this.aimInfo = new AimInfo(this);
    }

    public static IPlayerData get(EntityPlayer player) {
        return player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
    }

    @Override
    public void tick() {
        boostStats.onTick(player);
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
    public EntityPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isAiming() {
        return aimInfo.isAiming();
    }

    @Override
    public void setBackpackLevel(int level) {
        this.level = level;
    }

    @Override
    public int getBackpackLevel() {
        return this.level;
    }

    @Override
    public void hasEquippedNV(boolean nv) {
        this.eqNV = nv;
    }

    @Override
    public boolean getEquippedNV() {
        return this.eqNV;
    }

    @Override
    public boolean isReloading() {
        return this.reloading;
    }

    @Override
    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public boolean isUsingNV() {
        return this.nv;
    }

    @Override
    public int getReloadingTime() {
        return this.reloading_time;
    }

    @Override
    public void setReloadingTime(int rt) {
        this.reloading_time = rt;
    }

    @Override
    public void setNV(boolean nv) {
        this.nv = nv;
    }

    @Override
    public int getScopeType() {
        return scopetype;
    }

    @Override
    public void setScopeType(int type) {
        this.scopetype = type;
    }

    @Override
    public int getScopeColor() {
        return scopecolor;
    }

    @Override
    public void setScopeColor(int color) {
        this.scopecolor = color;
    }

    @Override
    public double getDistance() {
        return dist;
    }

    @Override
    public void setDistance(double dist) {
        this.dist = dist;
    }

    @Override
    public boolean isProning() {
        return isProne;
    }

    @Override
    public void setProning(boolean proning) {
        this.isProne = proning;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setTag("boostStats", boostStats.serializeNBT());
        c.setTag("aimInfo", aimInfo.serializeNBT());
        c.setInteger("level", level);
        c.setBoolean("eqnv", eqNV);
        c.setInteger("scopetype", scopetype);
        c.setInteger("scopecolor", scopecolor);
        c.setBoolean("reload", this.reloading);
        c.setBoolean("prone", this.isProne);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        deserialize(boostStats, "boostStats", nbt);
        deserialize(aimInfo, "aimInfo", nbt);
        level = nbt.getInteger("level");
        eqNV = nbt.getBoolean("eqnv");
        scopetype = nbt.getInteger("scopetype");
        scopecolor = nbt.getInteger("scopecolor");
        reloading = nbt.getBoolean("reload");
        isProne = nbt.getBoolean("prone");
    }

    @Override
    public void sync() {
        PacketHandler.sendToAllClients(new PacketClientCapabilitySync(player, this.serializeNBT()));
    }

    static void deserialize(INBTSerializable<NBTTagCompound> serializable, String key, NBTTagCompound nbt) {
        serializable.deserializeNBT(nbt.hasKey(key) ? nbt.getCompoundTag(key) : new NBTTagCompound());
    }

    @Mod.EventBusSubscriber
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> e) {
            if(e.getObject() instanceof EntityPlayer) {
                e.addCapability(new ResourceLocation(Pubgmc.MOD_ID, "playerdata"), new PlayerDataProvider((EntityPlayer) e.getObject()));
            }
        }

        @SubscribeEvent
        public static void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
            getCap(e.player).sync();
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
            if(event.getTarget() instanceof EntityPlayerMP) {
                EntityPlayer player = (EntityPlayer) event.getTarget();
                PacketHandler.sendToClient(new PacketClientCapabilitySync(player, PlayerData.get(player).serializeNBT()), (EntityPlayerMP) event.getEntityPlayer());
            }
        }

        public static IPlayerData getCap(EntityPlayer p) {
            if(p.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                return p.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            } else throw new IllegalStateException("[PUBGMC] Couldn't get player data for " + p.getName());
        }
    }
}
