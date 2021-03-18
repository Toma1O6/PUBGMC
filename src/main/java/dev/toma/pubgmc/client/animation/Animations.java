package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.util.ResourceLocation;

public class Animations {

    public static final ResourceLocation TEST_ANIMATION = create("test");

    public static void registerAnimations(AnimationLoader loader) {
        loader.registerEntries(
                TEST_ANIMATION
        );
    }

    static ResourceLocation create(String name) {
        return new ResourceLocation(Pubgmc.MOD_ID, name);
    }
}
