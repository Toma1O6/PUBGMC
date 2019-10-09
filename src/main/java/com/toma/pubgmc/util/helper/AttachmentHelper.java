package com.toma.pubgmc.util.helper;

import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;

public class AttachmentHelper {
    static final ItemAttachment[] PISTOL_MAG = {PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL};

    public static ItemAttachment getPistolBarrelAttachments() {
        return PMCRegistry.PMCItems.SILENCER_PISTOL;
    }

    public static ItemAttachment[] getSMGBarrelAttachments() {
        return new ItemAttachment[]{PMCItems.COMPENSATOR_SMG, PMCItems.SILENCER_SMG};
    }

    public static ItemAttachment[] getARBarrelAttachments() {
        return new ItemAttachment[]{PMCItems.COMPENSATOR_AR, PMCItems.SILENCER_AR};
    }

    public static ItemAttachment[] getSRBarrelAttachments() {
        return new ItemAttachment[]{PMCItems.COMPENSATOR_SNIPER, PMCItems.SILENCER_SNIPER};
    }

    public static ItemAttachment[] getPistolMagazineAttachments() {
        return new ItemAttachment[]{PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL};
    }

    public static ItemAttachment[] getSMGMagazineAttachments() {
        return new ItemAttachment[]{PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG, PMCRegistry.PMCItems.EXTENDED_MAG_SMG, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG};
    }

    public static ItemAttachment[] getARMagazineAttachments() {
        return new ItemAttachment[]{PMCRegistry.PMCItems.QUICKDRAW_MAG_AR, PMCRegistry.PMCItems.EXTENDED_MAG_AR, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR};
    }

    public static ItemAttachment[] getSRMagazineAttachments() {
        return new ItemAttachment[]{PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER, PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER};
    }

    public static ItemAttachment[] getGrips() {
        return new ItemAttachment[]{PMCItems.GRIP_VERTICAL, PMCItems.GRIP_ANGLED};
    }

    public static ItemAttachment[] getSights() {
        return new ItemAttachment[]{PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC};
    }

    public static ItemAttachment[] getSmallScopes() {
        return new ItemAttachment[]{PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC, PMCItems.SCOPE2X, PMCItems.SCOPE4X};
    }

    public static ItemAttachment[] getScopes() {
        return new ItemAttachment[]{PMCItems.RED_DOT, PMCItems.HOLOGRAPHIC, PMCItems.SCOPE2X, PMCItems.SCOPE4X, PMCItems.SCOPE8X, PMCItems.SCOPE15X};
    }

    public static ItemAttachment[] getStock(boolean sniper) {
        return sniper ? new ItemAttachment[]{PMCItems.CHEEKPAD, PMCItems.BULLET_LOOPS_SNIPER} : new ItemAttachment[]{PMCItems.BULLET_LOOPS_SHOTGUN};
    }
}
