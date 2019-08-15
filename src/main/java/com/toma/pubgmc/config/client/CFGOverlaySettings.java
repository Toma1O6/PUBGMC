package com.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public class CFGOverlaySettings {

    @Config.Name("Use image overlay for boost rendering")
    @Config.Comment("Your boost overlay will be rendered instead of the XP bar. If this == TEXT, you'll be able to see numbers above hunger bar which will indicate your boost value")
    public CFGEnumOverlayStyle imageBoostOverlay = CFGEnumOverlayStyle.IMAGE;

    @Config.Name("Boost (text) position")
    public CFG2DCoords textBoostOverlayPos = new CFG2DCoords(0, 0);

    @Config.Name("Boost (image) position")
    public CFG2DCoords imgBoostOverlayPos = new CFG2DCoords(0, 0);

    @Config.Name("Armor icons render")
    public boolean renderArmorIcons = true;
}
