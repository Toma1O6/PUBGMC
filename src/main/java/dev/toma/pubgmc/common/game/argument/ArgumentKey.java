package dev.toma.pubgmc.common.game.argument;

import dev.toma.pubgmc.api.common.game.IArgument;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.function.Predicate;

public class ArgumentKey<V> {

    private final String key;
    private final IArgument<V> fallback;
    private final Predicate<IArgument<V>> validator;
    private final ITextComponent localizedName;

    private ArgumentKey(Builder<V> builder) {
        key = builder.key;
        fallback = builder.fallback;
        validator = builder.validator;
        localizedName = new TextComponentTranslation("argument.key." + key);
    }

    public String getDisplayName() {
        return localizedName.getFormattedText();
    }

    public IArgument<V> getDefaultValue() {
        return fallback;
    }

    public boolean isValid(IArgument<V> argument) {
        return validator.test(argument);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArgumentKey<?> that = (ArgumentKey<?>) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "key=" + key;
    }

    public static class Builder<V> {

        private String key;
        private IArgument<V> fallback;
        private Predicate<IArgument<V>> validator = v -> true;

        public Builder<V> key(String key) {
            this.key = key;
            return this;
        }

        public Builder<V> fallback(IArgument<V> fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder<V> validator(Predicate<IArgument<V>> validator) {
            this.validator = validator;
            return this;
        }

        public ArgumentKey<V> build() {
            return new ArgumentKey<>(this);
        }
    }
}
