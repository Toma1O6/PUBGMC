package com.toma.pubgmc.client.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiScreenCentered extends GuiScreen {

    public int guiLeft;
    public int guiTop;
    private int guiWidth;
    private int guiHeight;

    public void calculateGuiPosition() {
        guiLeft = (this.width - this.guiWidth) / 2;
        guiTop = (this.height - this.guiTop) / 2;
    }

    public final void setDimension(int width, int height) {
        this.guiWidth = width;
        this.guiHeight = height;
    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiHeight() {
        return guiHeight;
    }
}
