package dev.toma.pubgmc.data.loot;

import dev.toma.pubgmc.api.game.loot.LootProcessor;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.data.loot.processor.AmmoPackProcessor;
import dev.toma.pubgmc.data.loot.processor.GhillieColorProcessor;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.math.WeightedRandom;
import net.minecraft.item.ItemStack;

import java.util.*;

public final class LootConfigurations {

    public static final String[] LOOT_SPAWNER = {
            "loot_spawner_t1",
            "loot_spawner_t2",
            "loot_spawner_t3"
    };
    public static final String AIRDROP = "airdrop";
    public static final String AIRDROP_LARGE = "airdrop_large";

    static Map<String, LootConfiguration> registerDefaultLootConfigurations(RegistrationHandler handler, boolean force) {
        Map<String, LootConfiguration> created = new HashMap<>();
        generateLootTier1(handler, created, force);
        generateLootTier2(handler, created, force);
        generateLootTier3(handler, created, force);
        generateAirdropDefault(handler, created, force);
        generateAirdropLarge(handler, created, force);
        return created;
    }

    private static void generateLootTier1(RegistrationHandler handler, Map<String, LootConfiguration> defaultConfigMap, boolean force) {
        LootProvider pool = new CountLootProvider(0, 2, new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(7, getMeds(10, 7, 5, 1)),
                new WeightedRandom.Entry<>(10, getArmor(30, 17, 3)),
                new WeightedRandom.Entry<>(15, getAttachments(40, 15, 2)),
                new WeightedRandom.Entry<>(10, getWeapons(30, 45, 30, 12, 6, 3, 0)),
                new WeightedRandom.Entry<>(5, getRandomAmmoPack()),
                new WeightedRandom.Entry<>(5, getThrowables()),
                new WeightedRandom.Entry<>(5, getOtherLoot())
        )));
        LootConfiguration configuration = new LootConfiguration(Collections.emptyMap(), pool);
        register(handler, defaultConfigMap, LOOT_SPAWNER[0], configuration, force);
    }

    private static void generateLootTier2(RegistrationHandler handler, Map<String, LootConfiguration> defaultConfigMap, boolean force) {
        LootProvider pool = new CountLootProvider(1, 3, new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(10, getMeds(6, 8, 5, 2)),
                new WeightedRandom.Entry<>(15, getArmor(20, 17, 6)),
                new WeightedRandom.Entry<>(15, getAttachments(25, 30, 8)),
                new WeightedRandom.Entry<>(15, getWeapons(20, 40, 30, 60, 45, 30, 1)),
                new WeightedRandom.Entry<>(1, getRandomAmmoPack()),
                new WeightedRandom.Entry<>(5, getThrowables()),
                new WeightedRandom.Entry<>(1, getOtherLoot())
        )));
        LootConfiguration configuration = new LootConfiguration(Collections.emptyMap(), pool);
        register(handler, defaultConfigMap, LOOT_SPAWNER[1], configuration, force);
    }

    private static void generateLootTier3(RegistrationHandler handler, Map<String, LootConfiguration> defaultConfigMap, boolean force) {
        LootProvider pool = new CountLootProvider(2, 4, new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(8, getMeds(2, 10, 10, 5)),
                new WeightedRandom.Entry<>(5, getArmor(5, 22, 10)),
                new WeightedRandom.Entry<>(7, getAttachments(5, 25, 15)),
                new WeightedRandom.Entry<>(12, getWeapons(5, 10, 10, 75, 55, 40, 1)),
                new WeightedRandom.Entry<>(0, getRandomAmmoPack()),
                new WeightedRandom.Entry<>(5, getThrowables()),
                new WeightedRandom.Entry<>(1, getOtherLoot())
        )));
        LootConfiguration configuration = new LootConfiguration(Collections.emptyMap(), pool);
        register(handler, defaultConfigMap, LOOT_SPAWNER[2], configuration, force);
    }

    private static void generateAirdropDefault(RegistrationHandler handler, Map<String, LootConfiguration> defaultConfigMap, boolean force) {
        LootProvider pool = new MultiValueLootProvider(Arrays.asList(
                getAirdropWeapons(),
                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3HELMET)),
                new RandomChanceLootProvider(0.7F, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY))),
                new RandomChanceLootProvider(0.6F, getLargeBackpacks()),
                new RandomChanceLootProvider(0.25F, new ItemStackLootProvider(
                        new ItemStack(PMCItems.GHILLIE_SUIT),
                        Collections.singletonList(new GhillieColorProcessor(GhillieColorProcessor.ColorProvider.BIOME, new int[0]))
                )),
                new CountLootProvider(1, 2, new RandomChanceLootProvider(0.4F, getAirdropAttachments())),
                new RandomChanceLootProvider(0.5F, getAirdropMeds())
        ));
        LootConfiguration configuration = new LootConfiguration(Collections.emptyMap(), pool);
        register(handler, defaultConfigMap, AIRDROP, configuration, force);
    }

    private static void generateAirdropLarge(RegistrationHandler handler, Map<String, LootConfiguration> defaultConfigMap, boolean force) {
        LootProvider pool = new MultiValueLootProvider(Arrays.asList(
                new CountLootProvider(2, 2, new MultiValueLootProvider(Arrays.asList(
                        getAirdropWeapons(),
                        new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3HELMET)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY)),
                        getLargeBackpacks()
                ))),
                new RandomChanceLootProvider(0.5F, new ItemStackLootProvider(
                        new ItemStack(PMCItems.GHILLIE_SUIT),
                        Collections.singletonList(new GhillieColorProcessor(GhillieColorProcessor.ColorProvider.BIOME, new int[0]))
                )),
                new CountLootProvider(1, 3, new RandomChanceLootProvider(0.5F, getAirdropAttachments())),
                new CountLootProvider(1, 2, getAirdropMeds())
        ));
        LootConfiguration configuration = new LootConfiguration(Collections.emptyMap(), pool);
        register(handler, defaultConfigMap, AIRDROP_LARGE, configuration, force);
    }

    private static LootProvider getArmor(int lvl1, int lvl2, int lvl3) {
        return new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(lvl1, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET))),
                new WeightedRandom.Entry<>(lvl1, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1BODY))),
                new WeightedRandom.Entry<>(lvl1, getSmallBackpacks()),
                new WeightedRandom.Entry<>(lvl2, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2HELMET))),
                new WeightedRandom.Entry<>(lvl2, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY))),
                new WeightedRandom.Entry<>(lvl2, getMediumBackpacks()),
                new WeightedRandom.Entry<>(lvl3, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY))),
                new WeightedRandom.Entry<>(lvl3, getLargeBackpacks())
        ));
    }

    private static LootProvider getAttachments(int tier1, int tier2, int tier3) {
        return new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SMG))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SMG))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE2X))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.GRIP_ANGLED))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SMG))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SMG))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_AR))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.BULLET_LOOPS))),
                new WeightedRandom.Entry<>(tier1, new ItemStackLootProvider(new ItemStack(PMCItems.CHEEKPAD))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_AR))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SNIPER))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE4X))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.GRIP_VERTICAL))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER))),
                new WeightedRandom.Entry<>(tier2, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER))),
                new WeightedRandom.Entry<>(tier3, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SNIPER))),
                new WeightedRandom.Entry<>(tier3, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_AR))),
                new WeightedRandom.Entry<>(tier3, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)))
        ));
    }

    private static LootProvider getWeapons(int pistols, int smgs, int shotguns, int ar, int dmr, int sr, int flare) {
        List<LootProcessor> ammo30 = Collections.singletonList(new AmmoPackProcessor(30, 1, 3));
        List<LootProcessor> ammo15 = Collections.singletonList(new AmmoPackProcessor(15, 1, 3));
        List<LootProcessor> ammo10 = Collections.singletonList(new AmmoPackProcessor(10, 1, 3));
        List<LootProcessor> ammo01 = Collections.singletonList(new AmmoPackProcessor(1, 1, 1));
        return new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(pistols, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.P92), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.P1911), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.R1895), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.R45), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.P18C), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SCORPION), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.DEAGLE), ammo15)
                ))),
                new WeightedRandom.Entry<>(smgs, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.MICROUZI), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.UMP45), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.VECTOR), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.TOMMY_GUN), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.BIZON), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MP5K), ammo30)
                ))),
                new WeightedRandom.Entry<>(shotguns, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.WIN94), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SAWED_OFF), ammo10),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S1897), ammo10),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S686), ammo10),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S12K), ammo10)
                ))),
                new WeightedRandom.Entry<>(ar, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.M16A4), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.M416), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SCAR_L), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.G36C), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.QBZ), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.AKM), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.BERYL_M762), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MK47_MUTANT), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.DP28), ammo30)
                ))),
                new WeightedRandom.Entry<>(dmr, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.VSS), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MINI14), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.QBU), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SKS), ammo30),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SLR), ammo30)
                ))),
                new WeightedRandom.Entry<>(sr, new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.KAR98K), ammo15),
                        new ItemStackLootProvider(new ItemStack(PMCItems.M24), ammo15)
                ))),
                new WeightedRandom.Entry<>(flare, new RandomLootProvider(Collections.singletonList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.FLARE_GUN), ammo01)
                )))
        ));
    }

    private static LootProvider getMeds(int basics, int painkiller, int firstAidKit, int medkit) {
        return new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(basics, new ItemStackLootProvider(new ItemStack(PMCItems.BANDAGE, 5))),
                new WeightedRandom.Entry<>(basics, new ItemStackLootProvider(new ItemStack(PMCItems.ENERGYDRINK))),
                new WeightedRandom.Entry<>(painkiller, new ItemStackLootProvider(new ItemStack(PMCItems.PAINKILLERS))),
                new WeightedRandom.Entry<>(firstAidKit, new ItemStackLootProvider(new ItemStack(PMCItems.FIRSTAIDKIT))),
                new WeightedRandom.Entry<>(medkit, new ItemStackLootProvider(new ItemStack(PMCItems.MEDKIT)))
        ));
    }

    private static LootProvider getRandomAmmoPack() {
        return new WeightedLootProvider(Arrays.asList(
                new WeightedRandom.Entry<>(10, new ItemStackLootProvider(new ItemStack(PMCItems.AMMO_9MM, 30))),
                new WeightedRandom.Entry<>(10, new ItemStackLootProvider(new ItemStack(PMCItems.AMMO_45ACP, 30))),
                new WeightedRandom.Entry<>(10, new ItemStackLootProvider(new ItemStack(PMCItems.AMMO_SHOTGUN, 10))),
                new WeightedRandom.Entry<>(5, new ItemStackLootProvider(new ItemStack(PMCItems.AMMO_556, 30))),
                new WeightedRandom.Entry<>(3, new ItemStackLootProvider(new ItemStack(PMCItems.AMMO_762, 30)))
        ));
    }

    private static LootProvider getThrowables() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.GRENADE)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SMOKE)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MOLOTOV)),
                new ItemStackLootProvider(new ItemStack(PMCItems.FLASHBANG))
        ));
    }

    private static LootProvider getOtherLoot() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.FUELCAN)),
                new ItemStackLootProvider(new ItemStack(PMCItems.NV_GOGGLES)),
                new ItemStackLootProvider(new ItemStack(PMCItems.PAN)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MACHETE)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SICKLE)),
                new ItemStackLootProvider(new ItemStack(PMCItems.CROWBAR))
        ));
    }

    private static LootProvider getAirdropWeapons() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(
                        new ItemStack(PMCItems.AUG),
                        Collections.singletonList(new AmmoPackProcessor(30, 1, 3))
                ),
                new ItemStackLootProvider(
                        new ItemStack(PMCItems.GROZA),
                        Collections.singletonList(new AmmoPackProcessor(30, 1, 3))
                ),
                new ItemStackLootProvider(
                        new ItemStack(PMCItems.M249),
                        Collections.singletonList(new AmmoPackProcessor(50, 2, 4))
                ),
                new ItemStackLootProvider(
                        new ItemStack(PMCItems.MK14),
                        Collections.singletonList(new AmmoPackProcessor(30, 1, 3))
                ),
                new ItemStackLootProvider(
                        new ItemStack(PMCItems.AWM),
                        Collections.singletonList(new AmmoPackProcessor(10, 2, 2))
                )
        ));
    }

    private static LootProvider getAirdropAttachments() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE15X)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE8X)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER))
        ));
    }

    private static LootProvider getAirdropMeds() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDKIT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.FIRSTAIDKIT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.ADRENALINESYRINGE))
        ));
    }

    private static LootProvider getSmallBackpacks() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_SNOW))
        ));
    }

    private static LootProvider getMediumBackpacks() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_SNOW))
        ));
    }

    private static LootProvider getLargeBackpacks() {
        return new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_SNOW))
        ));
    }

    private static void register(RegistrationHandler handler, Map<String, LootConfiguration> map, String confKey, LootConfiguration configuration, boolean force) {
        if (handler.register(confKey, configuration) || force) {
            map.put(confKey, configuration);
        }
    }

    @FunctionalInterface
    public interface RegistrationHandler {
        boolean register(String key, LootConfiguration configuration);
    }
}
