package dev.toma.pubgmc.api.game.mutator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import java.util.function.BiConsumer;

public class KillRewardMutator implements GameMutator {

    private final BiConsumer<EntityLivingBase, DamageSource> handler;

    public KillRewardMutator(BiConsumer<EntityLivingBase, DamageSource> handler) {
        this.handler = handler;
    }

    public void handle(EntityLivingBase victim, DamageSource source) {
        this.handler.accept(victim, source);
    }
}
