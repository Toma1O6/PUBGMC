package dev.toma.pubgmc.api.teams;

import javax.annotation.Nonnull;
import java.util.UUID;

public class Team {

    public final String name;
    public final int maxSize;
    public UUID[] players;
    public final int color;

    public Team(int maxSize, int color) {
        this(maxSize, color, "");
    }

    public Team(int maxSize, int color, String name) {
        this.maxSize = maxSize;
        this.color = color;
        this.players = new UUID[maxSize];
        this.name = name;
    }

    public boolean add(@Nonnull UUID uuid) {
        int index = this.getEntityCount();
        if(index >= this.maxSize) {
            return false;
        }
        players[index] = uuid;
        return true;
    }

    public boolean remove(@Nonnull UUID uuid) {
        int index = -1;
        for(int i = 0; i < players.length; i++) {
            if(players[i].equals(uuid)) {
                index = i;
                break;
            }
        }
        if(index < 0) return false;
        this.players[index] = null;
        return true;
    }

    public boolean isFull() {
        return this.getEntityCount() == maxSize;
    }

    public boolean isEmpty() {
        return this.getEntityCount() == 0;
    }

    public int getEntityCount() {
        int count = 0;
        for(UUID uuid : players) {
            if(uuid != null) ++count;
        }
        return count;
    }

    @Override
    public int hashCode() {
        int i = 0;
        for(UUID uuid : this.players) {
            if(uuid == null) continue;
            i += uuid.hashCode();
        }
        return i;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else {
            if(obj instanceof Team) {
                Team team = (Team) obj;
                if(this.getEntityCount() != team.getEntityCount()) {
                    return false;
                }
                for(int i = 0; i < team.players.length; i++) {
                    UUID uuid0 = this.players[i];
                    UUID uuid1 = team.players[i];
                    if(uuid0 != null && uuid1 != null) {
                        if(!uuid0.equals(uuid1)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
    }
}
