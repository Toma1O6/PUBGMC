package dev.toma.pubgmc.client.gui.menu;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiWIP extends GuiScreen {

    private final GuiScreen lastScreen;

    public GuiWIP(GuiScreen currentScreen) {
        this.lastScreen = currentScreen;
    }

    @Override
    public void initGui() {
        int px = (width - 60) / 2;
        int py = (height - 20) / 2;
        this.addButton(new GuiButton(0, px, py + 20, 60, 20, "Ok"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id == 0) {
            mc.displayGuiScreen(lastScreen);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        String text = "Work in progress... Coming soon";
        int tw = fontRenderer.getStringWidth(text);
        fontRenderer.drawStringWithShadow(text, (width - tw) / 2f, (height - fontRenderer.FONT_HEIGHT) / 2f, 0xff0000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
