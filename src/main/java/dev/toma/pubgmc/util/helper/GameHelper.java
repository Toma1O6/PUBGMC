package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.mutator.GameMutatorManager;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.team.Team;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.common.games.util.WorldGameBlockHelper;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketReloadChunks;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GameHelper {

    public static final Marker MARKER = MarkerManager.getMarker("GameEngine");
    public static final UUID DEFAULT_UUID = new UUID(0L, 0L);

    public static void spawnPlayerDeathCrate(UUID gameId, EntityPlayer player) {
        World world = player.world;
        List<InventoryProvider> inventoryProviders = new ArrayList<>();
        inventoryProviders.add(InventoryProvider.inventory(0, player.inventory));
        IPlayerData data = PlayerDataProvider.get(player);
        if (data != null) {
            inventoryProviders.add(InventoryProvider.inventory(41, data.getEquipmentInventory()));
        }
        spawnDeathCrate(gameId, world, player.getPosition(), inventoryProviders);
        player.inventory.clear();
        if (data != null) {
            data.getEquipmentInventory().clear();
            data.sync();
        }
    }

    public static void spawnAiPlayerDeathCrate(UUID gameId, EntityAIPlayer player) {
        World world = player.world;
        List<InventoryProvider> providers = new ArrayList<>();
        providers.add(InventoryProvider.wrappedEquipment(0, player));
        providers.add(InventoryProvider.inventory(EntityEquipmentSlot.values().length, player.getInventory()));
        providers.add(InventoryProvider.inventory(SpecialEquipmentSlot.values().length, player.getSpecialEquipmentInventory()));
        spawnDeathCrate(gameId, world, player.getPosition(), providers);
        player.getInventory().clear();
        PUBGMCUtil.clearEntityInventory(player);
    }

    public static void spawnDeathCrate(UUID gameId, World world, BlockPos pos, List<InventoryProvider> inventories) {
        BlockPos ground = PUBGMCUtil.getEmptyGroundPositionAt(world, pos);
        if (ground == null) {
            Pubgmc.logger.warn(MARKER, "Unable to generate death crate at {} position, no valid position was found", pos);
            return;
        }
        Pubgmc.logger.debug(MARKER, "Generating death crate at {}", ground);
        world.setBlockState(ground, PMCBlocks.PLAYER_CRATE.getDefaultState(), 3);
        TileEntity tileEntity = world.getTileEntity(ground);
        if (!(tileEntity instanceof TileEntityPlayerCrate)) {
            Pubgmc.logger.error(MARKER, "Unable to fill death crate contents at {} due to invalid tile entity type: {}", ground, tileEntity);
            return;
        }
        TileEntityPlayerCrate playerCrate = (TileEntityPlayerCrate) tileEntity;
        playerCrate.assignGameId(gameId);
        int inventorySize = playerCrate.getSizeInventory();
        for (InventoryProvider provider : inventories) {
            int index = provider.getSlotOffset();
            IInventory inventory = provider.getInventory();
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    int crateSlotIndex = index + i;
                    if (crateSlotIndex >= inventorySize) {
                        Pubgmc.logger.error(MARKER, "Attempted to insert item at slot index outside of crate inventory range. Index {}, max {}", crateSlotIndex, inventorySize);
                        break;
                    }
                    playerCrate.setInventorySlotContents(crateSlotIndex, stack.copy());
                }
            }
            inventory.clear();
        }
        Pubgmc.logger.debug(MARKER, "Death crate generated at {}", ground);
    }

    public static void updateLoadedGameObjects(World world, GenerationType.Context context) {
        updateLoadedGameObjects(world, context, false);
    }

    public static void updateLoadedGameObjects(World world, GenerationType.Context context, boolean forceUpdate) {
        List<GameObject> loadedGameObjects = mergeTileEntitiesAndEntitiesByRule(world, t -> t instanceof GameObject, t -> (GameObject) t)
                .collect(Collectors.toList());
        updateGameObjects(loadedGameObjects, world, context, forceUpdate);
    }

    public static void updateGameObjects(Collection<GameObject> collection, World world, GenerationType.Context context, boolean forceUpdate) {
        UUID currentGameId = getGameUUID(world);
        collection.forEach(object -> {
            UUID objectId = object.getCurrentGameId();
            if (!objectId.equals(currentGameId) || forceUpdate) {
                object.onNewGameDetected(currentGameId);
                if (!context.isEmpty() && object instanceof Generator) {
                    ((Generator) object).generate(context);
                }
            }
        });
        if (!world.isRemote && ConfigPMC.common.gameConfig.allowFullChunkScans.get()) {
            ChunkProviderServer serverChunkProvider = (ChunkProviderServer) world.getChunkProvider();
            for (Chunk chunk : serverChunkProvider.getLoadedChunks()) {
                WorldGameBlockHelper.processChunk(chunk);
            }
        }
    }

    public static <T> Stream<T> mergeTileEntitiesAndEntitiesByRule(World world, Predicate<Object> filter, Function<Object, T> mapper) {
        Stream<T> entityObjects = getLoadedEntityGameObjects(world, filter, mapper);
        Stream<T> blockObjects = getLoadedTileGameObjects(world, filter, mapper);
        return Stream.concat(entityObjects, blockObjects);
    }

    public static <T> Stream<T> getLoadedTileGameObjects(World world, Predicate<Object> filter, Function<Object, T> mapper) {
        return world.loadedTileEntityList.stream()
                .filter(filter)
                .map(mapper);
    }

    public static <T> Stream<T> getLoadedEntityGameObjects(World world, Predicate<Object> filter, Function<Object, T> mapper) {
        return world.loadedEntityList.stream()
                .filter(filter)
                .map(mapper);
    }

    public static <E extends Entity & GameObject> boolean validateGameEntityStillValid(E entity) {
        if (entity.world.isRemote) {
            return true;
        }
        UUID currentGameId = getGameUUID(entity.world);
        if (currentGameId.equals(entity.getCurrentGameId())) {
            return true;
        }
        entity.onNewGameDetected(currentGameId);
        return false;
    }

    public static UUID getGameUUID(World world) {
        return GameDataProvider.getGameData(world)
                .map(data -> data.getCurrentGame().getGameId())
                .orElse(DEFAULT_UUID);
    }

    public static void requestClientGameDataSynchronization(World world) {
        if (world.isRemote)
            return;
        GameDataProvider.getGameData(world)
                .ifPresent(GameData::sendGameDataToClients);
    }

    public static void fillPlayerHunger(EntityPlayer player) {
        player.getFoodStats().addStats(20, 0.5F);
    }

    public static void resetPlayerData(EntityPlayer player) {
        player.inventory.clear();
        Collection<PotionEffect> effects = new ArrayList<>(player.getActivePotionEffects());
        for (PotionEffect effect : effects) {
            player.removeActivePotionEffect(effect.getPotion());
        }
        fillPlayerHunger(player);
        player.setHealth(player.getMaxHealth());
        IPlayerData playerData = PlayerDataProvider.get(player);
        if (playerData != null) {
            playerData.setNightVisionActive(false);
            playerData.setProne(false, true);
            playerData.getBoostStats().reset();
            playerData.getEquipmentInventory().clear();
            playerData.getDebuffs().clearBlindStatus();
            playerData.getDebuffs().clearDeafStatus();
            playerData.sync();
        }
    }

    public static void clearEmptyTeams(WorldServer world, TeamManager manager) {
        Iterator<Team> iterator = manager.getTeams().iterator();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            if (team.isTeamEliminated()) {
                iterator.remove();
                continue;
            }
            boolean hasMember = false;
            for (Team.Member member : team.getAllMembers().values()) {
                if (member.getEntity(world) != null) {
                    hasMember = true;
                    break;
                }
            }
            if (!hasMember) {
                iterator.remove();
            }
        }
    }

    public static void moveToLobby(EntityPlayer player) {
        World world = player.world;
        GameDataProvider.getGameData(world).map(GameData::getGameLobby)
                .ifPresent(lobby -> {
                    if (!lobby.isWithin(player))
                        lobby.teleport(player);
                });
    }

    public static void stopGame(World world) {
        GameDataProvider.getGameData(world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            executeGameEventSafely(world, () -> {
                game.onGameStopped(world, data);
                MinecraftForge.EVENT_BUS.post(new GameEvent.Stopped(game));
                data.setActiveGame(null);
                data.setActiveGameMapName(null, null);
                data.sendGameDataToClients();
            });
        });
    }

    public static void executeGameEventSafely(World world, HandledGameEvent action) {
        executeGameEventSafely(world, action, GameExceptionConsumer.RETHROW);
    }

    public static void executeGameEventSafely(World world, HandledGameEvent action, GameExceptionConsumer customErrorHandler) {
        try {
            action.run();
        } catch (GameException e) {
            Pubgmc.logger.error("Game error occurred", e);
            customErrorHandler.handle(e);
        } catch (Exception e) {
            Pubgmc.logger.fatal("Critical exception occurred in game, aborting", e);
            resetErroredGameData(world);
        }
    }

    public static void resetErroredGameData(World world) {
        GameDataProvider.getGameData(world).ifPresent(data -> {
            data.setActiveGame(NoGame.INSTANCE);
            data.setActiveGameMapName(null, null);
            data.sendGameDataToClients();
            world.playerEntities.forEach(player -> {
                GameHelper.moveToLobby(player);
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Critical error in current game, cancelling"));
            });
        });
    }

    // Doesn't actually spawn the plane
    public static EntityPlane initializePlaneWithPath(UUID gameId, World world, Playzone playzone, int approximateFlightTime) {
        Position2 min = playzone.getPositionMin(1.0F);
        Position2 max = playzone.getPositionMax(1.0F);
        double xDiff = max.getX() - min.getX();
        double zDiff = max.getZ() - min.getZ();
        Position2 start;
        Position2 end;
        Random random = world.rand;
        double mult0 = random.nextDouble();
        double mult1 = 1.0 - mult0;
        switch (random.nextInt(4)) {
            default:
            case 0:
                start = new Position2(min.getX() + mult0 * xDiff, min.getZ());
                end = new Position2(min.getX() + mult1 * xDiff, max.getZ());
                break;
            case 1:
                start = new Position2(min.getX() + mult0 * xDiff, max.getZ());
                end = new Position2(min.getX() + mult1 * xDiff, min.getZ());
                break;
            case 2:
                start = new Position2(min.getX(), min.getZ() + mult0 * zDiff);
                end = new Position2(max.getX(), min.getZ() + mult1 * zDiff);
                break;
            case 3:
                start = new Position2(max.getX(), min.getZ() + mult0 * zDiff);
                end = new Position2(min.getX(), min.getZ() + mult1 * zDiff);
                break;
        }
        double flightPathLength = start.length(end);
        float movementSpeed = (float) (flightPathLength / approximateFlightTime);
        EntityPlane plane = new EntityPlane(world);
        plane.assignGameId(gameId);
        plane.setPath(start, end);
        plane.setMovementSpeed(movementSpeed);
        return plane;
    }

    public static void spawnPlanesWithPlayers(TeamManager teamManager, World world, Consumer<EntityPlayer> playerConsumer, Supplier<EntityPlane> planeProvider) {
        List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
        int pool = playerList.size();
        int cursor = 0;
        while (pool > 0) {
            List<EntityPlayer> sublist = playerList.subList(cursor, Math.min(playerList.size(), cursor + EntityPlane.PLANE_CAPACITY));

            EntityPlane plane = planeProvider.get();
            sublist.forEach(player -> teleport(player, plane.posX, plane.posY, plane.posZ));
            if (!world.spawnEntity(plane)) {
                Pubgmc.logger.fatal("Plane spawning failed for plane {}", plane);
            } else {
                sublist.forEach(player -> {
                    player.startRiding(plane);
                    playerConsumer.accept(player);
                });
            }

            pool -= sublist.size();
            cursor += EntityPlane.PLANE_CAPACITY;
        }
    }

    public static void teleport(Entity entity, double x, double y, double z) {
        if (entity.world.isRemote || !entity.isEntityAlive())
            return;
        entity.dismountRidingEntity();
        if (entity instanceof EntityPlayerMP) {
            entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
            NetHandlerPlayServer serverConnection = ((EntityPlayerMP) entity).connection;
            if (serverConnection == null) {
                Pubgmc.logger.warn("Unable to move player {} to position [{},{},{}] as their server side connection is not established!", entity.getName(), x, y, z);
            } else {
                serverConnection.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
            }
        } else {
            entity.setPositionAndUpdate(x, y, z);
        }
    }

    @Nullable
    public static Position2 findLoadedPositionWithinPlayzone(Playzone playzone, World world, List<EntityPlayer> playerList, int minAllowedPlayerDistance, int maxDistanceOffset) {
        return findLoadedPositionWithinPlayzone(playzone, world, playerList, minAllowedPlayerDistance, maxDistanceOffset, false);
    }

    @Nullable
    public static Position2 findLoadedPositionWithinPlayzone(Playzone playzone, World world, List<EntityPlayer> playerList, int minAllowedPlayerDistance, int maxDistanceOffset, boolean ignorePlayzone) {
        Random random = world.rand;
        if (playerList.isEmpty()) {
            return null;
        }
        int attempts = 0;
        BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos();
        while (attempts < 100) {
            ++attempts;
            EntityPlayer player = PUBGMCUtil.randomListElement(playerList, random);
            boolean xn = random.nextBoolean();
            boolean zn = random.nextBoolean();
            int minX = xn ? -minAllowedPlayerDistance : minAllowedPlayerDistance;
            int minZ = zn ? -minAllowedPlayerDistance : minAllowedPlayerDistance;
            int x = xn ? -random.nextInt(maxDistanceOffset) : random.nextInt(maxDistanceOffset);
            int z = zn ? -random.nextInt(maxDistanceOffset) : random.nextInt(maxDistanceOffset);
            double xPosition = player.posX + minX + x;
            double zPosition = player.posZ + minZ + z;
            if (!ignorePlayzone && !playzone.isWithin(xPosition, zPosition)) {
                continue;
            }
            position.setPos(xPosition, player.posY, zPosition);
            EntityPlayer nearest = world.getNearestAttackablePlayer(position, maxDistanceOffset * maxDistanceOffset, 128);
            if (nearest == null) {
                return new Position2(xPosition, zPosition);
            }
            double xDiff = nearest.posX - xPosition;
            double zDiff = nearest.posZ - zPosition;
            if ((xDiff * xDiff + zDiff * zDiff) <= maxDistanceOffset * maxDistanceOffset) {
                return new Position2(xPosition, zPosition);
            }
        }
        return null;
    }

    public static TeamRelations getEntityRelations(EntityLivingBase entity1, @Nullable EntityLivingBase entity2) {
        World world = entity1.world;
        GameData gameData = GameDataProvider.getGameData(world).orElse(null);
        if (gameData == null) {
            return TeamRelations.UNKNOWN;
        }
        Game<?> game = gameData.getCurrentGame();
        if (!(game instanceof TeamGame<?>)) {
            return TeamRelations.UNKNOWN;
        }
        TeamManager manager = ((TeamGame<?>) game).getTeamManager();
        Team team1 = manager.getEntityTeam(entity1);
        Team team2 = manager.getEntityTeam(entity2);
        try {
            return manager.getTeamRelationship(team1, team2);
        } catch (Exception e) {
            Pubgmc.logger.fatal("Fatal error while attempting to resolve entity team relations in game", e);
            return TeamRelations.UNKNOWN;
        }
    }

    public static Optional<Team> getEntityTeam(Entity entity) {
        World world = entity.world;
        return GameDataProvider.getGameData(world).map(data -> {
            Game<?> game = data.getCurrentGame();
            if (!(game instanceof TeamGame<?>)) {
                return null;
            }
            TeamManager manager = ((TeamGame<?>) game).getTeamManager();
            return manager.getEntityTeam(entity);
        });
    }

    public static boolean hasRestrictedInventory(World world) {
        if (ConfigPMC.common.players.forceInventoryRestrictions.get()) {
            return true;
        }
        return GameDataProvider.getGameData(world).map(data -> {
            Game<?> game = data.getCurrentGame();
            return game.isStarted() && GameMutatorManager.INSTANCE.hasMutator(game.getGameType(), GameMutators.INVENTORY_LIMIT);
        }).orElse(false);
    }

    @Nullable
    public static GameMapInstance getActiveGameMap(World world) {
        return GameDataProvider.getGameData(world).map(data -> {
            String mapName = data.getActiveGameMapName();
            return data.getGameMap(mapName);
        }).orElse(null);
    }

    @Nullable
    public static GameMap getActiveGameMapOrSubMap(World world) {
        return GameDataProvider.getGameData(world).flatMap(GameData::getActiveGameMap).orElse(null);
    }

    public static void reloadChunks(EntityPlayer player) {
        if (!player.world.isRemote) {
            PacketHandler.sendToClient(new S2C_PacketReloadChunks(), (EntityPlayerMP) player);
        }
    }

    public static ITextComponent getEntityDisplayName(Entity entity) {
        if (entity instanceof CustomEntityNametag) {
            return ((CustomEntityNametag) entity).getComponent();
        }
        return entity.getDisplayName();
    }

    public interface InventoryProvider {

        int getSlotOffset();

        IInventory getInventory();

        static InventoryProvider inventory(int indexStart, IInventory inventory) {
            return new InventoryProvider() {
                @Override
                public int getSlotOffset() {
                    return indexStart;
                }

                @Override
                public IInventory getInventory() {
                    return inventory;
                }
            };
        }

        static InventoryProvider wrappedEquipment(int index, EntityLiving living) {
            return new InventoryProvider() {
                @Override
                public int getSlotOffset() {
                    return index;
                }

                @Override
                public IInventory getInventory() {
                    return PUBGMCUtil.asInventory(living);
                }
            };
        }
    }

    @FunctionalInterface
    public interface HandledGameEvent {
        void run() throws GameException;
    }

    @FunctionalInterface
    public interface GameExceptionConsumer {

        GameExceptionConsumer RETHROW = e -> {
            throw new IllegalStateException("Game error", e);
        };

        void handle(GameException e);
    }
}
