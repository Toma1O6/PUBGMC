package dev.toma.pubgmc.api.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.GamePhase;
import dev.toma.pubgmc.api.GamePlayerData;
import dev.toma.pubgmc.api.objectives.types.GameArea;
import dev.toma.pubgmc.api.settings.EntityDeathManager;
import dev.toma.pubgmc.api.settings.GameBotManager;
import dev.toma.pubgmc.api.settings.GameManager;
import dev.toma.pubgmc.api.settings.TeamManager;
import dev.toma.pubgmc.api.teams.Team;
import dev.toma.pubgmc.api.util.DeathMessage;
import dev.toma.pubgmc.api.util.EntityDeathContex;
import dev.toma.pubgmc.api.util.GameUtils;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketSyncGameData;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Game creation API
 * <p>
 * Version 1.1.0
 *
 * @author Toma
 */
public abstract class Game {

    /**
     * Used to render some extra stuff, like objectives etc
     */
    public static boolean isDebugMode = false;

    /**
     * Makes sure your objectives are not removed when you change mode
     */
    public static Map<ResourceLocation, Map<BlockPos, GameArea>> cachedObjectives = new HashMap<>();

    /**
     * Game registry name
     */
    public final ResourceLocation registryName;

    /**
     * Image in custom main menu
     */
    public final ResourceLocation guiImage;

    /**
     * The zone for the game, damages players outside of it
     */
    public BlueZone zone;
    /**
     * Contains information about this game
     */
    public GameInfo gameInfo;
    /**
     * Time elapsed since game start
     */
    public int gameTimer;
    /**
     * Amount of players in the game
     */
    public int onlinePlayers;
    /**
     * Amount of AI entities
     */
    public int botsInGame;
    public DeathMessage[] displayedDeathMessages;
    /**
     * List of all teams
     */
    protected List<Team> teamList = new ArrayList<>();
    /**
     * UUID list of all players who joined the game
     */
    private List<UUID> playersInGame;

    @Nonnull
    private GamePhase gamePhase = GamePhase.OFFLINE;

    private HashMap<UUID, GamePlayerData> playerDataMap = new HashMap<>();

    public Game(final String name, final ResourceLocation texture) {
        this(Loader.instance().activeModContainer().getModId(), name, texture);
    }

    public Game(final String modid, final String name, final ResourceLocation texture) {
        this(new ResourceLocation(modid, name), texture);
    }

    public Game(final ResourceLocation name, final ResourceLocation texture) {
        this.playersInGame = new ArrayList<>();
        this.registryName = name;
        this.onlinePlayers = 0;
        this.gameTimer = 0;
        this.guiImage = texture;
    }

    protected static NBTTagCompound saveObjectivesToNBT(Map<BlockPos, GameArea> map) {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList keyList = new NBTTagList();
        NBTTagList dataList = new NBTTagList();
        for (Map.Entry<BlockPos, GameArea> entry : map.entrySet()) {
            keyList.appendTag(NBTUtil.createPosTag(entry.getKey()));
            dataList.appendTag(GameArea.createNBT(entry.getValue()));
        }
        nbt.setTag("keys", keyList);
        nbt.setTag("data", dataList);
        return nbt;
    }

    protected static Map<BlockPos, GameArea> readObjectivesFromNBT(NBTTagCompound nbt) {
        Map<BlockPos, GameArea> map = new HashMap<>();
        if (!nbt.hasKey("keys") || !nbt.hasKey("data")) {
            return map;
        }
        NBTTagList keys = nbt.getTagList("keys", Constants.NBT.TAG_COMPOUND);
        NBTTagList data = nbt.getTagList("data", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < keys.tagCount(); i++) {
            map.put(NBTUtil.getPosFromTag(keys.getCompoundTagAt(i)), GameArea.getFromNBT(data.getCompoundTagAt(i)));
        }
        return map;
    }

