package com.toma.pubgmc.util.game.loot;

import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.math.IWeight;
import com.toma.pubgmc.util.math.WeightedRandom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * WIP
 */
public class LootManager {

    private static final HashMap<LootType, List<LootEntry>> MAP = null;
    private final World world;
    private final LootOptions loot;

    public LootManager(World world) {
        this.world = world;
        IWorldData worldData = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        this.loot = LootOptions.getCurrent(worldData);
    }

    public <T extends TileEntity & ILootSpawner> void generateLootIn(T spawner) {
        NonNullList<ItemStack> inventory = spawner.getInventory();
        fillInventory(inventory, spawner.isAirdropContainer());
        IBlockState state = world.getBlockState(spawner.getPos());
        spawner.getWorld().notifyBlockUpdate(spawner.getPos(), state, state, 3);
    }

    public void fillInventory(NonNullList<ItemStack> inventory, boolean airdropStyle) {
        Random random = new Random();
        if(airdropStyle) {
            // TODO handle loot in
            return;
        }
        int maxIndex = inventory.size();
        int currentIndex = 0;
        int spawnAttempts = (int)loot.chanceModifier + 1;
        while (spawnAttempts > 0 && currentIndex < maxIndex) {
            --spawnAttempts;
            LootType type = WeightedRandom.getRandom(MAP.keySet());
            List<LootEntry> entryList = new ArrayList<>();
            Collections.copy(entryList, MAP.get(type));
            if (!loot.isSpecialLoot) {
                entryList = entryList.stream().filter(lootEntry -> !lootEntry.isSpecialLoot).collect(Collectors.toList());
            }
            boolean flag = type == LootType.GUN;
            if(flag) {
                entryList = entryList.stream().filter(lootEntry -> lootEntry.item instanceof GunBase && PUBGMCUtil.contains(((GunBase) lootEntry.item).getGunType(), loot.validWeaponTypes)).collect(Collectors.toList());
            }
            int amount = type == LootType.AMMO ? ConfigPMC.items().ammoLimit : 1;
            inventory.set(currentIndex, new ItemStack(WeightedRandom.getRandom(entryList, WeightedRandom.getTotalWeight(entryList), loot.chanceModifier).item));
            ++currentIndex;
            if(flag && loot.genAmmo) {
                AmmoType ammoType = ((GunBase) inventory.get(currentIndex).getItem()).getAmmoType();
                int ammoSlots = maxIndex - currentIndex < 3 ? maxIndex - currentIndex : 3;
                for(int i = 0; i < ammoSlots; i++) {
                    inventory.set(currentIndex, new ItemStack(ammoType.ammo(), loot.randomAmmoGen ? random.nextInt(amount) : amount));
                    ++currentIndex;
                }
            }
        }
    }

    /**
     * @param lootCategory - loot category
     * @param allowedTypes - Array of allowed gun types, CANNOT BE NULL when lootCategory == LootType.GUN!!
     * @param flag - 0 = Non-airdrop weapons; 1 = Allow airdrop weapons; 2 = Only airdrop weapons
     * @return - random weapon
     */
    public static Item getRandomObject(LootType lootCategory, @Nullable GunBase.GunType[] allowedTypes, byte flag) {
        List<LootEntry> entries = new ArrayList<>();
        Collections.copy(entries, MAP.get(lootCategory));
        entries = entries.stream().filter(e -> flag == 0 ? !e.isSpecialLoot : flag == 2 ? e.isSpecialLoot : e != null).collect(Collectors.toList());
        return WeightedRandom.getRandom(entries).item;
    }

    public static void register(LootType type, LootEntry entry) {
        MAP.get(type).add(entry);
    }

    public static void register(LootType type, Item item, int weight) {
        MAP.get(type).add(new LootEntry(item, weight));
    }

    public static class LootEntry implements IWeight {

        public final Item item;
        public final int weight;
        public final boolean isSpecialLoot;

        public LootEntry(final Item item, final int weight) {
            this(item, weight, false);
        }

        public LootEntry(final Item item, final int weight, final boolean isSpecialLoot) {
            this.item = item;
            this.weight = weight;
            this.isSpecialLoot = isSpecialLoot;
        }

        @Override
        public int getWeight() {
            return weight;
        }
    }

    static {
        for (LootType type : LootType.values()) {
            MAP.put(type, new ArrayList<>());
        }
    }
}
