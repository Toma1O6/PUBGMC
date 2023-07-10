package dev.toma.pubgmc.api.entity;

public interface EntityDebuffs {

    boolean isBlind();

    boolean isDeaf();

    void clearBlindStatus();

    void clearDeafStatus();

    void setBlindTime(int time);

    void setDeafTime(int time);

    int getRemainingBlindTime();

    int getRemainingDeafTime();
}
