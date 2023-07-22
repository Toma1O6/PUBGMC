package dev.toma.pubgmc.api.game.mutator;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;

import java.util.function.BiConsumer;

public final class AIPlayerMutator<G extends Game<?>> implements GameMutator {

    private final BiConsumer<EntityAIPlayer, G> applicator;

    public AIPlayerMutator(BiConsumer<EntityAIPlayer, G> applicator) {
        this.applicator = applicator;
    }

    public void apply(G game, EntityAIPlayer player) {
        this.applicator.accept(player, game);
    }
}
