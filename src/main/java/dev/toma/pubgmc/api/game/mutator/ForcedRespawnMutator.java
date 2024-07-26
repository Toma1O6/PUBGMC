package dev.toma.pubgmc.api.game.mutator;

public class ForcedRespawnMutator implements GameMutator {

    private final int respawnAfterTime;

    public ForcedRespawnMutator(int respawnAfter) {
        this.respawnAfterTime = respawnAfter;
    }

    public int getRespawnAfterTime() {
        return respawnAfterTime;
    }
}
