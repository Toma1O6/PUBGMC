package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.client.animation.interfaces.Animation;
import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Function;

public class AnimationType<A extends Animation> {

    final Function<EntityPlayer, A> factory;

    AnimationType(Function<EntityPlayer, A> factory) {
        this.factory = factory;
    }

    public A createAnimation(EntityPlayer player) {
        return factory.apply(player);
    }

    public static <A extends Animation> AnimationType<A> create(Function<EntityPlayer, A> factory) {
        return new AnimationType<>(factory);
    }
}
