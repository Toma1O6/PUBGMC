package com.toma.pubgmc.api;

import javax.annotation.Nullable;

/**
 * Interface for executing game related tasks once tileentity gets loaded in
 * world. These functions are only executed when IGameData#isPlaying is true
 * and last tileentity game hash is not equal current game hash.
 * API is still unfinished and custom game creating is not possible (but these methods will be called)!
 *
 * Default implementation can be found at com.toma.pubgmc.common.tileentity.TileEntityLootSpawner
 *
 * Created by Toma
 */
public interface IGameTileEntity {

    /**
     * Used to update tileentity hash to current game hash
     * @param hash - the hash you set to your String field
     */
    void setGameHash(String hash);

    /**
     * @return the String field
     */
    String getGameHash();

    /**
     * Called when tilentity hash gets updated. Can be null,
     * but the tileentity will be ignored and hash won't update
     * @return action which will be executed
     */
    void onLoaded();
}
