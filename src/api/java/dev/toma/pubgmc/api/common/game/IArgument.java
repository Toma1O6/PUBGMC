package dev.toma.pubgmc.api.common.game;

public interface IArgument<V> {

    V get();

    void set(V v);

    IArgument<V> copy();
}
