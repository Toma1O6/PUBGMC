package com.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.interfaces.TeamFillFactory;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.api.teams.TeamSettings;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public final class TeamManager {

    private final TeamSettings gameTeamSettings;
    private final TeamFillFactory teamFillFactory;
    private final Function<Game, List<Team>> teamCreator;

    private TeamManager(final Builder builder) {
        this.gameTeamSettings = builder.settings;
        this.teamFillFactory = builder.factory;
        this.teamCreator = builder.creator;
    }

    public TeamSettings getTeamSettings() {
        return gameTeamSettings;
    }

    public TeamFillFactory getTeamFillFactory() {
        return teamFillFactory;
    }

    public Function<Game, List<Team>> getTeamCreator() {
        return teamCreator;
    }

    public static void getDefaultFillFactory(Iterator<UUID> players, Iterator<Team> teams) {
        while(players.hasNext()) {
            boolean foundTeam = false;
            UUID uuid = players.next();
            while(teams.hasNext() && !foundTeam) {
                Team team = teams.next();
                foundTeam = team.add(uuid);
                if(!foundTeam) {
                    teams.remove();
                }
            }
        }
    }

    public static class Builder {

        private TeamSettings settings;
        private TeamFillFactory factory = TeamManager::getDefaultFillFactory;
        private Function<Game, List<Team>> creator;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder settings(final TeamSettings settings) {
            this.settings = settings;
            return this;
        }

        public Builder fillFactory(final TeamFillFactory factory) {
            this.factory = factory;
            return this;
        }

        public Builder creator(final Function<Game, List<Team>> function) {
            this.creator = function;
            return this;
        }

        public TeamManager build() {
            Preconditions.checkNotNull(settings, "Team settings cannot be null!");
            Preconditions.checkNotNull(factory, "Team fill factory cannot be null!");
            Preconditions.checkNotNull(creator, "Team creator cannot be null!");
            return new TeamManager(this);
        }
    }
}
