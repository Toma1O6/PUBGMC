package dev.toma.pubgmc.common.game.exception;

public class InvalidMapNameException extends MapException {

    public InvalidMapNameException(String mapName) {
        super(mapName + " is not valid name! Map name can only contain a-z0-9_- characters!");
    }
}
