package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.client.event.ClientWorldTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Pubgmc.MOD_ID)
public final class ClientGameEventHandler {

    @SubscribeEvent
    public static void onClientWorldTick(ClientWorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            World world = event.getWorld();
            GameDataProvider.getGameData(world)
                    .ifPresent(GameData::tick);
        }
    }

    @SubscribeEvent
    public static void onRenderNameplates(RenderLivingEvent.Specials.Pre<AbstractClientPlayer> event) {
        Minecraft client = Minecraft.getMinecraft();
        WorldClient worldClient = client.world;
        GameDataProvider.getGameData(worldClient).ifPresent(gameData -> {
            Entity entity = client.getRenderViewEntity();
            if (entity == null) {
                return;
            }
            EntityLivingBase target = event.getEntity();
            Game<?> game = gameData.getCurrentGame();
            if (!game.isStarted())
                return;
            GameConfiguration baseConfig = game.getConfiguration();
            if (game instanceof TeamGame<?>) {
                TeamGame<?> teamGame = (TeamGame<?>) game;
                TeamGameConfiguration teamConfig = (TeamGameConfiguration) baseConfig;
                TeamManager teamManager = teamGame.getTeamManager();
                Team myTeam = teamManager.getEntityTeam(entity);
                Team targetTeam = teamManager.getEntityTeam(target);
                // I am not in game, names can be rendered
                if (myTeam == null) {
                    return;
                }
                // Do not render nameplates of inactive players
                if (targetTeam == null) {
                    event.setCanceled(true);
                    return;
                }
                // Default override from base game config
                if (teamConfig.shouldShowNameplates()) {
                    return;
                }
                // Check if team relations and game config allow rendering of friendly nameplates
                TeamRelations relations = teamManager.getTeamRelationship(myTeam, targetTeam);
                if (relations.areNameplatesVisible() && teamConfig.shouldShowTeamNameplates()) {
                    return;
                }
                event.setCanceled(true);
            } else {
                if (baseConfig.shouldShowNameplates()) {
                    return;
                }
                event.setCanceled(true);
            }
        });
    }
}