    public static NBTTagCompound writeCachedObjectivesToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList keyList = new NBTTagList();
        NBTTagList dataList = new NBTTagList();
        for (Map.Entry<ResourceLocation, Map<BlockPos, GameArea>> entry : cachedObjectives.entrySet()) {
            keyList.appendTag(new NBTTagString(entry.getKey().toString()));
            dataList.appendTag(saveObjectivesToNBT(entry.getValue()));
        }
        nbt.setTag("keys", keyList);
        nbt.setTag("mappedObj", dataList);
        return nbt;
    }

    public static void readCachedObjectivesFromNBT(NBTTagCompound nbt) {
        cachedObjectives = new HashMap<>();
        if (!nbt.hasKey("keys") || !nbt.hasKey("mappedObj")) {
            return;
        }
        NBTTagList list = nbt.getTagList("keys", Constants.NBT.TAG_STRING);
        NBTTagList data = nbt.getTagList("mappedObj", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.tagCount(); i++) {
            cachedObjectives.put(new ResourceLocation(list.getStringTagAt(i)), readObjectivesFromNBT(data.getCompoundTagAt(i)));
        }
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
     * Called when game objective is reached
     *
     * @param team - the team which won
     */
    public abstract void onGameObjectiveReached(final World world, @Nullable final Team team);

    /**
     * Allows you to save additional data to disk
     */
    public abstract void writeDataToNBT(NBTTagCompound compound);

    public abstract void readDataFromNBT(NBTTagCompound compound);

    /**
     * @return - new Bluezone instance
     */
    @Nonnull
    public abstract BlueZone initializeZone(final World world);

    public abstract GameManager<Game> getGameManager();

    /**
     * Handles all bot related stuff
     */
    public abstract GameBotManager<Game> getBotManager();

    /**
     * Handles team creation and functionality
     */
    public abstract TeamManager<Game> getTeamManager();

    /**
     * Handles all death related events
     */
    public abstract EntityDeathManager getEntityDeathManager();

    /**
     * Decide what to do when player dies and attempts to respawn
     *
     * @return if player should return into the game
     */
    public boolean respawnPlayer(final EntityPlayer player) {
        return false;
    }

    /**
     * If player death crate will be created on player death
     **/
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
    public void renderGameInfo(Minecraft mc, ScaledResolution res) {
    }

    /* ============================================[                   API END                    ]============================================ */
    /* ============================================[ SOME MAIN FUNCTIONALITY, NOTHING INTERESTING ]============================================ */

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
     * @param sender         - The ICommandSender who executed this command
     * @param server         - The server this command got executed on
     * @param additionalArgs - all arguments after the 'start' keyword in command
     */
    @Nullable
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        return null;
    }

    /**
     * Additional information which will be displayed upon '/game info' command
     * execution.
     */
    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    /**
     * @param bot - the entity which got killed
     */
    public void onBotDeath(EntityAIPlayer bot) {
        if (this.botsInGame > 0) this.botsInGame--;
    }

    public final boolean prepareStart(World world) {
        try {
            this.playerDataMap = new HashMap<>(1);
            this.botsInGame = 0;
            this.playersInGame = new ArrayList<>();
            this.zone = this.initializeZone(world);
            this.populatePlayerList(world);
            this.onlinePlayers = playersInGame.size();
            this.teamList = this.getTeamManager().getTeamCreator().apply(this);
            this.getTeamManager().getTeamFillFactory().fill(this.getJoinedPlayers().iterator(), this.getTeamList().iterator(), this);
            this.displayedDeathMessages = new DeathMessage[this.getEntityDeathManager().getDeathMessagesCount()];
            if (playersInGame.size() < 1) {
                throw new IllegalStateException("Cannot start game because there are no valid players");
            }
            GameManager gameManager = this.getGameManager();
            boolean hasPrepStage = gameManager.getStartPhaseLength() > 0;
            IGameData gameData = this.getGameData(world);
            gameData.setGame(this);
            gameData.setGameID(PUBGMCUtil.generateID(7));
            if (hasPrepStage) {
                this.gameTimer = 0;
                this.setGamePhase(GamePhase.PREPARATION);
                return true;
            }
            this.startGame(world);
        } catch (Exception e) {
            e.printStackTrace();
            Pubgmc.logger.fatal("Exception occurred in game preparation phase, stopping!");
            return false;
        }
        return true;
    }

    public final void startGame(World world) {
        try {
            this.setGamePhase(GamePhase.RUNNING);
            this.onGameStart(world);
            gameTimer = 0;
            if (shouldUpdateTileEntities()) {
                GameUtils.updateLoadedTileEntities(world, this, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Pubgmc.logger.fatal(e.getMessage() + " => aborting game start!");
            this.setGamePhase(GamePhase.OFFLINE);
            this.onGameStopped(world);
        }
    }

    public final void updatePlayerCount(World world) {
        this.onlinePlayers = (int) playersInGame.stream().filter(uuid -> world.getPlayerEntityByUUID(uuid) != null).count();
    }

    public final void tickGame(World world) {
        try {
            IGameData gameData = this.getGameData(world);
            if (gameData.getCurrentGame().isRunning()) {
                if (this.getGamePhase() == GamePhase.PREPARATION) {
                    if (this.gameTimer >= this.getGameManager().getStartPhaseLength()) {
                        this.startGame(world);
                    }
                } else if (this.getGamePhase() == GamePhase.POST) {
                    if (this.gameTimer >= 160) {
                        this.stopGame(world);
                    }
                }
                ++gameTimer;
                this.onGameTick(world);
                this.zone.bluezoneTick(world);
                if (gameTimer % playerCounterUpdateFrequency() == 0) {
                    updatePlayerCount(world);
                    updateDataToClients(world);
                }
                GameManager<? super Game> manager = this.getGameManager();
                if (manager.shouldStopGame(this) && manager.gameStopVerification.test(this) && this.getGamePhase() == GamePhase.RUNNING) {
                    this.onGameObjectiveReached(world, this.getGameManager().getWinningTeam(this));
                    this.gameTimer = 0;
                    this.setGamePhase(GamePhase.POST);
                }
                this.tickDeathMessages();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setGamePhase(GamePhase.OFFLINE);
            Pubgmc.logger.fatal("Exception occured during game tick! Stopping game!");
            updateDataToClients(world);
        }
    }

    public final void addDeathMessage(EntityDeathContex contex) {
        PUBGMCUtil.shiftElementsInArray(this.displayedDeathMessages);
        this.displayedDeathMessages[0] = new DeathMessage(this.getEntityDeathManager(), contex);
    }

    public final void stopGame(World world) {
        this.setGamePhase(GamePhase.OFFLINE);
        IGameData data = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if (data != null && data.getLobby() != null && data.getLobby().center.getY() > 0) {
            BlockPos pos = data.getLobby().center;
            for (UUID uuid : playersInGame) {
                EntityPlayer player = world.getPlayerEntityByUUID(uuid);
                if (player != null) {
                    teleportEntityTo(player, pos.getX(), pos.getY() + 1, pos.getZ());
                }
            }
            this.onGameStopped(world);
            playersInGame = null;
            gameTimer = 0;
            updateDataToClients(world);
        }
    }

    @SideOnly(Side.CLIENT)
    public final void renderGameOverlay(Minecraft mc, ScaledResolution resolution) {
        this.renderGameInfo(mc, resolution);
        this.renderDeathMessages(mc, resolution);
    }

    public final List<UUID> getJoinedPlayers() {
        return playersInGame;
    }

    public final HashMap<UUID, GamePlayerData> getPlayerData() {
        return this.playerDataMap;
    }

    public final int getGameTimer() {
        return gameTimer;
    }

    public final List<EntityPlayer> getOnlinePlayers(World world) {
        List<EntityPlayer> list = new ArrayList<>();
        if (playersInGame == null || playersInGame.isEmpty()) return list;
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

    public final List<Team> getTeamList() {
        return teamList;
    }

    public final void readFromNBT(NBTTagCompound nbt) {
        if (playersInGame == null) {
            this.setGamePhase(GamePhase.OFFLINE);
        }
        playersInGame = new ArrayList<>();
        if (this.displayedDeathMessages == null) {
            EntityDeathManager manager = this.getEntityDeathManager();
            this.displayedDeathMessages = new DeathMessage[manager == null ? 0 : manager.getDeathMessagesCount()];
        }
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

    public final void teleportEntityTo(EntityLivingBase entity, int x, int y, int z) {
        entity.posX = x + 0.5;
        entity.posY = y;
        entity.posZ = z + 0.5;
        entity.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
    }

    public final boolean isRunning() {
        return gamePhase != null && this.gamePhase != GamePhase.OFFLINE;
    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    private void tickDeathMessages() {
        for (int i = 0; i < this.displayedDeathMessages.length; i++) {
            DeathMessage msg = this.displayedDeathMessages[i];
            if (msg == null) return;
            msg.tick(i, this);
        }
    }

    @SideOnly(Side.CLIENT)
    private void renderDeathMessages(Minecraft mc, ScaledResolution resolution) {
        for (int i = 0; i < displayedDeathMessages.length; i++) {
            DeathMessage deathMessage = displayedDeathMessages[i];
            if (deathMessage == null) return;
            deathMessage.draw(this, mc, resolution, 15, 40 + i * 12);
        }
    }

    public class GameInfo {

        public final String author;
        public final String[] gameInformation;
        public final String[] commandArguments;

        public GameInfo(String author, @Nullable String[] commandArguments, String... gameInformation) {
            this.author = author;
            this.commandArguments = commandArguments;
            this.gameInformation = gameInformation;
        }
    }
}
