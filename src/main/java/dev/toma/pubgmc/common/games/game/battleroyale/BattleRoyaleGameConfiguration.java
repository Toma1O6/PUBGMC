package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.area.AbstractDamagingArea;
import dev.toma.pubgmc.common.games.area.DynamicGameArea;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class BattleRoyaleGameConfiguration implements GameConfiguration {

    public boolean automaticGameJoining = true;
    public int teamSize = 1;
    public int areaGenerationDelay = 2400;
    public ZonePhaseConfiguration[] zonePhases = {
            new ZonePhaseConfiguration(0.65F, 1.0F, 60, 2400, 6000),
            new ZonePhaseConfiguration(0.75F, 1.0F, 40, 1800, 3600),
            new ZonePhaseConfiguration(0.75F, 1.0F, 20, 1200, 2400),
            new ZonePhaseConfiguration(0.50F, 2.0F, 20,  800, 1800),
            new ZonePhaseConfiguration(0.25F, 3.5F, 20,  450, 1200),
            new ZonePhaseConfiguration(0.00F, 5.0F, 20, 1200, 3600)
    };

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("autoJoin", automaticGameJoining);
        nbt.setInteger("teamSize", teamSize);
        NBTTagList zones = new NBTTagList();
        for (ZonePhaseConfiguration configuration : zonePhases) {
            zones.appendTag(configuration.serialize());
        }
        nbt.setTag("zonePhases", zones);
        return nbt;
    }

    public static BattleRoyaleGameConfiguration deserialize(NBTTagCompound nbt) {
        BattleRoyaleGameConfiguration configuration = new BattleRoyaleGameConfiguration();
        configuration.automaticGameJoining = nbt.getBoolean("autoJoin");
        configuration.teamSize = nbt.getInteger("teamSize");
        NBTTagList zones = nbt.getTagList("zonePhases", Constants.NBT.TAG_COMPOUND);
        configuration.zonePhases = new ZonePhaseConfiguration[zones.tagCount()];
        for (int i = 0; i < zones.tagCount(); i++) {
            NBTTagCompound zoneTag = zones.getCompoundTagAt(i);
            configuration.zonePhases[i] = ZonePhaseConfiguration.deserialize(zoneTag);
        }
        return configuration;
    }

    public static final class ZonePhaseConfiguration {

        private final float shrinkScale;
        private final float damage;
        private final int damageInterval;
        private final int shrinkTime;
        private final int shrinkDelay;

        public ZonePhaseConfiguration(float shrinkScale, float damage, int damageInterval, int shrinkTime, int shrinkDelay) {
            this.shrinkScale = shrinkScale;
            this.damage = damage;
            this.damageInterval = damageInterval;
            this.shrinkTime = shrinkTime;
            this.shrinkDelay = shrinkDelay;
        }

        public AbstractDamagingArea.DamageOptions getDamageOptions() {
            return new AbstractDamagingArea.DamageOptions(damage, damageInterval);
        }

        public DynamicGameArea.AreaTarget createNewShrinkTarget(GameArea area, Random random) {
            Position2 min = area.getPositionMin(1.0F);
            Position2 max = area.getPositionMax(1.0F);
            double length = Math.min(Math.abs(min.getX() - max.getX()), Math.abs(min.getZ() - max.getZ()));
            double newLength = length * shrinkScale;
            double minX = min.getX();
            double minZ = min.getZ();
            double diff = length - newLength;
            double x = minX + random.nextDouble() * diff;
            double z = minZ + random.nextDouble() * diff;
            Position2 newMin = new Position2(x, z);
            Position2 newMax = new Position2(x + newLength, z + newLength);
            AbstractDamagingArea.DamageOptions opt = getDamageOptions();
            return new DynamicGameArea.AreaTarget(newMin, newMax, opt, shrinkTime, shrinkDelay);
        }

        public NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setFloat("scale", shrinkScale);
            nbt.setFloat("damage", damage);
            nbt.setInteger("interval", damageInterval);
            nbt.setInteger("shrinkTime", shrinkTime);
            nbt.setInteger("shrinkDelay", shrinkDelay);
            return nbt;
        }

        public static ZonePhaseConfiguration deserialize(NBTTagCompound nbt) {
            float scale = nbt.getFloat("scale");
            float damage = nbt.getFloat("damage");
            int interval = nbt.getInteger("interval");
            int shrinkTime = nbt.getInteger("shrinkTime");
            int shrinkDelay = nbt.getInteger("shrinkDelay");
            return new ZonePhaseConfiguration(scale, damage, interval, shrinkTime, shrinkDelay);
        }
    }
}
