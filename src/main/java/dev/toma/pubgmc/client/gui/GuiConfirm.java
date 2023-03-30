package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class GuiConfirm extends GuiWidgets {

    public final GuiScreen parent;
    protected final Callback callback;
    protected final String title;
    protected final String desc;
    protected int guiLeft;
    protected int guiTop;
    protected int xSize;
    protected int ySize;
    private ButtonWidget confirm, cancel;

    public static void display(GuiScreen parent, String title, String desc, Callback callback) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiConfirm(parent, title, desc, callback));
    }

    public GuiConfirm(GuiScreen parent, String title, String desc, Callback callback) {
        this.parent = parent;
        this.callback = callback;
        this.title = title;
        this.desc = desc;
    }

    @Override
    public void init() {
        int sixth = width / 6;
        int half = height / 2;
        this.guiLeft = sixth;
        this.guiTop = half / 2;
        this.xSize = sixth * 4;
        this.ySize = half;
        int seventh = xSize / 7;
        addWidget(new CustomButton(guiLeft + seventh, guiTop + ySize - 25, 2 * seventh, 20, "Confirm", (widget, mouseX, mouseY, button) -> onConfirm()));
        addWidget(new CustomButton(guiLeft + 4 * seventh, guiTop + ySize - 25, 2 * seventh, 20, "Cancel", (widget, mouseX, mouseY, button) -> onCancel()));
    }

    protected void onConfirm() {
        callback.call(true, parent);
    }

    protected void onCancel() {
        callback.call(false, parent);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        parent.drawScreen(mouseX, mouseY, partialTicks);
        Widget.drawColorShape(guiLeft, guiTop, guiLeft + xSize, guiTop + ySize, 0.0F, 0.0F, 0.0F, 0.8F);
        Widget.drawColorShape(guiLeft, guiTop + 15, guiLeft + xSize, guiTop + 17, 1.0F, 1.0F, 1.0F, 1.0F);
        FontRenderer renderer = mc.fontRenderer;
        String info = "Confirm action";
        int textWidth = renderer.getStringWidth(info);
        int titleWidth = renderer.getStringWidth(title);
        renderer.drawString(info, guiLeft + (xSize - textWidth) / 2.0F, guiTop + 4, 0xFFFFFF, false);
        renderer.drawString(title, guiLeft + (xSize - titleWidth) / 2.0F, guiTop + 21, 0xFFFFFF, false);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public interface Callback {
        void call(boolean confirmed, GuiScreen parent);
    }

    static class CustomButton extends ButtonWidget {

        CustomButton(int x, int y, int width, int height, String text, IPressable pressable) {
            super(x, y, width, height, text, pressable);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
            drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
            int textWidth = mc.fontRenderer.getStringWidth(text);
            mc.fontRenderer.drawString(text, x + (width - textWidth) / 2.0F, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, isMouseOver(mouseX, mouseY) ? 0xFFFF00 : 0xFFFFFF, false);
        }
    }
}
