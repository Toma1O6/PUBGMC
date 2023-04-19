package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.entity.EntityGameItem;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
public final class CommonGameEventHandler {

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GameDataProvider.getGameData(event.world)
                    .ifPresent(GameData::tick);
        }
    }

    // TODO other events

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            game.onPlayerLoggedIn((EntityPlayerMP) event.player);
        });
    }

    @SubscribeEvent
    public static void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            game.onPlayerLoggedOut((EntityPlayerMP) event.player);
        });
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!event.isCanceled()) {
            GameDataProvider.getGameData(entity.world).ifPresent(data -> {
                Game<?> game = data.getCurrentGame();
                if (game.isStarted()) {
                    game.onEntityDeath(entity, event.getSource());
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        GameDataProvider.getGameData(event.player.world).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted()) {
                game.onPlayerRespawn(event.player);
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
            world.spawnEntity(gameItem);
        }
    }
}
