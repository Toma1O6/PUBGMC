package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.api.game.Game;
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
