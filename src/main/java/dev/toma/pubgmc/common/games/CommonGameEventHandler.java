package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.api.event.SpawnPositionSetEvent;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.common.entity.EntityGameItem;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
public final class CommonGameEventHandler {

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GameDataProvider.getGameData(event.world)
                    .ifPresent(GameData::tick);
        }
    }

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            game.invokeEvent(listener -> listener.onPlayerLoggedIn(event));
        });
    }

    @SubscribeEvent
    public static void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            game.invokeEvent(listener -> listener.onPlayerLoggedOut(event));
        });
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!event.isCanceled()) {
            GameDataProvider.getGameData(entity.world).ifPresent(data -> {
                Game<?> game = data.getCurrentGame();
                if (game.isStarted()) {
                    game.invokeEvent(listener -> listener.onEntityDeath(event));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!event.isCanceled()) {
            GameDataProvider.getGameData(entity.world).ifPresent(data -> {
                Game<?> game = data.getCurrentGame();
                if (game.isStarted()) {
                    game.invokeEvent(listener -> listener.onEntityAttack(event));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!event.isCanceled()) {
            GameDataProvider.getGameData(entity.world).ifPresent(data -> {
                GameLobby lobby = data.getGameLobby();
                if (lobby != null && lobby.isWithin(entity)) {
                    event.setCanceled(true);
                    return;
                }
                Game<?> game = data.getCurrentGame();
                if (game.isStarted()) {
                    game.invokeEvent(listener -> listener.onEntityHurt(event));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted()) {
                game.invokeEvent(listener -> listener.onPlayerRespawn(event));
            }
        });
    }

    @SubscribeEvent
    public static void replaceDroppedItems(EntityJoinWorldEvent event) {
        World world = event.getWorld();
        Entity entity = event.getEntity();
        if (entity.getClass().equals(EntityItem.class)) {
            EntityItem entityItem = (EntityItem) entity;
            event.setCanceled(true);
            EntityGameItem gameItem = new EntityGameItem(entityItem.world, entityItem.posX, entityItem.posY, entityItem.posZ, entityItem.getItem());
            gameItem.motionX = entityItem.motionX;
            gameItem.motionY = entityItem.motionY;
            gameItem.motionZ = entityItem.motionZ;
            gameItem.setThrower(entityItem.getThrower());
            gameItem.assignGameId(GameHelper.getGameUUID(world));
            gameItem.setPickupDelay(60);
            world.spawnEntity(gameItem);
        }
        if (entity instanceof LivingGameEntity) {
            GameDataProvider.getGameData(world).ifPresent(data -> {
                Game<?> game = data.getCurrentGame();
                if (game.isStarted()) {
                    game.invokeEvent(listener -> listener.onEntitySpawnInWorld(event));
                }
            });
        }
    }

    @SubscribeEvent
    public static void loadChunk(ChunkEvent.Load event) {
        Chunk chunk = event.getChunk();
        Map<BlockPos, TileEntity> tileEntityMap = new HashMap<>(chunk.getTileEntityMap());
        UUID currentGameId = GameHelper.getGameUUID(event.getWorld());
        for (Map.Entry<BlockPos, TileEntity> entry : tileEntityMap.entrySet()) {
            TileEntity entity = entry.getValue();
            if (entity instanceof GameObject) {
                GameObject gameObject = (GameObject) entity;
                if (!gameObject.getCurrentGameId().equals(currentGameId)) {
                    gameObject.onNewGameDetected(currentGameId);
                }
            }
        }
    }

    @SubscribeEvent
    public static void entityOpenParachute(ParachuteEvent.Open event) {
        World world = event.getParachuteEntity().getEntityWorld();
        GameDataProvider.getGameData(world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted()) {
                game.invokeEvent(listener -> listener.onEntityOpenParachute(event));
            }
        });
    }

    @SubscribeEvent
    public static void entityLandWithParachute(ParachuteEvent.Land event) {
        if (event.isCanceled())
            return;
        World world = event.getParachuteEntity().getEntityWorld();
        GameDataProvider.getGameData(world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted()) {
                game.invokeEvent(listener -> listener.onEntityWithParachuteLanded(event));
            }
        });
    }

    @SubscribeEvent
    public static void adjustSpawnPosition(SpawnPositionSetEvent event) {
        World world = event.getWorld();
        GameDataProvider.getGameData(world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted()) {
                game.invokeEvent(listener -> listener.setSpawnPosition(event));
            }
        });
    }
}
