package dev.toma.pubgmc.common.game.exception;

public class MapNotFoundException extends MapException {

    public MapNotFoundException(String mapName) {
        super("Map not found: " + mapName);
    }
}
