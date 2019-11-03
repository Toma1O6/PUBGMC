package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.network.sp.PacketSyncGameData;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Game creation API
 *
 * @author Toma
 */
public abstract class Game {

    public final String registryName;
    public BlueZone zone;
    public int onlinePlayers;
    public GameInfo gameInfo;
    protected int gameTimer;
    private List<UUID> playersInGame;

    public Game(final String name) {
        this.playersInGame = new ArrayList<>();
        this.registryName = name;
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
    public abstract void onGameStopped(final World world);

    /**
     * Allows you to save additional data to disk
     */
    public abstract void writeDataToNBT(NBTTagCompound compound);

    public abstract void readDataFromNBT(NBTTagCompound compound);

    /**
     * Called when player gets killed
     *
     * @param player           - the player who died
     * @param entityLivingBase - source of death, may be null
     * @param gun              - the weapon itemstack used to kill this player, may be EMPTY
     */
    public abstract void onPlayerKilled(EntityPlayer player, @Nullable EntityLivingBase entityLivingBase, ItemStack gun, boolean headshot);

    /**
     * @return - new Bluezone instance
     */
    @Nonnull
    public abstract BlueZone initializeZone(final World world);

    /**
     * Decide what to do when player dies and attempts to respawn
     *
     * @return if player should return into the game
     */
    public boolean respawnPlayer(final EntityPlayer player) {
        return false;
    }

    /** If player death crate will be created on player death **/
    public boolean shouldCreateDeathCrate() {
        return true;
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

    /**
     * At which rate (ticks) will be online player display updated, default 100 (5s)
     */
    public int playerCounterUpdateFrequency() {
        return 100;
    }

    /**
     * Allows game instances to initialize additional variables upon command execution
     * Returns null as default
     *
     * @param sender - The ICommandSender who executed this command
     * @param server - The server this command got executed on
     * @param additionalArgs - all arguments after the 'start' keyword in command
     */
    @Nullable
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        return null;
    }

    /**
     * For autocompletion of additional game arguments
     * @param additonalArgIndex - the index of current argument
     * @param arg - actual argument value
     * @return array of possible values
     */
    public String[] getCommandAutoCompletions(int additonalArgIndex, String arg) {
        return new String[0];
    }

    /**
     * Additional information which will be displayed upon '/game info' command
     * execution.
     */
    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    /* ============================================[                   API END                    ]============================================ */
    /* ============================================[ SOME MAIN FUNCTIONALITY, NOTHING INTERESTING ]============================================ */

    public final boolean startGame(World world) {
        try {
            playersInGame = new ArrayList<>();
            IGameData gameData = this.getGameData(world);
            this.zone = this.initializeZone(world);
            this.populatePlayerList(world);
            this.onlinePlayers = playersInGame.size();
            if (playersInGame.size() < 1) {
                throw new IllegalStateException("Cannot start game because there are no valid players");
            }
            this.onGameStart(world);
            gameData.setGame(this);
            gameData.setGameID(PUBGMCUtil.generateID(7));
            gameTimer = 0;
            if (shouldUpdateTileEntities()) {
                GameUtils.updateLoadedTileEntities(world, this, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Pubgmc.logger.fatal(e.getMessage() + " => aborting game start!");
            return false;
        }
        return true;
    }

    public final void updatePlayerCounter(World world) {
        this.onlinePlayers = (int) playersInGame.stream().filter(uuid -> world.getPlayerEntityByUUID(uuid) != null).count();
    }

    public final void tickGame(World world) {
        try {
            IGameData gameData = this.getGameData(world);
            if (gameData.isPlaying()) {
                ++gameTimer;
                this.onGameTick(world);
                this.zone.bluezoneTick(world);
                if (gameTimer % playerCounterUpdateFrequency() == 0) {
                    updatePlayerCounter(world);
                    updateDataToClients(world);
                }
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
        if (data != null && data.getLobby() != null && data.getLobby().center.getY() > 0) {
            BlockPos pos = data.getLobby().center;
            Iterator<UUID> it = playersInGame.iterator();
            while (it.hasNext()) {
                UUID uuid = it.next();
                EntityPlayer player = world.getPlayerEntityByUUID(uuid);
                if (player != null) {
                    teleportEntityTo(player, pos.getX(), pos.getY() + 1, pos.getZ());
                }
            }
            data.setPlaying(false);
            this.onGameStopped(world);
            playersInGame = null;
            gameTimer = 0;
            updateDataToClients(world);
        }
    }

    public final List<UUID> getJoinedPlayers() {
        return playersInGame;
    }

    public final int getGameTimer() {
        return gameTimer;
    }

    public final List<EntityPlayer> getOnlinePlayers(World world) {
        List<EntityPlayer> list = new ArrayList<>();
        if(playersInGame == null || playersInGame.isEmpty()) return list;
        playersInGame.stream().filter(uuid -> world.getPlayerEntityByUUID(uuid) != null).forEach(uuid -> list.add(world.getPlayerEntityByUUID(uuid)));
        return list;
    }

    public final GameInfo getGameInformation() {
        return gameInfo;
    }

    public void notifyAllPlayers(World world, String message) {
        playersInGame.stream().filter(uuid -> world.getPlayerEntityByUUID(uuid) != null).forEach(uuid -> world.getPlayerEntityByUUID(uuid).sendMessage(new TextComponentString(message)));
    }

    public final void updateDataToClients(World world) {
        PacketHandler.sendToAllClients(new PacketSyncGameData(world.getCapability(IGameData.GameDataProvider.GAMEDATA, null)));
    }

    public final void updateDataToClient(World world, EntityPlayerMP player) {
        PacketHandler.sendToClient(new PacketSyncGameData(world.getCapability(IGameData.GameDataProvider.GAMEDATA, null)), player);
    }

    public final NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (playersInGame == null) {
            playersInGame = new ArrayList<>();
        }
        nbt.setInteger("timer", gameTimer);
        NBTTagList uuidList = new NBTTagList();
        playersInGame.forEach(uuid -> uuidList.appendTag(new NBTTagString(uuid.toString())));
        nbt.setTag("players", uuidList);
        if (zone != null) {
            nbt.setTag("zone", zone.serializeNBT());
        }

        this.writeDataToNBT(nbt);

        return nbt;
    }

    public final void readFromNBT(NBTTagCompound nbt) {
        playersInGame = new ArrayList<>();
        gameTimer = nbt.getInteger("timer");
        NBTTagList uuids = nbt.getTagList("players", Constants.NBT.TAG_STRING);
        uuids.forEach(tag -> playersInGame.add(UUID.fromString(((NBTTagString) tag).getString())));
        if (nbt.hasKey("zone")) {
            zone = BlueZone.fromNBT(nbt.getCompoundTag("zone"));
        }

        this.readDataFromNBT(nbt);
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

    public class GameInfo {

        public final String author;
        public final String[] gameInformation;

        public GameInfo(String author, String... gameInformation) {
            this.author = author;
            this.gameInformation = gameInformation;
        }
    }
}
