package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.common.items.attachment.ItemGrip;
import dev.toma.pubgmc.common.items.attachment.ItemMagazine;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.attachment.ItemScope;

import static dev.toma.pubgmc.init.PMCItems.*;

public class AttachmentHelper {

    public static ItemMagazine[] getPistolSmgMags() {
        return new ItemMagazine[]{QUICKDRAW_MAG_SMG, EXTENDED_MAG_SMG, EXTENDED_QUICKDRAW_MAG_SMG};
    }

    public static ItemMagazine[] getARMags() {
        return new ItemMagazine[]{QUICKDRAW_MAG_AR, EXTENDED_MAG_AR, EXTENDED_QUICKDRAW_MAG_AR};
    }

    public static ItemMagazine[] getDMRMags() {
        return new ItemMagazine[]{QUICKDRAW_MAG_AR, EXTENDED_MAG_AR, EXTENDED_QUICKDRAW_MAG_AR, QUICKDRAW_MAG_SNIPER, EXTENDED_MAG_SNIPER, EXTENDED_QUICKDRAW_MAG_SNIPER};
    }

    public static ItemMagazine[] getSRMags() {
        return new ItemMagazine[]{QUICKDRAW_MAG_SNIPER, EXTENDED_MAG_SNIPER, EXTENDED_QUICKDRAW_MAG_SNIPER};
    }

    public static ItemMuzzle[] getSmgMuzzle() {
        return new ItemMuzzle[]{SILENCER_SMG, COMPENSATOR_SMG};
    }

    public static ItemMuzzle[] getARMuzzle() {
        return new ItemMuzzle[]{SILENCER_AR, COMPENSATOR_AR};
    }

    public static ItemMuzzle[] getDMRMuzzle() {
        return new ItemMuzzle[]{SILENCER_AR, SILENCER_SNIPER, COMPENSATOR_AR, COMPENSATOR_SNIPER};
    }

    public static ItemMuzzle[] getSRMuzzle() {
        return new ItemMuzzle[]{SILENCER_SNIPER, COMPENSATOR_SNIPER};
    }

    public static ItemScope[] redDotHoloScope() {
        return new ItemScope[]{RED_DOT, HOLOGRAPHIC};
    }

    public static ItemScope[] closeRangeScopes() {
        return new ItemScope[]{RED_DOT, HOLOGRAPHIC, SCOPE2X, SCOPE4X};
    }

    public static ItemScope[] longRangeScopes() {
        return new ItemScope[]{RED_DOT, HOLOGRAPHIC, SCOPE2X, SCOPE4X, SCOPE8X, SCOPE15X};
    }

    public static ItemGrip[] allGrips() {
        return new ItemGrip[]{GRIP_ANGLED, GRIP_VERTICAL};
    }
}
