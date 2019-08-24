package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.common.capability.IWorldData;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuiLootSetup extends GuiScreen {

    public GuiLootSetup(IWorldData lootData) {
        if(mc.player.getPermissionLevel() < 4) {
            mc.displayGuiScreen(null);
            mc.player.sendMessage(new TextComponentString(TextFormatting.RED + "You don't have permission to edit loot data"));
            return;
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
