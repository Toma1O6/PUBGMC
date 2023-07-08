package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.init.DamageSourceGun;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
        List<GameObject> loadedGameObjects = mergeTileEntitiesAndEntitiesByRule(world, t -> t instanceof GameObject, t -> (GameObject) t)
                .collect(Collectors.toList());
        updateGameObjects(loadedGameObjects, world, context);
    }

    public static void updateGameObjects(Collection<GameObject> collection, World world, GenerationType.Context context) {
        UUID currentGameId = getGameUUID(world);
        collection.forEach(object -> {
            UUID objectId = object.getCurrentGameId();
            if (!objectId.equals(currentGameId)) {
                object.onNewGameDetected(currentGameId);
                if (!context.isEmpty() && object instanceof Generator) {
                    ((Generator) object).generate(context);
                }
            }
        });
    }

    public static <T> Stream<T> mergeTileEntitiesAndEntitiesByRule(World world, Predicate<Object> filter, Function<Object, T> mapper) {
        Stream<T> entityObjects = world.loadedEntityList.stream()
                .filter(filter)
                .map(mapper);
        Stream<T> blockObjects = world.loadedTileEntityList.stream()
                .filter(filter)
                .map(mapper);
        return Stream.concat(entityObjects, blockObjects);
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
        Collection<PotionEffect> effects = player.getActivePotionEffects();
        for (PotionEffect effect : effects) {
            player.removeActivePotionEffect(effect.getPotion());
        }
        fillPlayerHunger(player);
        IPlayerData playerData = PlayerDataProvider.get(player);
        if (playerData != null) {
            playerData.setNightVisionActive(false);
            playerData.setProne(false);
            playerData.getBoostStats().reset();
            playerData.getEquipmentInventory().clear();
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
                .ifPresent(lobby -> lobby.teleport(player));
    }

    public static void stopGame(World world) {
        GameDataProvider.getGameData(world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            game.onGameStopped(world, data);
            data.setActiveGame(null);
            data.sendGameDataToClients();
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

    public static void spawnPlaneWithPlayers(EntityPlane plane, TeamManager teamManager, World world, Consumer<EntityPlayer> playerConsumer) {
        List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
        playerList.forEach(player -> teleport(player, plane.posX, plane.posY, plane.posZ));
        world.spawnEntity(plane);
        playerList.forEach(player -> {
            player.startRiding(plane);
            playerConsumer.accept(player);
        });
    }

    public static void teleport(Entity entity, double x, double y, double z) {
        entity.dismountRidingEntity();
        entity.setPositionAndUpdate(x, y, z);
    }

    public static DeathMessage createDefaultDeathMessage(EntityLivingBase victim, DamageSource source) {
        Entity sourceEntity = source.getTrueSource();
        boolean headshot = false;
        if (sourceEntity instanceof EntityLivingBase) {
            EntityLivingBase killer = (EntityLivingBase) sourceEntity;
            ItemStack killWeapon = killer.getHeldItemMainhand();
            if (source instanceof DamageSourceGun) {
                DamageSourceGun damageSourceGun = (DamageSourceGun) source;
                killWeapon = damageSourceGun.getWeapon();
                headshot = damageSourceGun.wasHeadshot();
            }
            ITextComponent label = killWeapon.isEmpty() ? TextComponentHelper.GENERIC_DEATH_BY_ENTITY : new TextComponentString(killWeapon.getDisplayName());
            if (source == PMCDamageSources.VEHICLE && killer.getRidingEntity() != null) {
                label = killer.getRidingEntity().getDisplayName();
            }
            return new DeathMessage(killer, victim, label, headshot);
        }
        if (source == PMCDamageSources.ZONE) {
            return new DeathMessage(null, victim, TextComponentHelper.GENERIC_ZONE);
        }
        return new DeathMessage(null, victim, TextComponentHelper.GENERIC_DEATH);
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

    public static TeamRelations getEntityRelations(EntityLivingBase entity1, EntityLivingBase entity2) {
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
        return manager.getTeamRelationship(team1, team2);
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
}
