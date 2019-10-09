package com.toma.pubgmc.api.games;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameUtils;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.util.helper.LootHelper;
import com.toma.pubgmc.util.math.ZonePos;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GameDeathmatch extends Game {

    private int lootType = 0;
    private GunBase.GunType spawnWeaponType;
    private int matchDuration;
    private boolean airdrops;

    public GameDeathmatch(String modeName) {
        super(modeName);
    }

    @Override
    public void populatePlayerList(World world) {
        world.playerEntities.forEach(player -> this.getJoinedPlayers().add(player.getUniqueID()));
    }

    @Nonnull
    @Override
    public BlueZone initializeZone(World world) {
        ZoneSettings settings = ZoneSettings.Builder.create().damage(10.0F).size(60).setStatic().build();
        return new BlueZone(this.getGameData(world), settings);
    }

    @Override
    public void onGameStart(World world) {
        this.getJoinedPlayers().forEach(player -> this.spawnAtRandomPosition(world.getPlayerEntityByUUID(player)));
    }

    @Override
    public void onGameTick(World world) {
        if(airdrops) {
            if(gameTimer % 1200 == 0) this.spawnAirdrop(world);
        }
    }

    @Override
    public void onGameStopped(World world) {
        GameUtils.teleportPlayersIntoLobby(world, this);
    }

    @Override
    public void onPlayerKilled(EntityPlayer player, @Nullable EntityLivingBase entityLivingBase, ItemStack gun, boolean headshot) {
    }

    @Override
    public boolean respawnPlayer(EntityPlayer player) {
        IPlayerData playerData = player.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
        playerData.setBackpackLevel(3);
        playerData.sync(player);
        return true;
    }

    @Override
    public boolean shouldCreateDeathCrate() {
        return false;
    }

    @Override
    public boolean shouldUpdateTileEntities() {
        return true;
    }

    @Nullable
    @Override
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        if(additionalArgs.length < 3) {
            return new CommandException("Additional arguments needed! Use /game start <weaponClass> <weaponRarity> <matchDuration[seconds]> <airdrops>");
        }
        GunBase.GunType type = GunBase.GunType.getTypeFromName(additionalArgs[0]);
        if(type == null) return new CommandException("Unknown weapon class!");
        int loot = additionalArgs[1].equalsIgnoreCase("normalWeapons") ? 0 : additionalArgs[1].equalsIgnoreCase("allowDropWeapons") ? 1 : additionalArgs[1].equalsIgnoreCase("onlyDropWeapons") ? 2 : -1;
        if(loot < 0) return new CommandException("Unknown loot style! Valid styles are [normalWeapons, allowDropWeapons, onlyDropWeapons]");
        boolean airdrops = additionalArgs.length >= 4 && Boolean.parseBoolean(additionalArgs[3]);
        int matchDuration;
        try {
            matchDuration = Integer.parseInt(additionalArgs[2]);
        } catch (NumberFormatException e) {
            return new CommandException(additionalArgs[2] + " is not a valid number!");
        }
        this.spawnWeaponType = type;
        this.lootType = loot;
        this.matchDuration = matchDuration;
        this.airdrops = airdrops;
        return null;
    }

    @Override
    public String[] getCommandAutoCompletions(int additonalArgIndex, String arg) {
        if(additonalArgIndex == 0) {
            return new String[] {"pistol", "smg", "shotgun", "ar", "dmr", "sniper"};
        } else if(additonalArgIndex == 1) {
            return new String[] {"normalWeapons", "allowDropWeapons", "onlyDropWeapons"};
        } else if(additonalArgIndex == 3) {
            return new String[] {"true", "false"};
        }
        return new String[0];
    }

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {
        compound.setBoolean("airdrops", this.airdrops);
        compound.setInteger("lootRarity", this.lootType);
        compound.setInteger("lootClass", this.spawnWeaponType.ordinal());
        compound.setInteger("matchDuration", this.matchDuration);
    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {
        this.airdrops = compound.getBoolean("airdrops");
        this.lootType = compound.getInteger("lootRarity");
        this.spawnWeaponType = GunBase.GunType.values()[compound.getInteger("lootClass")];
        this.matchDuration = compound.getInteger("matchDuration");
    }

    private void addLootIntoInventory(EntityPlayer player) {
        GunBase item = LootHelper.getRandomWeapon(new GunBase.GunType[] {spawnWeaponType}, lootType);
        ItemStack stack = new ItemStack(item);
        NBTTagCompound nbt = this.createAttachmentNBT(item, stack);
        stack.setTagCompound(nbt);
        player.addItemStackToInventory(stack);
        for(int i = 0; i < 5; i++) {
            player.addItemStackToInventory(item.getAmmoType().ammoStack(ConfigPMC.items().ammoLimit));
        }
        player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.PAINKILLERS));
        player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BANDAGE, 5));
        player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.FIRSTAIDKIT));
    }

    private void spawnAtRandomPosition(EntityPlayer player) {
        if(player == null) return;
        ZonePos min = this.zone.currentBounds.min();
        int maxDist = (int)Math.abs(this.zone.currentBounds.max().x - min.x);
        BlockPos pos = new BlockPos(min.x + Pubgmc.rng().nextInt(maxDist), 250, min.z + Pubgmc.rng().nextInt(maxDist));
        player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        this.addLootIntoInventory(player);
    }

    private NBTTagCompound createAttachmentNBT(GunBase gun, ItemStack stack) {
        NBTTagCompound nbt = new NBTTagCompound();
        int ordinal = gun.getGunType().ordinal();
        int scopeType = ordinal == 1 || ordinal == 3 ? 1 : ordinal > 3 ? ordinal : 0;
        int ammoAmount = gun.getWeaponAmmoLimit(stack);
        nbt.setBoolean("isValidWeapon", true);
        nbt.setInteger("ammo", ammoAmount);
        nbt.setInteger("scope", scopeType);
        return nbt;
    }

    private void spawnAirdrop(World world) {
        List<EntityPlayer> players = this.getOnlinePlayers(world);
        EntityPlayer player = players.get(Pubgmc.rng().nextInt(players.size()));
        PUBGMCUtil.spawnAirdrop(world, new BlockPos(player.posX, player.posY + 100, player.posZ), false);
    }
}
