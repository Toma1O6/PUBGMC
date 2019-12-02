package com.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GamePlayerData;
import com.toma.pubgmc.api.interfaces.TeamFillFactory;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.api.teams.TeamSettings;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public final class TeamManager<T extends Game> {

    private final TeamSettings gameTeamSettings;
    private final TeamFillFactory teamFillFactory;
    private final Function<T, List<Team>> teamCreator;

    private TeamManager(final Builder<T> builder) {
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

    public Function<T, List<Team>> getTeamCreator() {
        return teamCreator;
    }

    public void updateSize(int teamSize) {
        this.getTeamSettings().maxSize = teamSize;
    }

    public static <T extends Game> void getDefaultFillFactory(Iterator<UUID> players, Iterator<Team> teams, T game) {
        while(players.hasNext()) {
            boolean foundTeam = false;
            UUID uuid = players.next();
            while(teams.hasNext() && !foundTeam) {
                Team team = teams.next();
                foundTeam = team.add(uuid);
                if(!foundTeam) {
                    teams.remove();
                } else {
                    game.getPlayerData().put(uuid, new GamePlayerData(team));
                }
            }
        }
    }

    public static class Builder<T extends Game> {

        private TeamSettings settings;
        private TeamFillFactory factory = TeamManager::getDefaultFillFactory;
        private Function<T, List<Team>> creator;

        private Builder() {
        }

        public static <T extends Game> Builder<T> create(T game) {
            return new Builder<>();
        }

        public Builder<T> settings(final TeamSettings settings) {
            this.settings = settings;
            return this;
        }

        public Builder<T> fillFactory(final TeamFillFactory<T> factory) {
            this.factory = factory;
            return this;
        }

        public Builder<T> creator(final Function<T, List<Team>> function) {
            this.creator = function;
            return this;
        }

        public TeamManager<T> build() {
            Preconditions.checkNotNull(settings, "Team settings cannot be null!");
            Preconditions.checkNotNull(factory, "Team fill factory cannot be null!");
            Preconditions.checkNotNull(creator, "Team creator cannot be null!");
            return new TeamManager<>(this);
        }
    }
}
