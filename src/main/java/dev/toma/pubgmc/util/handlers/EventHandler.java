package dev.toma.pubgmc.util.handlers;

import dev.toma.pubgmc.util.IEventHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class EventHandler<T> implements IEventHandler<T> {

    private final List<T> subscribers;

    private EventHandler(T[] subscribers) {
        this.subscribers = new ArrayList<>();
        if(subscribers != null)
            for (T t : subscribers)
                subscribe(t);
    }

    public static <T> IEventHandler<T> emptyEventHandler() {
        return newEventHandler(null);
    }

    public static <T> IEventHandler<T> newEventHandler(T[] subscribers) {
        return new EventHandler<>(subscribers);
    }

    @Override
    public void invoke(Consumer<T> consumer) {
        subscribers.forEach(consumer);
    }

    @Override
    public void subscribe(T t) {
        subscribers.add(t);
    }

    @Override
    public void unsubscribe(T t) {
        subscribers.remove(t);
    }
}
