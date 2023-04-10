package dev.toma.pubgmc.data.loot.processor;

import com.google.gson.*;
import dev.toma.pubgmc.common.items.equipment.ItemGhillie;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

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
            color = biome.getFoliageColorAtPos(pos);
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
