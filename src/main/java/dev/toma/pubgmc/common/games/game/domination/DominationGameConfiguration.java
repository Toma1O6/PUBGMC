package dev.toma.pubgmc.common.games.game.domination;

import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;

public final class DominationGameConfiguration implements TeamGameConfiguration {

    public static final String LOADOUT_UMP45 = "domination/ump45";
    public static final String LOADOUT_VECTOR = "domination/vector";
    public static final String LOADOUT_AKM = "domination/akm";
    public static final String LOADOUT_M416 = "domination/m416";
    public static final String LOADOUT_SLR = "domination/slr";
    public static final String LOADOUT_M24 = "domination/m24";

    public int playerCount = 16;
    public float captureSpeed = 0.025F;
    public int totalTicketCount = 1000;
    public int pointTicketLoss = 25;
    public int killTicketLoss = 10;
    public int gameDuration = 18000;
    public boolean allowAi = true;
    public boolean requirePointSuperiority = false;
    public String[] availableLoadouts = {
            LOADOUT_UMP45,
            LOADOUT_VECTOR,
            LOADOUT_AKM,
            LOADOUT_M416,
            LOADOUT_SLR,
            LOADOUT_M24
    };
    public final GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public void performCorrections() {
        playerCount = Math.max(2, playerCount);
        captureSpeed = Math.max(0.0001F, captureSpeed);
        totalTicketCount = Math.max(10, totalTicketCount);
        pointTicketLoss = Math.max(1, pointTicketLoss);
        killTicketLoss = Math.max(0, killTicketLoss);
        gameDuration = Math.max(1200, gameDuration);
        worldConfiguration.correct();
    }
}
