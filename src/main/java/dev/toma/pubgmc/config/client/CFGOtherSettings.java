package dev.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public final class CFGOtherSettings {

    @Config.Name("On-join world notifications")
    public boolean messagesOnJoin = true;

    @Config.Name("Game zone color")
    @Config.RangeInt(min = 0x000000, max = 0xFFFFFF)
    public int zoneColor = 0x00BAFF;

    @Config.Name("Lootbox content render")
    @Config.RequiresMcRestart
    public CFGLootRenderStyle lootRenderStyle = CFGLootRenderStyle.FANCY;

    @Config.Name("Dynamic bobbing animation")
    @Config.Comment({"This will automatically toggle bobbing animations on when sprinting with gun"})
    public boolean useDynamicBobbing = true;
}
