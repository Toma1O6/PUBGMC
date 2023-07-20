package dev.toma.pubgmc.api.properties;

import dev.toma.pubgmc.Pubgmc;

public final class SharedProperties {

    public static final PropertyType<Integer> KILLS = Properties.intProperty(Pubgmc.getResource("kills"));
    public static final PropertyType<Integer> DEATHS = Properties.intProperty(Pubgmc.getResource("deaths"));
    public static final PropertyType<Integer> CAPTURES = Properties.intProperty(Pubgmc.getResource("captures"));
    public static final PropertyType<Integer> SCORE = Properties.intProperty(Pubgmc.getResource("score"));
    public static final PropertyType<Long> GAME_TIMESTAMP = Properties.longProperty(Pubgmc.getResource("game_timestamp"));

    private SharedProperties() {
    }
}
