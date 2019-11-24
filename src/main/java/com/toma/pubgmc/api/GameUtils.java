package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.interfaces.BotSpawner;
import com.toma.pubgmc.api.interfaces.IGameTileEntity;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.common.entity.bot.ai.EntityAIGunAttack;
import com.toma.pubgmc.common.entity.bot.ai.EntityAIMoveIntoZone;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.math.ZonePos;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public final class GameUtils {

    public static final DecimalFormat FORMATTER = new DecimalFormat("##00");

    public static void updateLoadedTileEntities(World world, Game game, boolean forcedUpdate) {
        IGameData gameData = game.getGameData(world);
        if (!game.shouldUpdateTileEntities()) {
            return;
        }
        for (TileEntity tileEntity : world.loadedTileEntityList) {
            if (tileEntity instanceof IGameTileEntity) {
                IGameTileEntity te = (IGameTileEntity) tileEntity;
                if (forcedUpdate) {
                    te.setGameHash(gameData.getGameID());
                    try {
                        te.onLoaded();
                    } catch (Exception e) {
                        Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                    }
                    continue;
                }
                if (te.getGameHash().equals(gameData.getGameID())) {
                    continue;
                }
                te.setGameHash(gameData.getGameID());
                try {
                    te.onLoaded();
                } catch (Exception e) {
                    Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                }
            }
        }
    }

    public static void createAndFillPlanes(World world) {
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        Game game = gameData.getCurrentGame();
        EntityPlane plane = new EntityPlane(world, gameData);
        int joined = 0;
        Iterator<EntityPlayer> iterator = game.getOnlinePlayers(world).iterator();
        while (iterator.hasNext()) {
            EntityPlayer player = iterator.next();
            player.inventory.clear();
            player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.PARACHUTE));
            boolean flag = iterator.hasNext();
            ++joined;
            plane.pendingPlayers.add(player);
            if (!flag) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                if (!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for (EntityPlayer p : plane.pendingPlayers) {
                    if (p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
            } else if (joined >= 31) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                if (!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for (EntityPlayer p : plane.pendingPlayers) {
                    if (p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
                if (flag) {
                    plane = new EntityPlane(world, gameData);
                    joined = 0;
                }
            }
        }
    }

    public static void markBlockForRemoval(World world, BlockPos pos, Block block) {
        world.scheduleUpdate(pos, block, 3);
    }

    public static void teleportPlayersIntoLobby(World world, Game game) {
        Lobby lobby = game.getGameData(world).getLobby();
        int x = lobby.center.getX();
        int y = lobby.center.getY() + 1;
        int z = lobby.center.getZ();
        game.getOnlinePlayers(world).forEach(player -> player.setPositionAndUpdate(x, y, z));
    }

    public static String getFormattedTime(int amount, final boolean isInTicks) {
        if (isInTicks) {
            amount = amount / 20;
        }
        int left = amount;
        int hours = left / 3600;
        left = left % 3600;
        int minutes = left / 60;
        int seconds = left % 60;
        StringBuilder builder = new StringBuilder(8);
        if (hours > 0) builder.append(FORMATTER.format(hours)).append(":");
        builder.append(FORMATTER.format(minutes)).append(":");
        builder.append(FORMATTER.format(seconds));
        return builder.toString();
    }

    public static BotSpawner getDefaultSpawner() {
        return (world, game) -> {
            BlueZone zone = game.zone;
            if (zone == null) {
                return null;
            }
            ZonePos min = zone.currentBounds.min();
            ZonePos max = zone.currentBounds.max();
            int maxDist = (int) Math.abs(max.x - min.x);
            int x = (int) min.x + world.rand.nextInt(maxDist);
            int z = (int) min.z + world.rand.nextInt(maxDist);
            int y = world.getHeight(x, z);
            BlockPos pos = new BlockPos(x, y, z);
            if (!world.isBlockLoaded(pos)) {
                List<EntityPlayer> playerList = game.getOnlinePlayers(world);
                EntityPlayer player = playerList.get(world.rand.nextInt(playerList.size()));
                x = (int) player.posX + world.rand.nextInt(64) - world.rand.nextInt(64);
                z = (int) player.posZ + world.rand.nextInt(64) - world.rand.nextInt(64);
                y = world.getHeight(x, z);
                pos = new BlockPos(x, y, z);
                if (!world.isBlockLoaded(pos)) {
                    pos = null;
                }
            }
            return pos;
        };
    }

    public static void addBaseTasks(EntityAITasks n, EntityAITasks t, EntityAIPlayer b) {
        n.addTask(0, new EntityAISwimming(b));
        n.addTask(2, new EntityAIGunAttack(b));
        n.addTask(4, new EntityAIMoveIntoZone(b));
        n.addTask(6, new EntityAIWanderAvoidWater(b, 1.0D, 0.0001F));
        n.addTask(8, new EntityAIWatchClosest(b, EntityPlayer.class, 8.0F));
        n.addTask(8, new EntityAILookIdle(b));
        t.addTask(2, new EntityAINearestAttackableTarget(b, EntityPlayer.class, 10, true, false, EntitySelectors.IS_ALIVE));
        t.addTask(2, new EntityAINearestAttackableTarget(b, EntityAIPlayer.class, true));
    }
}
