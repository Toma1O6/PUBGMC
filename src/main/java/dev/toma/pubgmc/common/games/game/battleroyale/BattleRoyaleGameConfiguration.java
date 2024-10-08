package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class BattleRoyaleGameConfiguration implements TeamGameConfiguration {

    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();
    public boolean automaticGameJoining = true;
    public int teamSize = 4;
    public float planeSpeed = 1.0F;
    public int planeFlightDelay = 100;
    public int planeFlightHeight = 256;
    public int playzoneGenerationDelay = 2400;
    public int entityCount = 64;
    public boolean allowAi = true;
    public boolean allowAiCompanions = true;
    public int aiSpawnInterval = 300;
    public int initialAiSpawnDelay = 1200;
    public boolean displayChatDeathMessages = false;
    public ZonePhaseConfiguration[] zonePhases = {
            new ZonePhaseConfiguration(0.65F, 1.0F, 60, 2400, 6000, AirdropTrigger.NONE),
            new ZonePhaseConfiguration(0.75F, 1.0F, 40, 1800, 3600, AirdropTrigger.ANY),
            new ZonePhaseConfiguration(0.75F, 1.0F, 20, 1200, 2400, AirdropTrigger.ON_SHRINK_END),
            new ZonePhaseConfiguration(0.50F, 2.0F, 20, 800, 1800, AirdropTrigger.ON_SHRINK_START),
            new ZonePhaseConfiguration(0.25F, 3.5F, 20, 450, 1200, AirdropTrigger.ON_SHRINK_START),
            new ZonePhaseConfiguration(0.00F, 5.0F, 20, 1200, 3600, AirdropTrigger.NONE)
    };

    @Override
    public void performCorrections() {
        teamSize = Math.max(1, teamSize);
        planeSpeed = MathHelper.clamp(planeSpeed, 0.1F, 10.0F);
        planeFlightDelay = Math.max(0, planeFlightDelay);
        planeFlightHeight = MathHelper.clamp(planeFlightHeight, 15, 270);
        playzoneGenerationDelay = Math.max(0, playzoneGenerationDelay);
        entityCount = Math.max(1, entityCount);
        aiSpawnInterval = Math.max(100, aiSpawnInterval);
        initialAiSpawnDelay = Math.max(0, initialAiSpawnDelay);
        worldConfiguration.correct();
    }

    public void serialize(DataWriter<?> writer) {
        writer.writeBoolean("autoJoin", automaticGameJoining);
        writer.writeInt("teamSize", teamSize);
        writer.writeInt("playzoneGenerationDelay", playzoneGenerationDelay);
        writer.writeFloat("planeSpeed", planeSpeed);
        writer.writeInt("planeFlightDelay", planeFlightDelay);
        writer.writeInt("planeFlightHeight", planeFlightHeight);
        writer.writeInt("entityCount", entityCount);
        writer.writeBoolean("allowAi", allowAi);
        writer.writeBoolean("allowAiCompanions", allowAiCompanions);
        writer.writeInt("aiSpawnInterval", aiSpawnInterval);
        writer.writeInt("initialAiSpawnDelay", initialAiSpawnDelay);
        writer.writeBoolean("displayChatDeathMessages", displayChatDeathMessages);
        writer.writeArray("zonePhases", zonePhases, ZonePhaseConfiguration::serialize);
        writer.write("worldConfig", worldConfiguration, GameWorldConfiguration::serialize);
    }

    public static BattleRoyaleGameConfiguration deserialize(DataReader<?> reader) {
        BattleRoyaleGameConfiguration configuration = new BattleRoyaleGameConfiguration();
        configuration.automaticGameJoining = reader.readBoolean("autoJoin", configuration.automaticGameJoining);
        configuration.teamSize = reader.readInt("teamSize", configuration.teamSize);
        configuration.playzoneGenerationDelay = reader.readInt("playzoneGenerationDelay", configuration.playzoneGenerationDelay);
        configuration.planeSpeed = reader.readFloat("planeSpeed", configuration.planeSpeed);
        configuration.planeFlightDelay = reader.readInt("planeFlightDelay", configuration.planeFlightDelay);
        configuration.planeFlightHeight = reader.readInt("planeFlightHeight", configuration.planeFlightHeight);
        configuration.entityCount = reader.readInt("entityCount", configuration.entityCount);
        configuration.allowAi = reader.readBoolean("allowAi", configuration.allowAi);
        configuration.allowAiCompanions = reader.readBoolean("allowAiCompanions", configuration.allowAiCompanions);
        configuration.aiSpawnInterval = reader.readInt("aiSpawnInterval", configuration.aiSpawnInterval);
        configuration.initialAiSpawnDelay = reader.readInt("initialAiSpawnDelay", configuration.initialAiSpawnDelay);
        configuration.displayChatDeathMessages = reader.readBoolean("displayChatDeathMessages", configuration.displayChatDeathMessages);
        configuration.zonePhases = reader.readArray("zonePhases", ZonePhaseConfiguration[]::new, ZonePhaseConfiguration::deserialize, configuration.zonePhases);
        configuration.worldConfiguration = reader.read("worldConfig", GameWorldConfiguration::deserialize, configuration.worldConfiguration);
        return configuration;
    }

    public static final class ZonePhaseConfiguration {

        private final float shrinkScale;
        private final float damage;
        private final int damageInterval;
        private final int shrinkTime;
        private final int shrinkDelay;
        private final AirdropTrigger airdropTrigger;

        public ZonePhaseConfiguration(float shrinkScale, float damage, int damageInterval, int shrinkTime, int shrinkDelay, AirdropTrigger trigger) {
            this.shrinkScale = MathHelper.clamp(shrinkScale, 0.0F, 1.0F);
            this.damage = Math.max(damage, 0.0F);
            this.damageInterval = Math.max(-1, damageInterval);
            this.shrinkTime = Math.max(0, shrinkTime);
            this.shrinkDelay = Math.max(0, shrinkDelay);
            this.airdropTrigger = trigger;
        }

        public AbstractDamagingPlayzone.DamageOptions getDamageOptions() {
            return new AbstractDamagingPlayzone.DamageOptions(damage, damageInterval);
        }

        public AirdropTrigger getAirdropTrigger() {
            return airdropTrigger;
        }

        public DynamicPlayzone.ResizeTarget createNewShrinkTarget(Playzone playzone, Random random) {
            Position2 min = playzone.getPositionMin(1.0F);
            Position2 max = playzone.getPositionMax(1.0F);
            double length = Math.min(Math.abs(min.getX() - max.getX()), Math.abs(min.getZ() - max.getZ()));
            double newLength = length * shrinkScale;
            double minX = min.getX();
            double minZ = min.getZ();
            double diff = length - newLength;
            double x = minX + random.nextDouble() * diff;
            double z = minZ + random.nextDouble() * diff;
            Position2 newMin = new Position2(x, z);
            Position2 newMax = new Position2(x + newLength, z + newLength);
            AbstractDamagingPlayzone.DamageOptions opt = getDamageOptions();
            return new DynamicPlayzone.ResizeTarget(newMin, newMax, opt, shrinkTime, shrinkDelay);
        }

        public void serialize(DataWriter<?> writer) {
            writer.writeFloat("scale", shrinkScale);
            writer.writeFloat("damage", damage);
            writer.writeInt("interval", damageInterval);
            writer.writeInt("shrinkTime", shrinkTime);
            writer.writeInt("shrinkDelay", shrinkDelay);
            writer.writeString("airdropTrigger", airdropTrigger.name());
        }

        public static ZonePhaseConfiguration deserialize(DataReader<?> reader) {
            float scale = reader.readFloat("scale", 0.5F);
            float damage = reader.readFloat("damage", 2.0F);
            int interval = reader.readInt("interval", 20);
            int shrinkTime = reader.readInt("shrinkTime", 600);
            int shrinkDelay = reader.readInt("shrinkDelay", 600);
            AirdropTrigger trigger;
            try {
                trigger = AirdropTrigger.valueOf(reader.readString("airdropTrigger", AirdropTrigger.ON_SHRINK_END.name()));
            } catch (IllegalArgumentException e) {
                trigger = AirdropTrigger.ON_SHRINK_END;
            }
            return new ZonePhaseConfiguration(scale, damage, interval, shrinkTime, shrinkDelay, trigger);
        }
    }

    public enum AirdropTrigger {

        NONE,
        ANY,
        ON_SHRINK_START,
        ON_SHRINK_END;

        public boolean shouldDrop(boolean completedResize) {
            return this == ANY || (this != NONE && ((completedResize && this == ON_SHRINK_END) || (!completedResize && this == ON_SHRINK_START)));
        }
    }
}
