package com.toma.pubgmc.api.teams;

public class TeamSettings {

    public final boolean isSizeLimited;
    public final int maxSize;
    public final boolean eliminateOnDeath;

    public TeamSettings(final int size, final boolean isSizeLimited, final boolean eliminateOnDeath) {
        this.isSizeLimited = isSizeLimited;
        this.maxSize = size;
        this.eliminateOnDeath = eliminateOnDeath;
    }
}
