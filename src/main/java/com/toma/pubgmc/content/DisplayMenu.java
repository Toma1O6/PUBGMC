package com.toma.pubgmc.content;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public interface DisplayMenu {

    void init(GuiMainMenu menu);

    void draw(GuiMainMenu gui, Minecraft mc, int mx, int my, float partialTicks);

    void onButtonClicked(GuiMainMenu menu, GuiButton button) throws IOException;

    default void onMouseScroll() throws IOException {

    }

    default void mouseClick() {
        
    }
}
