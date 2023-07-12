package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.loot.LootProcessor;
import dev.toma.pubgmc.api.game.loot.LootProvider;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.SpecialInventoryItem;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.ffa.FFAGameConfiguration;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.data.loot.*;
import dev.toma.pubgmc.data.loot.processor.AmmoPackProcessor;
import dev.toma.pubgmc.data.loot.processor.AttachmentProcessor;
import dev.toma.pubgmc.data.loot.processor.GhillieColorProcessor;
import dev.toma.pubgmc.data.loot.processor.GhillieColorProcessor.ColorProvider;
import dev.toma.pubgmc.data.loot.processor.WeaponAmmoProcessor;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import dev.toma.pubgmc.util.math.WeightedRandom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class DefaultEntityLoadouts {

    public static void register() {
        // Loadout handlers
        LoadoutManager.registerLoadoutHandler(EntityPlayerMP.class, DefaultEntityLoadouts::applyPlayerLoadout);
        LoadoutManager.registerLoadoutHandler(EntityAIPlayer.class, DefaultEntityLoadouts::applyAiLoadout);

        // Loadouts
        registerDefaultAiLoadout();
        registerBattleRoyaleLoadouts();
        registerFfaLoadouts();
    }

    private static void registerFfaLoadouts() {
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_UMP45, new EntityLoadout.Builder()
                .withName("UMP-45")
                .withIcon(new ItemStack(PMCItems.UMP45))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.UMP45), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT)));
                            map.put(AttachmentType.MUZZLE, new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SMG)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(60, 3, 3)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(1), helmet(2)
                )))
                .build()
        );
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_VECTOR, new EntityLoadout.Builder()
                .withName("Vector")
                .withIcon(new ItemStack(PMCItems.VECTOR))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.VECTOR), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT)));
                            map.put(AttachmentType.MUZZLE, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SMG)));
                            map.put(AttachmentType.MAGAZINE, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SMG)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(60, 3, 3)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(1), helmet(2)
                )))
                .build()
        );
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_AKM, new EntityLoadout.Builder()
                .withName("AKM")
                .withIcon(new ItemStack(PMCItems.AKM))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.AKM), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC)));
                            map.put(AttachmentType.MUZZLE, new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(60, 3, 3)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(2), helmet(1)
                )))
                .build()
        );
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_M416, new EntityLoadout.Builder()
                .withName("M416")
                .withIcon(new ItemStack(PMCItems.M416))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.M416), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC)));
                            map.put(AttachmentType.MUZZLE, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_AR)));
                            map.put(AttachmentType.MAGAZINE, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(60, 3, 3)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(2), helmet(1)
                )))
                .build()
        );
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_SLR, new EntityLoadout.Builder()
                .withName("SLR")
                .withIcon(new ItemStack(PMCItems.SLR))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.SLR), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE4X)));
                            map.put(AttachmentType.MAGAZINE, new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER)));
                            map.put(AttachmentType.STOCK, new ItemStackLootProvider(new ItemStack(PMCItems.CHEEKPAD)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(45, 2, 2)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(1), helmet(1)
                )))
                .build()
        );
        LoadoutManager.register(FFAGameConfiguration.LOADOUT_M24, new EntityLoadout.Builder()
                .withName("M24")
                .withIcon(new ItemStack(PMCItems.M24))
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.M24), Arrays.asList(
                        new AttachmentProcessor(map -> {
                            map.put(AttachmentType.SCOPE, new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE8X)));
                            map.put(AttachmentType.MUZZLE, new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SNIPER)));
                            map.put(AttachmentType.MAGAZINE, new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER)));
                        }),
                        new WeaponAmmoProcessor(1.0F),
                        new AmmoPackProcessor(20, 2, 2)
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        armor(2), helmet(2)
                )))
                .withSpecialEquipmentProvider(new ItemStackLootProvider(new ItemStack(PMCItems.GHILLIE_SUIT), Collections.singletonList(
                        new GhillieColorProcessor(ColorProvider.BIOME, new int[0])
                )))
                .build()
        );
    }

    private static void registerDefaultAiLoadout() {
        LoadoutManager.register(EntityAIPlayer.DEFAULT_LOADOUT, new EntityLoadout.Builder()
                .withWeaponProvider(new RandomLootProvider(Arrays.asList(
                        new ItemStackLootProvider(new ItemStack(PMCItems.P92)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.P1911)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.R1895)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.R45)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.P18C)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SCORPION)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.DEAGLE)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SAWED_OFF)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S1897)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S686)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.S12K)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MICROUZI)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.UMP45)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.TOMMY_GUN)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.VECTOR)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MP5K)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.BIZON)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.M16A4)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.M416)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SCAR_L)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.G36C)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.QBZ)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.AKM)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.BERYL_M762)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MK47_MUTANT)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.DP28)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.VSS)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.MINI14)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.QBU)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SKS)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.SLR)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.WIN94)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.KAR98K)),
                        new ItemStackLootProvider(new ItemStack(PMCItems.M24))
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        new RandomLootProvider(Arrays.asList(
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2HELMET)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3HELMET))
                        )),
                        new RandomLootProvider(Arrays.asList(
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1BODY)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY))
                        ))
                )))
                .withSpecialEquipmentProvider(new MultiValueLootProvider(Arrays.asList(
                        new RandomChanceLootProvider(0.5F, new RandomLootProvider(Arrays.asList(
                                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_FOREST)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_FOREST)),
                                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_FOREST))
                        ))),
                        new RandomChanceLootProvider(0.1F, new ItemStackLootProvider(new ItemStack(PMCItems.NV_GOGGLES))),
                        new RandomChanceLootProvider(0.1F, new ItemStackLootProvider(new ItemStack(PMCItems.GHILLIE_SUIT), Collections.singletonList(new GhillieColorProcessor(GhillieColorProcessor.ColorProvider.BIOME, new int[0]))))
                )))
                .build()
        );
    }

    private static void registerBattleRoyaleLoadouts() {
        // Declarations
        LootProcessor pistolAmmoPack = new AmmoPackProcessor(15, 1, 3);
        LootProcessor shotgunAmmoPack = new AmmoPackProcessor(5, 1, 3);
        LootProcessor defaultAmmoPack = new AmmoPackProcessor(30, 1, 3);
        Function<List<LootProcessor>, LootProvider> pistolsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.P92), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.P1911), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.R1895), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.R45), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.P18C), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.SCORPION), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.DEAGLE), processors)
        ));
        Function<List<LootProcessor>, LootProvider> shotgunsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.SAWED_OFF), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.S1897), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.S686), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.S12K), processors)
        ));
        Function<List<LootProcessor>, LootProvider> smgsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.MICROUZI), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.UMP45), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.TOMMY_GUN), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.VECTOR), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.MP5K), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.BIZON), processors)
        ));
        Function<List<LootProcessor>, LootProvider> arsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.M16A4), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.M416), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.SCAR_L), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.G36C), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.QBZ), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.AKM), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.BERYL_M762), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.MK47_MUTANT), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.DP28), processors)
        ));
        Function<List<LootProcessor>, LootProvider> dmrsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.VSS), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.MINI14), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.QBU), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.SKS), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.SLR), processors)
        ));
        Function<List<LootProcessor>, LootProvider> srsProvider = (processors) -> new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.WIN94), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.KAR98K), processors),
                new ItemStackLootProvider(new ItemStack(PMCItems.M24), processors)
        ));
        LootProvider smallBackpacks = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SMALL_BACKPACK_SNOW))
        ));
        LootProvider mediumBackpacks = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDIUM_BACKPACK_SNOW))
        ));
        LootProvider largeBackpacks = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_FOREST)),
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_DESERT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.LARGE_BACKPACK_SNOW))
        ));
        LootProvider lowTierMeds = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.BANDAGE, 5)),
                new ItemStackLootProvider(new ItemStack(PMCItems.ENERGYDRINK)),
                new ItemStackLootProvider(new ItemStack(PMCItems.PAINKILLERS))
        ));
        LootProvider highTierMeds = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.FIRSTAIDKIT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.MEDKIT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.ADRENALINESYRINGE))
        ));
        LootProvider lowTierAttachments = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT)),
                new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC)),
                new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SMG)),
                new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SMG))
        ));
        LootProvider highTierAttachments = new RandomLootProvider(Arrays.asList(
                new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE2X)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE4X)),
                new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SNIPER)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR)),
                new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER))
        ));
        LootProcessor lowTierWeaponAttachments = new AttachmentProcessor(map -> {
            map.put(AttachmentType.SCOPE, new RandomChanceLootProvider(0.2F, new RandomLootProvider(Arrays.asList(
                    new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE2X))
            ))));
            map.put(AttachmentType.MUZZLE, new RandomChanceLootProvider(0.3F, new RandomLootProvider(Arrays.asList(
                    new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SMG)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SMG))
            ))));
            map.put(AttachmentType.MAGAZINE, new RandomChanceLootProvider(0.35F, new RandomLootProvider(Arrays.asList(
                    new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SMG)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_AR)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SMG)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR))
            ))));
        });
        LootProcessor highTierWeaponAttachments = new AttachmentProcessor(map -> {
            map.put(AttachmentType.SCOPE, new RandomChanceLootProvider(0.5F, new RandomLootProvider(Arrays.asList(
                    new ItemStackLootProvider(new ItemStack(PMCItems.RED_DOT)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.HOLOGRAPHIC)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE2X)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.SCOPE4X))
            ))));
            map.put(AttachmentType.MUZZLE, new RandomChanceLootProvider(0.4F, new MultiValueLootProvider(Arrays.asList(
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SMG)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SMG))
                    )),
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_AR)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_AR))
                    )),
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.COMPENSATOR_SNIPER)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.SILENCER_SNIPER))
                    ))
            ))));
            map.put(AttachmentType.GRIP, new RandomChanceLootProvider(0.4F, new RandomLootProvider(Arrays.asList(
                    new ItemStackLootProvider(new ItemStack(PMCItems.GRIP_VERTICAL)),
                    new ItemStackLootProvider(new ItemStack(PMCItems.GRIP_ANGLED))
            ))));
            map.put(AttachmentType.MAGAZINE, new RandomChanceLootProvider(0.35F, new MultiValueLootProvider(Arrays.asList(
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SMG)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SMG)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG))
                    )),
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_AR)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_AR)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_AR))
                    )),
                    new RandomLootProvider(Arrays.asList(
                            new ItemStackLootProvider(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER)),
                            new ItemStackLootProvider(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER))
                    ))
            ))));
        });

        // Loadouts
        LoadoutManager.register(BattleRoyaleGame.PLAYER_INITIAL_LOOT_PATH, new EntityLoadout.Builder()
                .withWeaponProvider(new ItemStackLootProvider(new ItemStack(PMCItems.PARACHUTE)))
                .build()
        );
        LoadoutManager.register(BattleRoyaleGame.AI_INITIAL_LOOT_PATH, EntityLoadout.EMPTY);
        LoadoutManager.register(BattleRoyaleGame.AI_EARLY_GAME_LOOT_PATH, new EntityLoadout.Builder()
                .withWeaponProvider(new WeightedLootProvider(Arrays.asList(
                        new WeightedRandom.Entry<>(40, pistolsProvider.apply(Arrays.asList(lowTierWeaponAttachments, pistolAmmoPack))),
                        new WeightedRandom.Entry<>(20, shotgunsProvider.apply(Arrays.asList(lowTierWeaponAttachments, shotgunAmmoPack))),
                        new WeightedRandom.Entry<>(30, smgsProvider.apply(Arrays.asList(lowTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(10, arsProvider.apply(Arrays.asList(lowTierWeaponAttachments, defaultAmmoPack)))
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        new RandomChanceLootProvider(0.3F, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET))),
                        new RandomChanceLootProvider(0.5F, new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(70, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1BODY))),
                                new WeightedRandom.Entry<>(30, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY)))
                        )))
                )))
                .withSpecialEquipmentProvider(new RandomChanceLootProvider(0.5F, new WeightedLootProvider(Arrays.asList(
                        new WeightedRandom.Entry<>(60, smallBackpacks),
                        new WeightedRandom.Entry<>(40, mediumBackpacks)
                ))))
                .withGeneralLootProvider(new CountLootProvider(0, 2, new RandomLootProvider(Arrays.asList(
                        lowTierMeds, lowTierAttachments
                ))))
                .build()
        );
        LoadoutManager.register(BattleRoyaleGame.AI_MID_GAME_LOOT_PATH, new EntityLoadout.Builder()
                .withWeaponProvider(new WeightedLootProvider(Arrays.asList(
                        new WeightedRandom.Entry<>(30, smgsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(50, arsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(10, dmrsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(10, srsProvider.apply(Arrays.asList(highTierWeaponAttachments, pistolAmmoPack)))
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(45, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2HELMET))),
                                new WeightedRandom.Entry<>(55, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET)))
                        )),
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(10, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY))),
                                new WeightedRandom.Entry<>(55, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY))),
                                new WeightedRandom.Entry<>(35, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1BODY)))
                        ))
                )))
                .withSpecialEquipmentProvider(new MultiValueLootProvider(Arrays.asList(
                        new RandomChanceLootProvider(0.05F, new ItemStackLootProvider(new ItemStack(PMCItems.NV_GOGGLES))),
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(35, smallBackpacks),
                                new WeightedRandom.Entry<>(50, mediumBackpacks),
                                new WeightedRandom.Entry<>(15, largeBackpacks)
                        ))
                )))
                .withGeneralLootProvider(new CountLootProvider(1, 4, new RandomLootProvider(Arrays.asList(
                        lowTierMeds, lowTierAttachments, highTierMeds, highTierAttachments
                ))))
                .build()
        );
        LoadoutManager.register(BattleRoyaleGame.AI_LATE_GAME_LOOT_PATH, new EntityLoadout.Builder()
                .withWeaponProvider(new WeightedLootProvider(Arrays.asList(
                        new WeightedRandom.Entry<>(5, smgsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(50, arsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(30, dmrsProvider.apply(Arrays.asList(highTierWeaponAttachments, defaultAmmoPack))),
                        new WeightedRandom.Entry<>(15, srsProvider.apply(Arrays.asList(highTierWeaponAttachments, pistolAmmoPack)))
                )))
                .withArmorProvider(new MultiValueLootProvider(Arrays.asList(
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(90, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2HELMET))),
                                new WeightedRandom.Entry<>(10, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET)))
                        )),
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(25, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY))),
                                new WeightedRandom.Entry<>(75, new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY)))
                        ))
                )))
                .withSpecialEquipmentProvider(new MultiValueLootProvider(Arrays.asList(
                        new RandomChanceLootProvider(0.15F, new ItemStackLootProvider(new ItemStack(PMCItems.NV_GOGGLES))),
                        new WeightedLootProvider(Arrays.asList(
                                new WeightedRandom.Entry<>(10, smallBackpacks),
                                new WeightedRandom.Entry<>(65, mediumBackpacks),
                                new WeightedRandom.Entry<>(25, largeBackpacks)
                        ))
                )))
                .withGeneralLootProvider(new CountLootProvider(1, 5, new RandomLootProvider(Arrays.asList(
                        lowTierMeds, highTierMeds, highTierAttachments
                ))))
                .build()
        );
    }

    private static void applyPlayerLoadout(EntityPlayerMP player, EntityLoadout loadout) {
        LootGenerationContext context = LootGenerationContext.entity(player);
        List<ItemStack> weaponItems = loadout.getWeapons(context);
        List<ItemStack> armorItems = loadout.getArmor(context);
        List<ItemStack> specialEquipment = loadout.getSpecialEquipment(context);
        List<ItemStack> generalLoot = loadout.getGeneralLoot(context);

        addToInventory(player.inventory, weaponItems, 0, 0);
        addArmor(armorItems, player);
        addToInventory(player.inventory, generalLoot, 0, 3);
        IPlayerData data = PlayerDataProvider.get(player);
        if (data != null) {
            addSpecialEquipment(data, specialEquipment);
        }
    }

    private static void applyAiLoadout(EntityAIPlayer ai, EntityLoadout loadout) {
        LootGenerationContext context = LootGenerationContext.entity(ai);
        List<ItemStack> weapon = loadout.getWeapons(context);
        List<ItemStack> armorItems = loadout.getArmor(context);
        List<ItemStack> specialEquipment = loadout.getSpecialEquipment(context);
        List<ItemStack> general = loadout.getGeneralLoot(context);

        ai.clearInventory();
        if (!weapon.isEmpty()) {
            ai.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weapon.get(0));
            addToInventory(ai.getInventory(), weapon.subList(1, weapon.size()), 0, 0);
        }
        addArmor(armorItems, ai);
        addSpecialEquipment(ai, specialEquipment);
        addToInventory(ai.getInventory(), general, 2, 0);

        SerializationHelper.syncEntity(ai);
    }

    private static void addToInventory(IInventory inventory, List<ItemStack> items, int index, int offset) {
        for (int i = index; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                index = i;
                break;
            }
        }
        for (ItemStack stack : items) {
            int position = index++ + offset;
            if (position >= inventory.getSizeInventory())
                break;
            inventory.setInventorySlotContents(position, stack.copy());
        }
    }

    private static void addArmor(List<ItemStack> items, EntityLivingBase entity) {
        for (ItemStack stack : items) {
            if (!stack.isEmpty() && stack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) stack.getItem();
                EntityEquipmentSlot slot = armor.armorType;
                ItemStack equipped = entity.getItemStackFromSlot(slot);
                if (equipped.isEmpty()) {
                    entity.setItemStackToSlot(slot, stack.copy());
                }
            }
        }
    }

    private static void addSpecialEquipment(SpecialInventoryProvider provider, List<ItemStack> items) {
        for (ItemStack stack : items) {
            if (!stack.isEmpty() && stack.getItem() instanceof SpecialInventoryItem) {
                SpecialInventoryItem item = (SpecialInventoryItem) stack.getItem();
                SpecialEquipmentSlot slot = item.getSlotType();
                ItemStack oldItem = provider.getSpecialItemFromSlot(slot);
                if (oldItem.isEmpty()) {
                    provider.setSpecialItemToSlot(slot, stack.copy());
                }
            }
        }
    }

    private static LootProvider helmet(int level) {
        switch (level) {
            default:
            case 1:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1HELMET));
            case 2:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2HELMET));
            case 3:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3HELMET));
        }
    }

    private static LootProvider armor(int level) {
        switch (level) {
            default:
            case 1:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR1BODY));
            case 2:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR2BODY));
            case 3:
                return new ItemStackLootProvider(new ItemStack(PMCItems.ARMOR3BODY));
        }
    }

    private DefaultEntityLoadouts() {
    }
}
