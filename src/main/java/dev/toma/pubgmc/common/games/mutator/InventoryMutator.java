package dev.toma.pubgmc.common.games.mutator;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.mutator.GameMutator;
import dev.toma.pubgmc.api.game.mutator.GameMutatorType;

// could be expanded more
public class InventoryMutator implements GameMutator {

    public static final GameMutatorType<InventoryMutator> TYPE = GameMutatorType.createMutatorType(Pubgmc.getResource("inventory"));
    public static final InventoryMutator INSTANCE = new InventoryMutator();

    private InventoryMutator() {}
}
