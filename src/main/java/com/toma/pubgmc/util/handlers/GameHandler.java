package com.toma.pubgmc.util.handlers;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameObjectiveBased;
import com.toma.pubgmc.api.interfaces.IGameTileEntity;
import com.toma.pubgmc.api.objectives.types.GameArea;
import com.toma.pubgmc.api.settings.EntityDeathManager;
import com.toma.pubgmc.api.settings.GameBotManager;
import com.toma.pubgmc.api.settings.TeamManager;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.api.util.EntityDeathContex;
import com.toma.pubgmc.api.util.GameUtils;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.math.ZonePos;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class GameHandler {

    @Mod.EventBusSubscriber(Side.CLIENT)
    public static class Client {

        @SubscribeEvent
        public static void onRenderOverlay(RenderGameOverlayEvent.Post e) {
            if(e.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                IGameData gameData = Minecraft.getMinecraft().world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
                if(gameData.getCurrentGame().isRunning() && !gameData.isInactiveGame()) {
                    gameData.getCurrentGame().renderGameOverlay(Minecraft.getMinecraft(), e.getResolution());
                }
            }
        }

        @SubscribeEvent
        public static void renderGameObjects(RenderWorldLastEvent e) {
            World world = Minecraft.getMinecraft().world;
            Game g = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getCurrentGame();
            if(g instanceof GameObjectiveBased) {
                GameObjectiveBased game = (GameObjectiveBased) g;
                Collection<GameArea> areas = game.getObjectives().values();
                for(GameArea area : areas) {
                    if(area.isLoaded(world)) {
                        area.renderGameArea(e.getPartialTicks());
                    }
                }
            }
        }
    }

    @Mod.EventBusSubscriber
    public static class Common {

        @SubscribeEvent
        public static void onTick(TickEvent.WorldTickEvent e) {
            if(e.phase == TickEvent.Phase.END) {
                return;
            }
            IGameData gameData = e.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            if(!gameData.getCurrentGame().isRunning()) {
                return;
            }
            Game game = gameData.getCurrentGame();
            if(gameData.isInactiveGame()) {
                return;
            }
            game.tickGame(e.world);
            if(game.gameTimer % 200 == 0) {
                GameBotManager botManager = game.getBotManager();
                if(botManager.areBotsEnabled() && botManager.currentBotCount < botManager.maxBotAmount() && botManager.getBotSpawnVerification().test(game)) {
                    BlockPos pos = botManager.getBotSpawner().getSpawnPosition(e.world, game);
                    if(pos == null) return;
                    game.botsInGame++;
                    EntityAIPlayer aiPlayer = new EntityAIPlayer(e.world, pos);
                    e.world.spawnEntity(aiPlayer);
                    botManager.getLootFactory().accept(aiPlayer);
                }
            }
        }

        @SubscribeEvent
        public static void onChunkLoaded(ChunkEvent.Load e) {
            IGameData gameData = e.getWorld().getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            Game game = gameData.getCurrentGame();
            if(gameData == null || !gameData.isInactiveGame() || !game.shouldUpdateTileEntities()) {
                return;
            }
            Map<BlockPos, TileEntity> map = e.getChunk().getTileEntityMap();
            for(TileEntity tileEntity : map.values()) {
                if(tileEntity instanceof IGameTileEntity) {
                    IGameTileEntity te = (IGameTileEntity)tileEntity;
                    if(!te.getGameHash().equals(gameData.getGameID())) {
                        te.setGameHash(gameData.getGameID());
                        try {
                            te.onLoaded();
                        } catch (Exception ex) {
                            Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                        }
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerKilled(LivingDeathEvent e) {
            IGameData data = e.getEntity().world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            Game game = data.getCurrentGame();
            if(e.getEntity() instanceof EntityLivingBase) {
                EntityDeathContex ctx = EntityDeathContex.getDeathContex(e, game);
                EntityDeathManager manager = game.getEntityDeathManager();
                manager.getDeathAction().accept(ctx);
                if(!e.getEntity().world.isRemote) {
                    game.addDeathMessage(ctx);
                    TeamManager teamManager = game.getTeamManager();
                    if(teamManager.getTeamSettings().eliminateOnDeath) {
                        if(e.getEntity() instanceof EntityPlayer || e.getEntity() instanceof EntityAIPlayer) {
                            eliminatePlayerAndTeam(game, e.getEntity().getUniqueID());
                        }
                    }
                    if(game.shouldCreateDeathCrate()) {
                        if(e.getEntity() instanceof EntityPlayer) {
                            GameUtils.createDeathCrate((EntityPlayer) e.getEntity());
                        } else if(e.getEntity() instanceof EntityAIPlayer && game.getBotManager().allowBotCrates()) {
                            GameUtils.createDeathCrate((EntityAIPlayer) e.getEntity());
                            game.onBotDeath((EntityAIPlayer) e.getEntity());
                        }
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent e) {
            IGameData gameData = e.player.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            Game game = gameData.getCurrentGame();
            boolean canRespawnIntoGame = game.respawnPlayer(e.player);
            if(canRespawnIntoGame) {
                game.getJoinedPlayers().add(e.player.getUniqueID());
                game.updateDataToClients(e.player.world);
                ZonePos startPos = game.zone.currentBounds.min();
                int max = (int)Math.abs(startPos.x - game.zone.currentBounds.max().x);
                int x = (int)startPos.x + Pubgmc.rng().nextInt(max);
                int z = (int)startPos.z + Pubgmc.rng().nextInt(max);
                int y = e.player.world.getHeight(x, z);
                e.player.setPositionAndUpdate(x, y, z);
            }
        }

        public static void createAndFillDeathCrate(World world, BlockPos pos, EntityPlayer player) {
            if(pos == null) {
                Pubgmc.logger.warn("Couldn't create death crate for {}", player.getDisplayName());
                return;
            }
            world.setBlockState(pos, PMCRegistry.PMCBlocks.PLAYER_CRATE.getDefaultState());
            TileEntityPlayerCrate te = (TileEntityPlayerCrate)world.getTileEntity(pos);
            if(te == null) {
                Pubgmc.logger.fatal("Exception occurred when creating player crate, tile entity is null!");
                return;
            }
            for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);
                te.setInventorySlotContents(i, stack.copy());
            }
            IPlayerData data = IPlayerData.PlayerData.get(player);
            int backpack = data.getBackpackLevel();
            if(backpack > 0) {
                te.setInventorySlotContents(41, new ItemStack(backpack == 1 ? PMCRegistry.PMCItems.BACKPACK1 : backpack == 2 ? PMCRegistry.PMCItems.BACKPACK2 : PMCRegistry.PMCItems.BACKPACK3));
            }
            if(data.getEquippedNV()) {
                te.setInventorySlotContents(42, new ItemStack(PMCRegistry.PMCItems.NV_GOGGLES));
            }
            player.inventory.clear();
        }

        public static boolean isEmpty(InventoryPlayer inv) {
            for(int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if(!stack.isEmpty() && stack.getItem() != PMCRegistry.PMCItems.IBLOCK) {
                    return false;
                }
            }
            return true;
        }

        private static void eliminatePlayerAndTeam(Game game, UUID uuid) {
                Team team = null;
            // TODO remove
            try {
                for(Team t : game.getTeamList()) {
                    if(PUBGMCUtil.contains(uuid, t.players)) {
                        team = t;
                        break;
                    }
                }
                if(team != null) {
                    team.remove(uuid);
                    if(team.isEmpty()) {
                        game.getTeamList().remove(team);
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
