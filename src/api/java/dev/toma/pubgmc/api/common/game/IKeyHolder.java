package dev.toma.pubgmc.api.common.game;

public interface IKeyHolder {

    /**
     * Used to update held gameID in this object
     * Useful for example for loot generators to not
     * generate loot multiple times in one game
     * @param gameID - new ID to be set
     */
    void setGameID(long gameID);

    /**
     * Used to obtain currently held game ID for further processing
     * @return currently contained game ID
     */
    long getGameID();
}
