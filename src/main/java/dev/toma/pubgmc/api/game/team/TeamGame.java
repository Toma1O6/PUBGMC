package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.Game;

public interface TeamGame<CFG extends TeamGameConfiguration> extends Game<CFG> {

    TeamManager getTeamManager();

    TeamInviteManager getInviteManager();
}
