package dev.toma.pubgmc.common.game.argument;

import dev.toma.pubgmc.api.common.game.IArgument;

import java.util.Objects;

public abstract class AbstractArgument<V> implements IArgument<V> {

    private V v;

    public AbstractArgument(V v) {
        set(Objects.requireNonNull(v));
    }

    @Override
    public V get() {
        return v;
    }

    @Override
    public void set(V v) {
        this.v = v;
    }

    public static IArgument<Boolean> bool(boolean value) {
        return new BoolArgument(value);
    }

    public static class BoolArgument extends AbstractArgument<Boolean> {
        public BoolArgument(boolean value) {
            super(value);
        }

        @Override
        public IArgument<Boolean> copy() {
            return new BoolArgument(get());
        }
    }

    public static class IntArgument extends AbstractArgument<Integer> {
        public IntArgument(int value) {
            super(value);
        }

        @Override
        public IArgument<Integer> copy() {
            return new IntArgument(get());
        }
    }
}
