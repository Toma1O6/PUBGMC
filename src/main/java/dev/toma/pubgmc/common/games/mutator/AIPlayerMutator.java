package dev.toma.pubgmc.common.games.mutator;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.mutator.GameMutator;
import dev.toma.pubgmc.api.game.mutator.GameMutatorType;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;

import java.util.function.BiConsumer;

public final class AIPlayerMutator<G extends Game<?>> implements GameMutator {

    public static final GameMutatorType<AIPlayerMutator<?>> TYPE = GameMutatorType.createMutatorType(Pubgmc.getResource("ai"));
    private final BiConsumer<EntityAIPlayer, G> applicator;

    public AIPlayerMutator(BiConsumer<EntityAIPlayer, G> applicator) {
        this.applicator = applicator;
    }

    public void apply(G game, EntityAIPlayer player) {
        this.applicator.accept(player, game);
    }
}
