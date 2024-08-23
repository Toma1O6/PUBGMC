package dev.toma.pubgmc.api.game.groups;

import java.util.UUID;

public class SingleplayerGameGroupManager implements GroupManager {

    public static final GroupManager GROUP_MANAGER = new SingleplayerGameGroupManager();

    private SingleplayerGameGroupManager() {}

    @Override
    public Group getGroupForPlayer(UUID playerId) {
        return null;
    }
}
