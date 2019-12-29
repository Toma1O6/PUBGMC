package com.toma.pubgmc.api.interfaces;

import com.toma.pubgmc.api.games.Game;
import com.toma.pubgmc.api.teams.Team;

import java.util.Iterator;
import java.util.UUID;

public interface TeamFillFactory<T extends Game> {

    void fill(Iterator<UUID> players, Iterator<Team> teams, T game);
}
