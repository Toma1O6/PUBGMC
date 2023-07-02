package dev.toma.pubgmc.api.game;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.util.GameRuleStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class GameWorldConfiguration {

    public boolean doDaylightCycle = false;
    public int daytime;
    public boolean doWeatherCycle = false;
    public WeatherType weatherType = WeatherType.CLEAR;
    public int weatherDuration = 1000000;

    public void correct() {
        daytime = MathHelper.clamp(daytime, 0, 23999);
        weatherDuration = MathHelper.clamp(weatherDuration, 0, 1000000);
        weatherType = weatherType == null ? WeatherType.CLEAR : weatherType;
    }

    public void apply(WorldServer worldServer, GameRuleStorage storage) {
        MinecraftServer server = worldServer.getMinecraftServer();
        storage.storeValueAndSet(worldServer, "doDaylightCycle", Boolean.toString(doDaylightCycle));
        storage.storeValueAndSet(worldServer, "doWeatherCycle", Boolean.toString(doWeatherCycle));
        for (World world : server.worlds) {
            world.setWorldTime(daytime);
        }
        weatherType.apply(worldServer, weatherDuration);
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("daylightCycle", doDaylightCycle);
        nbt.setBoolean("weatherCycle", doWeatherCycle);
        nbt.setInteger("daytime", daytime);
        nbt.setInteger("weatherTime", weatherDuration);
        nbt.setInteger("weather", weatherType.ordinal());
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        doDaylightCycle = nbt.getBoolean("daylightCycle");
        doWeatherCycle = nbt.getBoolean("weatherCycle");
        daytime = nbt.getInteger("daytime");
        weatherDuration = nbt.getInteger("weatherTime");
        weatherType = WeatherType.values()[nbt.getInteger("weather") % WeatherType.values().length];
    }

    public JsonObject jsonSerialize() {
        JsonObject object = new JsonObject();
        object.addProperty("daylightCycle", doDaylightCycle);
        object.addProperty("weatherCycle", doWeatherCycle);
        object.addProperty("dayTime", daytime);
        object.addProperty("weatherDuration", weatherDuration);
        object.addProperty("weatherType", weatherType.name());
        return object;
    }

    public void jsonDeserialize(JsonObject object) {
        doDaylightCycle = JsonUtils.getBoolean(object, "daylightCycle", false);
        doWeatherCycle = JsonUtils.getBoolean(object, "weatherCycle", false);
        daytime = JsonUtils.getInt(object, "dayTime", 0);
        weatherDuration = JsonUtils.getInt(object, "weatherDuration", 1000000);
        try {
            weatherType = WeatherType.valueOf(JsonUtils.getString(object, "weatherType", "CLEAR"));
        } catch (IllegalArgumentException e) {
            weatherType = WeatherType.CLEAR;
        }
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
