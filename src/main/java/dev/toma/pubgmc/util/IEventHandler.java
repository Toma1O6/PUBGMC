package dev.toma.pubgmc.util;

import java.util.function.Consumer;

public interface IEventHandler<T> {

    void invoke(Consumer<T> tConsumer);

    void subscribe(T t);

    void unsubscribe(T t);
}
