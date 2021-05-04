package dev.toma.pubgmc.common.game;

import dev.toma.pubgmc.common.game.interfaces.IGameStage;
import dev.toma.pubgmc.common.game.interfaces.IPlayerManager;
import net.minecraft.entity.player.EntityPlayer;

public class GameStageHandler {

    private IGameStage currentStage;

    public GameStageHandler() {
        currentStage = GameStages.SCHEDULED;
    }

    public void playerJoin(EntityPlayer player, IPlayerManager playerManager, IGameStage stage) {
        if(stage.allowPlayerJoin()) {
            playerManager.join(player);
        }
    }

    public void playerLeave(EntityPlayer player, IPlayerManager playerManager, IGameStage stage) {
        playerManager.leave(player);
    }

    public final void nextStage(AbstractGame game) {
        onStageEnding(currentStage, game);
        IGameStage stage = currentStage.next();
        if(stage != null) {
            currentStage = stage;
            onStageStarting(stage, game);
        } else {
            onGameEnd(game);
        }
    }

    protected void onStageEnding(IGameStage stage, AbstractGame game) {

    }

    protected void onStageStarting(IGameStage stage, AbstractGame game) {

    }

    protected void onGameEnd(AbstractGame game) {

    }
}
