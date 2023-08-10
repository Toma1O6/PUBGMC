package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class GameEvent extends Event {

    private final Game<?> game;

    public GameEvent(Game<?> game) {
        this.game = game;
    }

    public Game<?> getGame() {
        return game;
    }

    public static final class Initialized extends GameEvent {

        public Initialized(Game<?> game) {
            super(game);
        }
    }

    public static final class MapCreated extends GameEvent {

        private final GameMap map;

        public MapCreated(Game<?> game, GameMap map) {
            super(game);
            this.map = map;
        }

        public GameMap getMap() {
            return map;
        }
    }

    public static final class Started extends GameEvent {

        private final GameMap map;

        public Started(Game<?> game, GameMap map) {
            super(game);
            this.map = map;
        }

        public GameMap getMap() {
            return map;
        }
    }

    public static final class Stopped extends GameEvent {

        public Stopped(Game<?> game) {
            super(game);
        }
    }

    public static final class PlayerCompleteGame extends GameEvent {

        private final EntityPlayer player;
        private final boolean won;

        public PlayerCompleteGame(Game<?> game, EntityPlayer player, boolean won) {
            super(game);
            this.player = player;
            this.won = won;
        }

        public EntityPlayer getPlayer() {
            return player;
        }

        public boolean hasWonGame() {
            return won;
        }
    }
}
