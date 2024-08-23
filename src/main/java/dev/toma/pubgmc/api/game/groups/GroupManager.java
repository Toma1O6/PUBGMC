package dev.toma.pubgmc.api.game.groups;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.capability.PartyDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.common.games.NoGame;
import net.minecraft.world.World;

import java.util.UUID;

public interface GroupManager {

    Group getGroupForPlayer(UUID playerId);

    default boolean canCreateTeam(UUID playerId) {
        return false;
    }

    static GroupManager getInContext(World world) {
        return GameDataProvider.getGameData(world).map(data -> {
            Game<?> game = data.getCurrentGame();
            if (game != null && NoGame.INSTANCE != game) {
                if (game instanceof TeamGame<?>) {
                    TeamGame<?> teamGame = (TeamGame<?>) game;
                    return teamGame.getTeamManager();
                }
                return SingleplayerGameGroupManager.GROUP_MANAGER;
            }
            return PartyDataProvider.getPartyData(world).map(t -> (GroupManager) t).orElse(SingleplayerGameGroupManager.GROUP_MANAGER);
        }).orElse(SingleplayerGameGroupManager.GROUP_MANAGER);
    }
}
