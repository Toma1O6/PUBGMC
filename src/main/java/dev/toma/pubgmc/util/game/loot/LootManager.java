package dev.toma.pubgmc.util.game.loot;

import dev.toma.pubgmc.common.capability.world.IWorldData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.math.IWeight;
import dev.toma.pubgmc.util.math.WeightedRandomUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO remove and replace by data driven loot system
@Deprecated
public class LootManager {

    private static final HashMap<LootType, List<LootEntry>> MAP = new HashMap<>();
    private final World world;
    private final LootOptions loot;

    public LootManager(World world) {
        this.world = world;
        IWorldData worldData = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        this.loot = LootOptions.getCurrent(worldData);
    }

    /**
     * @param lootCategory - loot category
     * @param allowedTypes - Array of allowed gun types, CANNOT BE NULL when lootCategory == LootType.GUN!!
     * @param flag - 0 = Non-airdrop weapons; 1 = Allow airdrop weapons; 2 = Only airdrop weapons
     * @return - random weapon
     */
    public static Item getRandomObject(LootType lootCategory, @Nullable GunBase.GunType[] allowedTypes, byte flag) {
        List<LootEntry> entries = new ArrayList<>(MAP.get(lootCategory));
        if(allowedTypes != null) {
            entries = entries.stream().filter(e -> e.stack.getItem() instanceof GunBase && PUBGMCUtil.contains(((GunBase) e.stack.getItem()).getGunType(), allowedTypes)).collect(Collectors.toList());
        }
        entries = entries.stream().filter(e -> flag == 0 ? !e.isSpecialLoot : flag == 2 ? e.isSpecialLoot : e != null).collect(Collectors.toList());
        return WeightedRandomUtil.getRandom(entries).stack.getItem();
    }

    public static void register(LootType type, LootEntry entry) {
        MAP.get(type).add(entry);
    }

    public static void register(LootType type, Item item, int weight) {
        MAP.get(type).add(new LootEntry(item, weight));
    }

    public static class LootEntry implements IWeight {

        private final ItemStack stack;
        public final int weight;
        public final boolean isSpecialLoot;

        public LootEntry(final Item item, final int weight) {
            this(item, weight, false);
        }

        public LootEntry(final Item item, final int weight, final boolean isSpecialLoot) {
            this(item, weight, isSpecialLoot, 1);
        }

        public LootEntry(final Item item, final int weight, final boolean isSpecialLoot, final int amount) {
            this.stack = new ItemStack(item, amount);
            this.weight = weight;
            this.isSpecialLoot = isSpecialLoot;
        }

        public ItemStack get() {
            return stack.copy();
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "LootEntry:[item=" + stack.toString() + ",weight=" + weight + ",specialLoot=" + isSpecialLoot + "]";
        }
    }

    static {
        for (LootType type : LootType.values()) {
            MAP.put(type, new ArrayList<>());
        }
    }
}
