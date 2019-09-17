package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSyncGameData;
import com.toma.pubgmc.init.GameRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Game creation API
 *
 * @author Toma
 */
public abstract class Game implements INBTSerializable<NBTTagCompound> {

    public BlueZone zone;
    public final ResourceLocation registryName;

    protected int gameTimer;
    protected List<EntityPlayer> joinedPlayers;
    private List<UUID> temporaryPlayerStorage;

    public Game(final ResourceLocation registryName) {
        this.joinedPlayers = new ArrayList<>();
        this.registryName = registryName;
        GameRegistry.registerGame(registryName, this);
    }

    /**
     * To tell the game which players are going to
     * be able to play
     */
    public abstract void populatePlayerList(final World world);

    /**
     * Additional actions for game start
     */
    public abstract void onGameStart(final World world);

    /**
     * Gets executed on every world tick
     */
    public abstract void onGameTick(final World world);

    /**
     * Called when game is getting stopped
     */
    public abstract void onGameStopped(final World world, Game game);

    /**
     * @return - new Bluezone instance
     */
    @Nonnull
    public abstract BlueZone initializeZone(final World world);

    /**
     * Decide what to do when player dies and attempts to respawn
     * @return if player should get removed from the game
     */
    public boolean respawnPlayer(final EntityPlayer player) {
        return false;
    }

    /**
     * This will allow all instances of IGameTileEntity to call it's onLoaded function
     * (for example this is used for loot spawners to generate loot and windows to renew glass)
     */
    public boolean shouldUpdateTileEntities() {
        return true;
    }

    /**
     * Minimal amount of players to continue in game. Only called once one player dies
     */
    public int getMinimalPlayerCount() {
        return 2;
    }

    /**
     * For rendering whatever game information you might want onto player's screen
     */
    @SideOnly(Side.CLIENT)
    public void renderGameInfo(ScaledResolution res) {

    }

    /* ============================================[                   API END                    ]============================================ */
    /* ============================================[ SOME MAIN FUNCTIONALITY, NOTHING INTERESTING ]============================================ */

    public final boolean startGame(World world) {
        try {
            IGameData gameData = this.getGameData(world);
            this.zone = this.initializeZone(world);
            this.populatePlayerList(world);
            if(joinedPlayers.size() < 1) {
                throw new IllegalStateException("Cannot start game as there are no valid players");
            }
            this.onGameStart(world);
            gameData.setGame(this);
            gameData.setGameID(PUBGMCUtil.generateID(7));
            gameTimer = 0;
            if(shouldUpdateTileEntities()) {
                GameUtils.updateLoadedTileEntities(world, this, true);
            }
        } catch (Exception e) {
            Pubgmc.logger.fatal(e.getMessage()  + " => aborting game start!");
            return false;
        }
        return true;
    }

    public final void tickGame(World world) {
        try {
            IGameData gameData = this.getGameData(world);
            if(gameData.isPlaying()) {
                if(temporaryPlayerStorage != null) {
                    temporaryPlayerStorage.forEach(uuid -> joinedPlayers.add(world.getPlayerEntityByUUID(uuid)));
                    temporaryPlayerStorage = null;
                    joinedPlayers = joinedPlayers.stream().filter(Objects::nonNull).collect(Collectors.toList());
                }
                ++gameTimer;
                this.onGameTick(world);
                this.zone.bluezoneTick(world);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getGameData(world).setPlaying(false);
            Pubgmc.logger.fatal("Exception occured during game tick! Stopping game!");
            updateDataToClients(world);
        }
    }

    public final void stopGame(World world) {
        IGameData data = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if(data != null && data.getLobby() != null && data.getLobby().center.getY() > 0) {
            BlockPos pos = data.getLobby().center;
            joinedPlayers.forEach(p -> teleportEntityTo(p, pos.getX(), pos.getY() + 1, pos.getZ()));
            data.setPlaying(false);
            this.onGameStopped(world, data.getCurrentGame());
            updateDataToClients(world);
        }
    }

    public final void onPlayerRespawning(EntityPlayer player) {
        if(joinedPlayers.contains(player)) {
            boolean remove = this.respawnPlayer(player);
            if(remove) {
                joinedPlayers.remove(player);
                this.updateDataToClients(player.world);
            }
        }
    }

    public final List<EntityPlayer> getJoinedPlayers() {
        return joinedPlayers;
    }

    public final int getGameTimer() {
        return gameTimer;
    }

    public void notifyAllPlayers(String message) {
        joinedPlayers.forEach(p -> p.sendMessage(new TextComponentString(message)));
    }

    public final void updateDataToClients(World world) {
        PacketHandler.sendToAllClients(new PacketSyncGameData(world.getCapability(IGameData.GameDataProvider.GAMEDATA, null)));
    }

    public final void updateDataToClient(World world, EntityPlayerMP player) {
        PacketHandler.sendToClient(new PacketSyncGameData(world.getCapability(IGameData.GameDataProvider.GAMEDATA, null)), player);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("timer", gameTimer);
        NBTTagList uuidList = new NBTTagList();
        joinedPlayers.forEach(p -> uuidList.appendTag(new NBTTagString(p.getUniqueID().toString())));
        nbt.setTag("players", uuidList);
        if(zone != null) {
            nbt.setTag("zone", zone.serializeNBT());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        temporaryPlayerStorage = new ArrayList<>();
        joinedPlayers.clear();
        gameTimer = nbt.getInteger("timer");
        NBTTagList uuids = nbt.getTagList("players", Constants.NBT.TAG_STRING);
        uuids.forEach(tag -> temporaryPlayerStorage.add(UUID.fromString(((NBTTagString) tag).getString())));
        if(nbt.hasKey("zone")) {
            zone = BlueZone.fromNBT(nbt.getCompoundTag("zone"));
        }
    }

    public IGameData getGameData(World world) {
        return world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
    }

    protected final void teleportEntityTo(EntityLivingBase entity, int x, int y, int z) {
        entity.posX = x + 0.5;
        entity.posY = y;
        entity.posZ = z + 0.5;
        entity.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
    }
}
