package dev.toma.pubgmc.init;

import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.event.GunPostInitializeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Deprecated
public class PMCRegistry {

    @Mod.EventBusSubscriber
    public static class GunInit {
        @SubscribeEvent
        public static void onGunInitialized(GunPostInitializeEvent e) {
            GunBase gun = e.getGun();
            if (gun == PMCItems.MICROUZI) {
                e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
                e.initScopeAttachments(PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC);
            } else if (gun == PMCItems.VECTOR) {
                e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
                e.initGripAttachments(PMCItems.GRIP_VERTICAL);
                e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.BIZON) {
                e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.MP5K) {
                e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.TOMMY_GUN) {
                e.initBarrelAttachments(PMCItems.SILENCER_SMG);
                e.initGripAttachments(PMCItems.GRIP_VERTICAL);
                e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
            } else if (gun == PMCItems.UMP45) {
                e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.M16A4) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.M416) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.SCAR_L) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.QBZ) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.G36C) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.AUG) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.AKM) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.BERYL_M762) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.MK47_MUTANT) {
                e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.GROZA) {
                e.initBarrelAttachments(PMCItems.SILENCER_AR);
                e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.DP28) {
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.M249) {
                e.initScopeAttachments(AttachmentHelper.getSmallScopes());
            } else if (gun == PMCItems.VSS) {
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            } else if (gun == PMCItems.MINI14) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
            } else if (gun == PMCItems.QBU) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
            } else if (gun == PMCItems.SKS) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initGripAttachments(AttachmentHelper.getGrips());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            } else if (gun == PMCItems.SLR) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            } else if (gun == PMCItems.MK14) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            } else if (gun == PMCItems.KAR98K) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(AttachmentHelper.getStock(true));
            } else if (gun == PMCItems.M24) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            } else if (gun == PMCItems.AWM) {
                e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
                e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
                e.initScopeAttachments(AttachmentHelper.getScopes());
                e.initStockAttachments(PMCItems.CHEEKPAD);
            }
        }
    }
}
