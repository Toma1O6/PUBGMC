package dev.toma.pubgmc.api.client;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.PartyData;

public interface DataListenerGui {

    default void onGameDataReceived(GameData data) {
    }

    default void onPartyDataReceived(PartyData data) {
    }
}
