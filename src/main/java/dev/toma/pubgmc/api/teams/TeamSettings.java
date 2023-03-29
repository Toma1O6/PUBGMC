package dev.toma.pubgmc.api.teams;

public class TeamSettings {

    public final boolean isSizeLimited;
    public final boolean eliminateOnDeath;
    public int maxSize;

    public TeamSettings(final int size, final boolean isSizeLimited, final boolean eliminateOnDeath) {
        this.isSizeLimited = isSizeLimited;
        this.maxSize = size;
        this.eliminateOnDeath = eliminateOnDeath;
    }
}
