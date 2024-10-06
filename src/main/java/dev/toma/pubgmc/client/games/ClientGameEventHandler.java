package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.game.team.Team;
import dev.toma.pubgmc.client.event.ClientWorldTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Pubgmc.MOD_ID)
public final class ClientGameEventHandler {

    private static int deadTimer;

    @SubscribeEvent
    public static void onClientWorldTick(ClientWorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            World world = event.getWorld();
            GameDataProvider.getGameData(world)
                    .ifPresent(GameData::tick);
        }
    }

    @SubscribeEvent
    public static void cancelNameRenderingInGame(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        EntityLivingBase entity = event.getEntity();
        if (!canRenderEntityName(entity)) {
            event.setCanceled(true);
            return;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        WorldClient worldClient = minecraft.world;
        GameDataProvider.getGameData(worldClient).ifPresent(data -> {
            Game<?> game = data.getCurrentGame();
            if (game.isStarted() && !game.getConfiguration().shouldShowNameplates()) {
                event.setCanceled(true);

                if (!game.isStarted() || !(game instanceof TeamGame<?>)) {
                    return;
                }
                TeamGame<?> teamGame = (TeamGame<?>) game;
                Entity clientEntity = minecraft.getRenderViewEntity();
                EntityLivingBase renderingEntity = event.getEntity();
                if (clientEntity == null || clientEntity == renderingEntity) {
                    return;
                }
                TeamGameConfiguration teamGameConfiguration = teamGame.getConfiguration();
                if (teamGameConfiguration.shouldShowTeamNameplates()) {
                    TeamManager teamManager = teamGame.getTeamManager();
                    Team myTeam = teamManager.getEntityTeam(clientEntity);
                    Team renderingTeam = teamManager.getEntityTeam(renderingEntity);
                    if (teamManager.getTeamRelationship(myTeam, renderingTeam) == TeamRelations.FRIENDLY) {
                        renderEntityName(minecraft.getRenderManager(), renderingEntity, event.getX(), event.getY(), event.getZ());
                    }
                }
            }
        });
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
            return;
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        if (player != null && player.isDead) {
            GameMutatorHelper.getMutator(player.world, GameMutators.FORCE_RESPAWN).ifPresent(mutator -> {
                int limit = mutator.getRespawnAfterTime();
                if (++deadTimer >= limit) {
                    player.respawnPlayer();
                    minecraft.displayGuiScreen(null);
                }
            });
        } else {
            deadTimer = 0;
        }
    }

    private static void renderEntityName(RenderManager renderManager, EntityLivingBase entity, double x, double y, double z) {
        String text;
        if (entity instanceof CustomEntityNametag) {
            text = ((CustomEntityNametag) entity).getComponent().getFormattedText();
        } else {
            text = entity.getDisplayName().getFormattedText();
        }
        GlStateManager.alphaFunc(516, 0.1F);
        boolean flag = entity.isSneaking();
        float f = renderManager.playerViewY;
        float f1 = renderManager.playerViewX;
        boolean flag1 = renderManager.options.thirdPersonView == 2;
        float f2 = entity.height + 0.5F - (flag ? 0.25F : 0.0F);
        EntityRenderer.drawNameplate(Minecraft.getMinecraft().fontRenderer, text, (float) x, (float) (y + f2), (float) z, 0, f, f1, flag1, flag);
    }

    private static boolean canRenderEntityName(EntityLivingBase livingBase) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
        boolean visible = !livingBase.isInvisibleToPlayer(entityplayersp);
        boolean playerCondition = Minecraft.isGuiEnabled() && livingBase != minecraft.getRenderManager().renderViewEntity && visible && !livingBase.isBeingRidden();
        if (livingBase instanceof EntityLiving && !(livingBase instanceof LivingGameEntity)) {
            return playerCondition && (livingBase.getAlwaysRenderNameTagForRender() || livingBase.hasCustomName() && livingBase == minecraft.getRenderManager().pointedEntity);
        }
        return playerCondition;
    }
}
