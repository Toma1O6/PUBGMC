package dev.toma.pubgmc.common.game.interfaces;

public interface IGameStage {

    boolean allowPlayerJoin();

    IGameStage next();
}
