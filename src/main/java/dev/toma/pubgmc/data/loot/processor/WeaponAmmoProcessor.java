package dev.toma.pubgmc.data.loot.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.game.loot.LootProcessor;
import dev.toma.pubgmc.api.game.loot.LootProcessorSerializer;
import dev.toma.pubgmc.api.game.loot.LootProcessorType;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.List;
import java.util.Random;

public class WeaponAmmoProcessor implements LootProcessor {

    private final float minPct;
    private final float maxPct;

    public WeaponAmmoProcessor(float minPct, float maxPct) {
        this.minPct = minPct;
        this.maxPct = maxPct;
    }

    public WeaponAmmoProcessor(float pct) {
        this(pct, pct);
    }

    @Override
    public void processItems(LootGenerationContext context, List<ItemStack> generated) {
        Random random = context.getWorld().rand;
        for (ItemStack stack : generated) {
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                float f = minPct == maxPct ? minPct : minPct + (maxPct - minPct) * random.nextFloat();
                int cap = gun.getWeaponAmmoLimit(stack);
                int val = (int) (cap * f);
                gun.setAmmo(stack, val);
            }
        }
    }

    @Override
    public LootProcessorType<?> getType() {
        return LootProcessors.WEAPON_AMMO_PROCESOR;
    }

    public static final class Serializer implements LootProcessorSerializer<WeaponAmmoProcessor> {

        @Override
        public void serialize(JsonObject object, WeaponAmmoProcessor processor) {
            object.addProperty("min", processor.minPct);
            object.addProperty("max", processor.maxPct);
        }

        @Override
        public WeaponAmmoProcessor parse(JsonObject object) throws JsonParseException {
            float min = JsonUtils.getFloat(object, "min");
            float max = JsonUtils.getFloat(object, "max");
            if (min > max) {
                throw new JsonSyntaxException("Min value cannot be larger than max value");
            }
            return new WeaponAmmoProcessor(min, max);
        }
    }
}
