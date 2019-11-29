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
}
