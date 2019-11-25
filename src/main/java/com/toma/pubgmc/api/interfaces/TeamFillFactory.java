package com.toma.pubgmc.api.interfaces;

import com.toma.pubgmc.api.teams.Team;

import java.util.Iterator;
import java.util.UUID;

public interface TeamFillFactory {

    void fill(Iterator<UUID> players, Iterator<Team> teams);
}
