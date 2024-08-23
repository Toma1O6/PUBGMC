package dev.toma.pubgmc.api.game.groups;

public final class PartyOptions {

    private boolean autoJoinGames;

    public void setAutoJoinGames(boolean autoJoinGames) {
        this.autoJoinGames = autoJoinGames;
    }

    public boolean shouldAutoJoinGames() {
        return autoJoinGames;
    }
}
