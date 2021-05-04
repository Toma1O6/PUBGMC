package dev.toma.pubgmc.common.game;

import com.google.common.collect.ImmutableList;
import dev.toma.pubgmc.common.game.argument.ArgumentKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

public final class GameType<G extends AbstractGame> {

    private final ResourceLocation registryKey;
    private final InstanceFactory<G> instanceFactory;
    private final ITextComponent name;
    private final ITextComponent[] description;
    private List<ArgumentKey<?>> argumentKeys;

    GameType(GameTypeBuilder<G> builder) {
        registryKey = builder.registryKey;
        instanceFactory = builder.instanceFactory;
        name = builder.title;
        description = builder.description.toArray(new ITextComponent[0]);
        argumentKeys = builder.argumentKeys;
    }

    public ResourceLocation getRegistryKey() {
        return registryKey;
    }

    public List<ArgumentKey<?>> getArguments() {
        return ImmutableList.copyOf(argumentKeys);
    }

    public InstanceFactory<G> getInstanceFactory() {
        return instanceFactory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameType<?> gameType = (GameType<?>) o;
        return registryKey.equals(gameType.registryKey);
    }

    @Override
    public int hashCode() {
        return registryKey.hashCode();
    }

    public interface InstanceFactory<G extends AbstractGame> {
        G newInstance(GameLaunchConfig<G> launchConfig);
    }
}
