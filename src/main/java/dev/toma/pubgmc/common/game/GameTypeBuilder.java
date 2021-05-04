package dev.toma.pubgmc.common.game;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.common.game.argument.ArgumentKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.List;

public final class GameTypeBuilder<G extends AbstractGame> {

    ResourceLocation registryKey;
    GameType.InstanceFactory<G> instanceFactory;
    ITextComponent title;
    List<ITextComponent> description;
    List<ArgumentKey<?>> argumentKeys;

    private GameTypeBuilder() {
        description = new ArrayList<>();
        argumentKeys = new ArrayList<>();
    }

    public static <G extends AbstractGame> GameTypeBuilder<G> create() {
        return new GameTypeBuilder<>();
    }

    public GameTypeBuilder<G> registryKey(ResourceLocation key) {
        this.registryKey = registryKey;
        return this;
    }

    public GameTypeBuilder<G> instanceFactory(GameType.InstanceFactory<G> instanceFactory) {
        this.instanceFactory = instanceFactory;
        return this;
    }

    public GameTypeBuilder<G> title(String localizationKey) {
        this.title = new TextComponentTranslation(localizationKey);
        return this;
    }

    public GameTypeBuilder<G> addDescriptionLine(String lineLocalizationKey) {
        description.add(new TextComponentTranslation(lineLocalizationKey));
        return this;
    }

    public GameTypeBuilder<G> registerArgument(ArgumentKey<?> key) {
        argumentKeys.add(key);
        return this;
    }

    public GameType<G> build() {
        Preconditions.checkNotNull(registryKey, "Registry key cannot be null!");
        Preconditions.checkNotNull(instanceFactory, "Instance factory cannot be null!");
        Preconditions.checkNotNull(title, "Title cannot be null!");
        Preconditions.checkState(!description.isEmpty(), "Description must contain atleast one line!");
        return new GameType<>(this);
    }
}
