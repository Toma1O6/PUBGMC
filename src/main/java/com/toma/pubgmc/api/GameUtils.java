package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;

public final class GameUtils {

    public static void updateLoadedTileEntities(World world, Game game, boolean forcedUpdate) {
        IGameData gameData = game.getGameData(world);
        if(!game.shouldUpdateTileEntities()) {
            return;
        }
        for(TileEntity tileEntity : world.loadedTileEntityList) {
            if(tileEntity instanceof IGameTileEntity) {
                IGameTileEntity te = (IGameTileEntity)tileEntity;
                if(forcedUpdate) {
                    te.setGameHash(gameData.getGameID());
                    try {
                        te.onLoaded();
                    } catch (Exception e) {
                        Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                    }
                    continue;
                }
                if(te.getGameHash().equals(gameData.getGameID())) {
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
        while(iterator.hasNext()) {
            EntityPlayer player = iterator.next();
            player.inventory.clear();
            player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.PARACHUTE));
            boolean flag = iterator.hasNext();
            ++joined;
            plane.pendingPlayers.add(player);
            if(!flag) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                if(!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for(EntityPlayer p : plane.pendingPlayers) {
                    if(p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
            }
            else if(joined >= 31) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                if(!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for(EntityPlayer p : plane.pendingPlayers) {
                    if(p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
                if(flag) {
                    plane = new EntityPlane(world, gameData);
                    joined = 0;
                }
            }
        }
    }
}
