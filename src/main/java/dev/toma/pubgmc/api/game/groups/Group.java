package dev.toma.pubgmc.api.game.groups;

import java.util.UUID;

public interface Group {

    UUID getId();

    UUID getLeader();

    String getUsername(UUID uuid);
}
