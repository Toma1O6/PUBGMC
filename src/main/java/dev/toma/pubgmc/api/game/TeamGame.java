package dev.toma.pubgmc.api.game;

public interface TeamGame<CFG extends GameConfiguration> extends Game<CFG> {

    TeamManager getTeamManager();
}
