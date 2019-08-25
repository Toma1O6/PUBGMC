package com.toma.pubgmc.client.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiScreenCentered extends GuiScreen {

    public int guiLeft;
    public int guiTop;
    private int guiWidth;
    private int guiHeight;

    public void calculateGuiPosition() {
        guiLeft = (this.width - this.guiWidth) / 2;
        guiTop = (this.height - this.guiHeight) / 2;
    }

    public final GuiScreenCentered setDimension(int width, int height) {
        this.guiWidth = width;
        this.guiHeight = height;
        return this;
    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiHeight() {
        return guiHeight;
    }
}
