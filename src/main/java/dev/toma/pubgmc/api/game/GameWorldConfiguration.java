package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class GameWorldConfiguration {

    public boolean doDaylightCycle = false;
    public int daytime = 5000;
    public boolean doWeatherCycle = false;
    public WeatherType weatherType = WeatherType.CLEAR;
    public int weatherDuration = 1000000;
    public boolean doMobSpawning = false;
    public static boolean adventureMode = true;

    public void correct() {
        daytime = MathHelper.clamp(daytime, 0, 23999);
        weatherDuration = MathHelper.clamp(weatherDuration, 0, 1000000);
        weatherType = weatherType == null ? WeatherType.CLEAR : weatherType;
    }

    public void apply(WorldServer worldServer, GameRuleStorage storage) {
        MinecraftServer server = worldServer.getMinecraftServer();
        storage.storeValueAndSet(worldServer, "doDaylightCycle", Boolean.toString(doDaylightCycle));
        storage.storeValueAndSet(worldServer, "doWeatherCycle", Boolean.toString(doWeatherCycle));
        storage.storeValueAndSet(worldServer, "doMobSpawning", Boolean.toString(doMobSpawning));
        for (World world : server.worlds) {
            world.setWorldTime(daytime);
        }
        weatherType.apply(worldServer, weatherDuration);
    }

    public void serialize(DataWriter<?> writer) {
        writer.writeBoolean("daylightCycle", doDaylightCycle);
        writer.writeBoolean("weatherCycle", doWeatherCycle);
        writer.writeInt("daytime", daytime);
        writer.writeInt("weatherTime", weatherDuration);
        writer.writeEnum("weather", weatherType);
        writer.writeBoolean("mobSpawning", doMobSpawning);
        writer.writeBoolean("adventureMode", adventureMode);
    }

    public static GameWorldConfiguration deserialize(DataReader<?> reader) {
        GameWorldConfiguration cfg = new GameWorldConfiguration();
        cfg.doDaylightCycle = reader.readBoolean("daylightCycle", cfg.doDaylightCycle);
        cfg.doWeatherCycle = reader.readBoolean("weatherCycle", cfg.doWeatherCycle);
        cfg.daytime = reader.readInt("daytime", cfg.daytime);
        cfg.weatherDuration = reader.readInt("weatherTime", cfg.weatherDuration);
        cfg.weatherType = reader.readEnum("weather", WeatherType.class, cfg.weatherType);
        cfg.doMobSpawning = reader.readBoolean("mobSpawning", cfg.doMobSpawning);
        adventureMode = reader.readBoolean("adventureMode", adventureMode);
        return cfg;
    }

    public enum WeatherType {

        CLEAR((info, time) -> {
            info.setCleanWeatherTime(time);
            info.setRainTime(0);
            info.setThunderTime(0);
            info.setRaining(false);
            info.setThundering(false);
        }),
        RAIN((info, time) -> {
            info.setCleanWeatherTime(0);
            info.setRainTime(time);
            info.setThunderTime(0);
            info.setRaining(true);
            info.setThundering(false);
        }),
        THUNDER((info, time) -> {
            info.setCleanWeatherTime(0);
            info.setRainTime(0);
            info.setThunderTime(time);
            info.setRaining(false);
            info.setThundering(true);
        });

        private final WeatherFunction function;

        WeatherType(WeatherFunction function) {
            this.function = function;
        }

        public void apply(World world, int duration) {
            WorldInfo worldInfo = world.getWorldInfo();
            function.applyWeatherAttributes(worldInfo, duration);
        }

        @FunctionalInterface
        public interface WeatherFunction {
            void applyWeatherAttributes(WorldInfo info, int duration);
        }
    }
}
