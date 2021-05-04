package dev.toma.pubgmc.common.game.event;

import dev.toma.pubgmc.common.game.GameType;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameTypeRegistryEvent extends Event {

    final List<GameType<?>> types;

    public GameTypeRegistryEvent() {
        types = new ArrayList<>();
    }

    public void register(GameType<?> gameType) {
        types.add(gameType);
    }

    public void registerAll(GameType<?>... types) {
        for (GameType<?> type : types) {
            register(type);
        }
    }

    public void forEachType(Consumer<GameType<?>> action) {
        for (GameType<?> type : types) {
            action.accept(type);
        }
    }
}
