package dev.toma.pubgmc.api.game.mutator;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Optional;

public class GameMutatorHelper {

    public static <T extends GameMutator> Optional<T> getMutator(World world, GameMutatorType<T> type) {
        return GameDataProvider.getGameData(world).flatMap(data -> {
            Game<?> game = data.getCurrentGame();
            GameType<?, ?> gameType = game.getGameType();
            if (!game.isStarted())
                return Optional.empty();
            return GameMutatorManager.INSTANCE.getMutator(gameType, type);
        });
    }

    @SuppressWarnings("unchecked")
    public static <R, T extends GameMutator> Optional<R> getMutatorGeneric(World world, GameMutatorType<T> type) {
        return getMutator(world, type).flatMap(mutator -> {
            try {
                return Optional.of((R) mutator);
            } catch (ClassCastException e) {
                return Optional.empty();
            }
        });
    }

    public static void giveKillReward(EntityLivingBase victim, DamageSource source) {
        getMutator(victim.world, GameMutators.KILL_REWARD).ifPresent(mutator -> mutator.handle(victim, source));
    }
}
