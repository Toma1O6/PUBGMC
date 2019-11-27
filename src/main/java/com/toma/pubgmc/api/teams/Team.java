package com.toma.pubgmc.api.teams;

import javax.annotation.Nonnull;
import java.util.UUID;

public class Team {

    public final int maxSize;
    public UUID[] players;
    public final int color;

    public Team(int maxSize, int color) {
        this.maxSize = maxSize;
        this.color = color;
        this.players = new UUID[maxSize];
    }

    public boolean add(@Nonnull UUID uuid) {
        int index = this.getEntityCount();
        if(index >= this.maxSize) {
            return false;
        }
        players[index] = uuid;
        return true;
    }

    public boolean isFull() {
        return this.getEntityCount() == maxSize;
    }

    public int getEntityCount() {
        int count = 0;
        for(UUID uuid : players) {
            if(uuid != null) ++count;
        }
        return count;
    }
}
