package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.common.game.interfaces.IGameStage;

public final class GameStages {

    public static final IGameStage SCHEDULED;
    public static final IGameStage WAITING;
    public static final IGameStage PLAYING;
    public static final IGameStage ENDING;

    static {
        SCHEDULED = new ScheduledStage();
        WAITING = new WaitingStage();
        PLAYING = new PlayingStage();
        ENDING = new EndingStage();
    }

    private static class ScheduledStage implements IGameStage {

        @Override
        public boolean allowPlayerJoin() {
            return false;
        }

        @Override
        public IGameStage next() {
            return WAITING;
        }
    }

    private static class WaitingStage implements IGameStage {

        @Override
        public boolean allowPlayerJoin() {
            return true;
        }

        @Override
        public IGameStage next() {
            return PLAYING;
        }
    }

    private static class PlayingStage implements IGameStage {

        @Override
        public boolean allowPlayerJoin() {
            return true;
        }

        @Override
        public IGameStage next() {
            return ENDING;
        }
    }

    private static class EndingStage implements IGameStage {

        @Override
        public boolean allowPlayerJoin() {
            return false;
        }

        @Override
        public IGameStage next() {
            return null;
        }
    }
}
