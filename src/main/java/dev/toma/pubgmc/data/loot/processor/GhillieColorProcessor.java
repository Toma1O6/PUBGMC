package dev.toma.pubgmc.data.loot.processor;

import com.google.gson.*;
import dev.toma.pubgmc.api.game.loot.LootProcessor;
import dev.toma.pubgmc.api.game.loot.LootProcessorSerializer;
import dev.toma.pubgmc.api.game.loot.LootProcessorType;
import dev.toma.pubgmc.common.items.equipment.ItemGhillie;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GhillieColorProcessor implements LootProcessor {

    private final ColorProvider colorProvider;
    private final int[] colorList;

    public GhillieColorProcessor(ColorProvider colorProvider, int[] colorList) {
        this.colorProvider = colorProvider;
        this.colorList = colorList;
    }

    @Override
    public void processItems(LootGenerationContext context, List<ItemStack> generated) {
        int color;
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (colorProvider == ColorProvider.BIOME) {
            Biome biome = world.getBiome(pos);
            if (biome.isSnowyBiome()) {
                color = 0xE8F2F2;
            } else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
                color = 0xE2D6AA;
            } else {
                float temperature = MathHelper.clamp(biome.getTemperature(pos), 0.0F, 1.0F);
                float humidity = MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F);
                color = FoliageColor.getColor(temperature, humidity);
            }
        } else {
            Random random = world.rand;
            color = colorList[random.nextInt(colorList.length)];
        }
        if (generated.isEmpty()) {
            return;
        }
        ItemStack first = generated.get(0);
        ItemGhillie.setFoliageColor(first, color);
    }

    @Override
    public LootProcessorType<?> getType() {
        return LootProcessors.GHILLIE_COLOR_PROCESSOR;
    }

    public enum ColorProvider {

        BIOME,
        COLOR_LIST
    }

    public static final class FoliageColor {

        static final int[] foliage = new int[65536];

        static int getColor(float temperature, float humidity) {
            humidity = humidity * temperature;
            int i = (int)((1.0D - temperature) * 255.0D);
            int j = (int)((1.0D - humidity) * 255.0D);
            return foliage[j << 8 | i];
        }

        static {
            for (int y = 0; y < 256; y++) {
                for (int x = 0; x < 256; x++) {
                    boolean warm = x < y + 1;
                    if (warm) {
                        float f0 = y / 255.0F;
                        float f1 = x  / (y + 1.0F);
                        float f2 = f0 * f1;
                        int result = lerp(f0, f1, f2, 0x1ABF00, 0xAEA42A, 0x60A17B);
                        foliage[y << 8 | x] = result;
                    } else {
                        float f0 = y / 255.0F;
                        float f1 = y  / (float) x;
                        float f2 = f0 * f1;
                        int result = lerp(f0, f1, f2, 0x6B9793, 0x749A3E, 0x1CA449);
                        foliage[y << 8 | x] = result;
                    }
                }
            }
        }

        static int lerp(float f0, float f1, float f2, int c0, int c1, int c2) {
            int r0 = (c0 >> 16) & 255;
            int g0 = (c0 >>  8) & 255;
            int b0 = c0 & 255;
            int r1 = (c1 >> 16) & 255;
            int g1 = (c1 >>  8) & 255;
            int b1 = c1 & 255;
            int r2 = (c2 >> 16) & 255;
            int g2 = (c2 >>  8) & 255;
            int b2 = c2 & 255;
            int red = mix(mix(r0, r1, f0), mix(r1, r2, f1), f2);
            int green = mix(mix(g0, g1, f0), mix(g1, g2, f1), f2);
            int blue = mix(mix(b0, b1, f0), mix(b1, b2, f1), f2);
            return red << 16 | green << 8 | blue;
        }

        public static int mix(int a, int b, float f) {
            int m = (int) ((1.0F - f) * a);
            int n = (int) (f * b);
            return m + n;
        }
    }

    public static final class Serializer implements LootProcessorSerializer<GhillieColorProcessor> {

        @Override
        public GhillieColorProcessor parse(JsonObject object) throws JsonParseException {
            String type = JsonUtils.getString(object, "colorProviderType").toUpperCase();
            ColorProvider colorProvider;
            try {
                colorProvider = ColorProvider.valueOf(type);
            } catch (IllegalArgumentException e) {
                throw new JsonSyntaxException("Unknown color provider type: '" + type + "'. Use one of: [" + Arrays.stream(ColorProvider.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", ")) + "]");
            }
            int[] colorList = new int[0];
            if (colorProvider == ColorProvider.COLOR_LIST) {
                JsonArray values = JsonUtils.getJsonArray(object, "colors");
                if (values.size() == 0) {
                    throw new JsonSyntaxException("At least one ghillie color must be defined");
                }
                colorList = new int[values.size()];
                for (int i = 0; i < values.size(); i++) {
                    JsonElement element = values.get(i);
                    colorList[i] = element.getAsInt();
                }
            }
            return new GhillieColorProcessor(colorProvider, colorList);
        }

        @Override
        public void serialize(JsonObject object, GhillieColorProcessor processor) {
            object.addProperty("colorProviderType", processor.colorProvider.name());
            if (processor.colorProvider == ColorProvider.COLOR_LIST) {
                JsonArray array = new JsonArray();
                for (int i : processor.colorList) {
                    array.add(i);
                }
                object.add("colors", array);
            }
        }
    }
}
