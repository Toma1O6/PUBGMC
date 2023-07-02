package dev.toma.pubgmc.api.game;

import java.util.UUID;

public interface GameObject {

    UUID getCurrentGameId();

    void assignGameId(UUID gameId);

    void onNewGameDetected(UUID newGameId);
}
