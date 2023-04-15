package dev.toma.pubgmc.api.game;

public class GameStartException extends Exception {

    public GameStartException() {
    }

    public GameStartException(String message) {
        super(message);
    }

    public GameStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameStartException(Throwable cause) {
        super(cause);
    }
}
