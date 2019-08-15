package com.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public class CFGOtherSettings {

    @Config.Name("On-join world notifications")
    public boolean messagesOnJoin = true;

    @Config.Name("Lootbox content render")
    @Config.RequiresMcRestart
    public CFGLootRenderStyle lootRenderStyle = CFGLootRenderStyle.FANCY;
}
