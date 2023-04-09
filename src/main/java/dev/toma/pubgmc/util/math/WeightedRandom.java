package dev.toma.pubgmc.util.math;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class WeightedRandom<T> {

    private final Random random = new Random();
    private final List<T> values;
    private final ToIntFunction<T> weightProvider;
    private final int sum;

    public WeightedRandom(ToIntFunction<T> weightProvider, List<T> values) {
        this.values = values;
        this.weightProvider = weightProvider;
        this.sum = values.stream()
                .mapToInt(weightProvider)
                .sum();
    }

    public T getElement() {
        int value = random.nextInt(sum);
        for (int i = values.size() - 1; i >= 0; i--) {
            T element = values.get(i);
            value -= weightProvider.applyAsInt(element);
            if (value < 0) {
                return element;
            }
        }
        return null;
    }

    public List<T> getValues() {
        return values;
    }

    public static final class Entry<T> {

        private final int weight;
        private final T element;

        public Entry(int weight, T element) {
            this.weight = weight;
            this.element = element;
        }

        public int getWeight() {
            return weight;
        }

        public T getElement() {
            return element;
        }

        public JsonObject serializeJson(Function<T, JsonObject> serializer) {
            JsonObject object = new JsonObject();
            object.addProperty("weight", weight);
            object.add("entry", serializer.apply(element));
            return object;
        }

        public static <T> Entry<T> deserializeJson(JsonObject object, Function<JsonObject, T> deserializer) {
            int weight = JsonUtils.getInt(object, "weight", 1);
            T t = deserializer.apply(JsonUtils.getJsonObject(object, "entry"));
            return new Entry<>(weight, t);
        }
    }
}
