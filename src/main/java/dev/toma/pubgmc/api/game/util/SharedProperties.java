package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.Pubgmc;

public final class SharedProperties {

    public static final PlayerPropertyHolder.PropertyType<Integer> KILLS = PlayerPropertyHolder.PropertyType.intProperty(Pubgmc.getResource("kills"));
}
