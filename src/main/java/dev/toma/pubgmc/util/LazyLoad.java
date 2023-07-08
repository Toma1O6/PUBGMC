package dev.toma.pubgmc.util;

import java.util.Objects;
import java.util.function.Supplier;

public class LazyLoad<T> implements Supplier<T> {

    Supplier<T> supplier;
    T t;

    public LazyLoad(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (supplier != null) {
            t = Objects.requireNonNull(supplier.get());
            supplier = null;
        }
        return t;
    }
}
