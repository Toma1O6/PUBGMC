package dev.toma.pubgmc.api.game.mutator;

import dev.toma.pubgmc.Pubgmc;

public final class GameMutators {

    public static final GameMutatorType<LightmapMutator> LIGHTMAP = GameMutatorType.createMutatorType(Pubgmc.getResource("lightmap"));
    public static final GameMutatorType<InventoryMutator> INVENTORY_LIMIT = GameMutatorType.createMutatorType(Pubgmc.getResource("inventory"));
    public static final GameMutatorType<AIPlayerMutator<?>> AI_TASKS = GameMutatorType.createMutatorType(Pubgmc.getResource("ai"));
    public static final GameMutatorType<KillRewardMutator> KILL_REWARD = GameMutatorType.createMutatorType(Pubgmc.getResource("kill_reward"));
    public static final GameMutatorType<ArmorMutator> ARMOR = GameMutatorType.createMutatorType(Pubgmc.getResource("armor"));

    private GameMutators() {}
}
