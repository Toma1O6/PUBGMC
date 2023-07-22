package dev.toma.pubgmc.api.game.mutator;

import java.util.function.UnaryOperator;

public class LightmapMutator implements GameMutator {

    public static final LightmapMutator DEFAULT = new LightmapMutator(gamma -> gamma, 0.10F, 0.2F);

    private final UnaryOperator<Float> clientGammaProcessor;
    private final float gammaMultiplier;
    private final float gammaBoost;

    public LightmapMutator(UnaryOperator<Float> clientGammaProcessor, float gammaMultiplier, float gammaBoost) {
        this.clientGammaProcessor = clientGammaProcessor;
        this.gammaMultiplier = gammaMultiplier;
        this.gammaBoost = gammaBoost;
    }

    public float applyClientGamma(float gamma) {
        return clientGammaProcessor.apply(gamma);
    }

    public float getGammaMultiplier() {
        return gammaMultiplier;
    }

    public float getGammaBoost() {
        return gammaBoost;
    }
}
