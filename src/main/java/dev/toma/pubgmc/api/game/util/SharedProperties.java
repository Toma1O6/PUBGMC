package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.Pubgmc;

public final class SharedProperties {

    public static final PlayerPropertyHolder.PropertyType<Integer> KILLS = PlayerPropertyHolder.PropertyType.intProperty(Pubgmc.getResource("kills"));
    public static final PlayerPropertyHolder.PropertyType<Long> GAME_TIMESTAMP = PlayerPropertyHolder.PropertyType.longProperty(Pubgmc.getResource("game_timestamp"));

    private SharedProperties() {
    }
}
