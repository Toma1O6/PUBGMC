package com.toma.pubgmc.util.handlers;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class GameEventHandler {

    @SubscribeEvent
    public static void onTick(TickEvent.WorldTickEvent e) {
        IGameData gameData = e.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if(!gameData.isPlaying()) {
            return;
        }
        Game game = gameData.getCurrentGame();
        if(game == null) {
            return;
        }
        game.onGameTick();
    }
}
