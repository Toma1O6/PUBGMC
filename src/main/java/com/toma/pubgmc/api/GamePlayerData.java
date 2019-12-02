package com.toma.pubgmc.api;

import com.toma.pubgmc.api.teams.Team;

public class GamePlayerData {

    private final Team team;
    private int killCount;
    private int data;

    public GamePlayerData(Team team) {
        this.team = team;
    }

    public void addKill() {
        this.killCount++;
    }

    public void addData(int data) {
        this.data += data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getData() {
        return data;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else {
            if(obj instanceof GamePlayerData) {
                GamePlayerData o = (GamePlayerData) obj;
                return this.team.equals(o.team);
            }
            return false;
        }
    }
}
