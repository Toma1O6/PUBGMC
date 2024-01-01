package dev.toma.pubgmc.common.games.game.ffa;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;

public class FFAGameConfiguration implements GameConfiguration {

    public static final String LOADOUT_UMP45 = "ffa/ump45";
    public static final String LOADOUT_VECTOR = "ffa/vector";
    public static final String LOADOUT_AKM = "ffa/akm";
    public static final String LOADOUT_M416 = "ffa/m416";
    public static final String LOADOUT_SLR = "ffa/slr";
    public static final String LOADOUT_M24 = "ffa/m24";

    public int entityCount = 8;
    public boolean allowAi = true;
    public int gameDuration = 12000;
    public int killTarget = 30;
    public int spawnProtectionTime = 60;
    public int healPerKill = 3;
    public String[] loadoutFiles = {
            LOADOUT_UMP45,
            LOADOUT_VECTOR,
            LOADOUT_AKM,
            LOADOUT_M416,
            LOADOUT_SLR,
            LOADOUT_M24
    };
    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public void performCorrections() {
        entityCount = Math.max(2, entityCount);
        gameDuration = Math.max(1200, gameDuration);
        killTarget = Math.max(-1, killTarget);
        spawnProtectionTime = Math.max(0, spawnProtectionTime);
        healPerKill = Math.max(0, healPerKill);
        worldConfiguration.correct();
    }

    public void serialize(DataWriter<?> writer) {
        writer.writeInt("entityCount", entityCount);
        writer.writeInt("gameDuration", gameDuration);
        writer.writeInt("killTarget", killTarget);
        writer.writeBoolean("allowAi", allowAi);
        writer.writeInt("spawnProtection", spawnProtectionTime);
        writer.writeInt("healPerKill", healPerKill);
        writer.writeStringArray("loadouts", loadoutFiles);
        writer.write("worldConfig", worldConfiguration, GameWorldConfiguration::serialize);
    }

    public static FFAGameConfiguration deserialize(DataReader<?> reader) {
        FFAGameConfiguration cfg = new FFAGameConfiguration();
        cfg.entityCount = reader.readInt("entityCount", cfg.entityCount);
        cfg.gameDuration = reader.readInt("gameDuration", cfg.gameDuration);
        cfg.killTarget = reader.readInt("killTarget", cfg.killTarget);
        cfg.allowAi = reader.readBoolean("allowAi", cfg.allowAi);
        cfg.spawnProtectionTime = reader.readInt("spawnProtection", cfg.spawnProtectionTime);
        cfg.healPerKill = reader.readInt("healPerKill", cfg.healPerKill);
        cfg.loadoutFiles = reader.readStringArray("loadouts", cfg.loadoutFiles);
        cfg.worldConfiguration = reader.read("worldConfig", GameWorldConfiguration::deserialize, cfg.worldConfiguration);
        return cfg;
    }
}
