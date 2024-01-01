package dev.toma.pubgmc.common.games.game.domination;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
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
    public float captureSpeed = 0.0125F; // calculate with: x = 1 / (4 * <seconds to capture>)
    public int totalTicketCount = 10000;
    public int pointTicketLoss = 50;
    public int killTicketLoss = 15;
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
    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

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

    public void serialize(DataWriter<?> writer) {
        writer.writeInt("playerCount", playerCount);
        writer.writeFloat("captureSpeed", captureSpeed);
        writer.writeInt("totalTickets", totalTicketCount);
        writer.writeInt("pointTicketLoss", pointTicketLoss);
        writer.writeInt("killTicketLoss", killTicketLoss);
        writer.writeInt("gameDuration", gameDuration);
        writer.writeBoolean("allowAi", allowAi);
        writer.writeBoolean("requirePointSuperiority", requirePointSuperiority);
        writer.writeStringArray("loadouts", availableLoadouts);
        writer.write("worldConfig", worldConfiguration, GameWorldConfiguration::serialize);
    }

    public static DominationGameConfiguration deserialize(DataReader<?> reader) {
        DominationGameConfiguration cfg = new DominationGameConfiguration();
        cfg.playerCount = reader.readInt("playerCount", cfg.playerCount);
        cfg.captureSpeed = reader.readFloat("captureSpeed", cfg.captureSpeed);
        cfg.totalTicketCount = reader.readInt("totalTickets", cfg.totalTicketCount);
        cfg.pointTicketLoss = reader.readInt("pointTicketLoss", cfg.pointTicketLoss);
        cfg.killTicketLoss = reader.readInt("killTicketLoss", cfg.killTicketLoss);
        cfg.gameDuration = reader.readInt("gameDuration", cfg.gameDuration);
        cfg.allowAi = reader.readBoolean("allowAi", cfg.allowAi);
        cfg.requirePointSuperiority = reader.readBoolean("requirePointSuperiority", cfg.requirePointSuperiority);
        cfg.availableLoadouts = reader.readStringArray("loadouts", cfg.availableLoadouts);
        cfg.worldConfiguration = reader.read("worldConfig", GameWorldConfiguration::deserialize, cfg.worldConfiguration);
        return cfg;
    }
}
