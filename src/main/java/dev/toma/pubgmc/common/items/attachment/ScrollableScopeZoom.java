package dev.toma.pubgmc.common.items.attachment;

import dev.toma.configuration.api.type.DoubleType;
import dev.toma.pubgmc.DevUtil;
import net.minecraft.item.Item;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ScrollableScopeZoom implements ScopeZoom {

    private static final Map<Item, TickCache> SCROLL_AMOUNTS = new IdentityHashMap<>();
    private static final float ZOOM_STEP_SCALE = 1.0F / 12.0F;

    private final float minFov;
    private final float maxFov;
    private final Supplier<Float> minSensitivity;
    private final Supplier<Float> maxSensitivity;

    public ScrollableScopeZoom(float minFov, float maxFov, DoubleType minSensitivity, DoubleType maxSensitivity) {
        this.minFov = minFov;
        this.maxFov = maxFov;
        this.minSensitivity = minSensitivity::getAsFloat;
        this.maxSensitivity = maxSensitivity::getAsFloat;
    }

    public static void tick() {
        SCROLL_AMOUNTS.values().forEach(TickCache::tick);
    }

    @Override
    public float getCurrentZoom(Item item, float deltaTicks) {
        TickCache cache = getOrBuildCache(item);
        float amount = cache.getInterpolatedValue(deltaTicks);
        return DevUtil.lerp(this.maxFov, this.minFov, amount);
    }

    @Override
    public float getSensitivity(Item item) {
        TickCache cache = getOrBuildCache(item);
        return DevUtil.lerp(this.maxSensitivity.get(), this.minSensitivity.get(), cache.getDirectValue());
    }

    @Override
    public boolean zoomOut(Item item) {
        TickCache cache = getOrBuildCache(item);
        float amount = cache.getDirectValue();
        if (amount >= 1.0F) {
            return false;
        }
        cache.update(Math.min(amount + ZOOM_STEP_SCALE, 1.0F));
        return true;
    }

    @Override
    public boolean zoomIn(Item item) {
        TickCache cache = getOrBuildCache(item);
        float amount = cache.getDirectValue();
        if (amount <= 0.0F) {
            return false;
        }
        cache.update(Math.max(amount - ZOOM_STEP_SCALE, 0.0F));
        return true;
    }

    @Override
    public boolean hasMouseScrollOverrides() {
        return true;
    }

    @Override
    public String toString() {
        return this.minFov + "-" + this.maxFov;
    }

    private TickCache getOrBuildCache(Item item) {
        return SCROLL_AMOUNTS.computeIfAbsent(item, k -> new TickCache(0.0F, 0.0F));
    }

    private static final class TickCache {

        private float current;
        private float previous;

        public TickCache(float current, float previous) {
            this.current = current;
            this.previous = previous;
        }

        public void update(float newValue) {
            this.current = newValue;
        }

        public float getInterpolatedValue(float delta) {
            return DevUtil.lerp(this.current, this.previous, delta);
        }

        public float getDirectValue() {
            return this.getInterpolatedValue(1.0F);
        }

        public void tick() {
            this.previous = this.current;
        }
    }
}
