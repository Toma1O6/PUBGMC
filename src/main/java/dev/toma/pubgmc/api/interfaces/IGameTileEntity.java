package dev.toma.pubgmc.api.interfaces;

/**
 * Interface for executing game related tasks once tileentity gets loaded in
 * world. These functions are only executed when IGameData#isPlaying is true
 * and last tileentity game hash is not equal current game hash.
 * API is still unfinished and custom game creating is not possible (but these methods will be called)!
 * <p>
 * Default implementation can be found at {@link dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator}
 * <p>
 * Created by Toma
 */
public interface IGameTileEntity {

    /**
     * @return the String field
     */
    String getGameHash();

    /**
     * Used to update tileentity hash to current game hash
     *
     * @param hash - the hash you set to your String field
     */
    void setGameHash(String hash);

    /**
     * Called when tilentity hash gets updated. Can be null,
     * but the tileentity will be ignored and hash won't update
     */
    void onLoaded();
}
