package com.toma.pubgmc.util.game.loot;

import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.items.armor.ItemGhillie;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.network.sp.PacketSyncTileEntity;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.math.IWeight;
import com.toma.pubgmc.util.math.WeightedRandom;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class LootManager {

    private static final HashMap<LootType, List<LootEntry>> MAP = new HashMap<>();
    private final World world;
    private final LootOptions loot;

    public LootManager(World world) {
        this.world = world;
        IWorldData worldData = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        this.loot = LootOptions.getCurrent(worldData);
    }

    public <T extends TileEntity & ILootSpawner> void generateLootIn(T spawner, int attempts) {
        NonNullList<ItemStack> inventory = spawner.getInventory();
        this.fillInventory(inventory, spawner.isAirdropContainer(), attempts);
        PacketHandler.sendToAllClients(new PacketSyncTileEntity(spawner.writeToNBT(new NBTTagCompound()), spawner.getPos()));
    }

    public void fillInventory(NonNullList<ItemStack> inventory, boolean airdropStyle, int generatorRuns) {
        inventory.clear();
        Random random = new Random();
        if(airdropStyle) {
            int lastIndex = 0;
            for(int i = 0; i < generatorRuns; i++) {
                lastIndex = generateAirdropLoot(inventory, lastIndex, random);
            }
            return;
        }
        int maxIndex = inventory.size();
        int currentIndex = 0;
        while (generatorRuns > 0 && currentIndex < maxIndex) {
            --generatorRuns;
            LootType type = WeightedRandom.getRandom(MAP.keySet());
            List<LootEntry> entryList = new ArrayList<>(MAP.get(type));
            if (!loot.isSpecialLoot) {
                entryList = entryList.stream().filter(lootEntry -> !lootEntry.isSpecialLoot).collect(Collectors.toList());
            }
            boolean flag = type == LootType.GUN;
            if(flag) {
                entryList = entryList.stream().filter(lootEntry -> lootEntry.stack.getItem() instanceof GunBase && PUBGMCUtil.contains(((GunBase) lootEntry.stack.getItem()).getGunType(), loot.validWeaponTypes)).collect(Collectors.toList());
            }
            inventory.set(currentIndex, WeightedRandom.getRandom(entryList, WeightedRandom.getTotalWeight(entryList), loot.chanceModifier).get());
            ++currentIndex;
            if(flag && loot.genAmmo) {
                AmmoType ammoType = ((GunBase) inventory.get(currentIndex - 1).getItem()).getAmmoType();
                int ammoSlots = maxIndex - currentIndex < 3 ? maxIndex - currentIndex : random.nextInt(3) + 1;
                for(int i = 0; i < ammoSlots; i++) {
                    inventory.set(currentIndex, new ItemStack(ammoType.ammo(), loot.randomAmmoGen ? random.nextInt(30) + 1 : 30));
                    ++currentIndex;
                }
            }
        }
    }

    private int generateAirdropLoot(NonNullList<ItemStack> inventory, int startingIndex, Random rand) {
        int i = startingIndex;
        inventory.set(i, new ItemStack(getRandomObject(LootType.GUN, GunBase.GunType.values(), (byte) 2)));
        ++i;
        int j = 1 + rand.nextInt(3);
        ItemStack stack = ((GunBase) inventory.get(i-1).getItem()).getAmmoType().ammoStack();
        stack.setCount(stack.getItem() == PMCRegistry.PMCItems.AMMO_300M ? 5 : 30);
        for(int k = 0; k < j; k++) {
            inventory.set(i, stack.copy());
            i++;
        }
        inventory.set(i, new ItemStack(PMCRegistry.PMCItems.ARMOR3HELMET));
        i++;
        inventory.set(i, new ItemStack(PMCRegistry.PMCItems.ARMOR3BODY));
        i++;
        inventory.set(i, new ItemStack(PMCRegistry.PMCItems.BACKPACK3));
        i++;
        ItemStack stack1 = new ItemStack(getRandomObject(LootType.ATTACHMENT, null, (byte) 1));
        inventory.set(i, stack1);
        i++;
        if(rand.nextInt(10) < 5) {
            Integer[] ints = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null).getGhillieSuitsColorVariants().toArray(new Integer[0]);
            int color = ints.length == 0 ? ItemGhillie.DEFAULT_COLOR : ints[rand.nextInt(ints.length)];
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("ghillieColor", color);
            ItemStack stack2 = new ItemStack(PMCRegistry.PMCItems.GHILLIE_SUIT);
            stack2.setTagCompound(nbt);
            inventory.set(i, stack2);
            i++;
        }
        return i;
    }

    // TODO create loot category types
    /**
     * @param lootCategory - loot category
     * @param allowedTypes - Array of allowed gun types, CANNOT BE NULL when lootCategory == LootType.GUN!!
     * @param flag - 0 = Non-airdrop weapons; 1 = Allow airdrop weapons; 2 = Only airdrop weapons
     * @return - random weapon
     */
    public static Item getRandomObject(LootType lootCategory, @Nullable GunBase.GunType[] allowedTypes, byte flag) {
        List<LootEntry> entries = new ArrayList<>(MAP.get(lootCategory));
        entries = entries.stream().filter(e -> flag == 0 ? !e.isSpecialLoot : flag == 2 ? e.isSpecialLoot : e != null).collect(Collectors.toList());
        return WeightedRandom.getRandom(entries).stack.getItem();
    }

    public static void register(LootType type, LootEntry entry) {
        MAP.get(type).add(entry);
    }

    public static void register(LootType type, Item item, int weight) {
        MAP.get(type).add(new LootEntry(item, weight));
    }

    public static Map<LootType, List<LootEntry>> getEntryMap() {
        return MAP;
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
