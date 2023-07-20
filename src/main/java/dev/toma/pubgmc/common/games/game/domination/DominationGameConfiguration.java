package dev.toma.pubgmc.common.games.game.domination;

import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;

public final class DominationGameConfiguration implements TeamGameConfiguration {

    public int playerCount = 16;
    public float captureSpeed = 0.025F;
    public int totalTicketCount = 1000;
    public int pointTicketLoss = 25;
    public int killTicketLoss = 10;
    public int gameDuration = 18000;

    @Override
    public void performCorrections() {

    }
}
