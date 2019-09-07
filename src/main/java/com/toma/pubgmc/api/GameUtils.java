package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GameUtils {

    public static void updateLoadedTileEntities(World world, Game game, boolean forcedUpdate) {
        IGameData gameData = game.gameData;
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
                    return;
                }
                if(te.getGameHash().equals(gameData.getGameID())) {
                    return;
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
}
