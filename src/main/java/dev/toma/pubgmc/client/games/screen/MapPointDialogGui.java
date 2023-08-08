package dev.toma.pubgmc.client.games.screen;

import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.util.helper.ImageUtil;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public abstract class MapPointDialogGui<P extends GameMapPoint> extends GuiWidgets {

    protected final GameMapInstance ownerMap;
    protected final P point;

    protected int dialogWidth;
    protected int dialogHeight;
    protected int left;
    protected int top;

    public MapPointDialogGui(GameMapInstance ownerMap, P point) {
        this.ownerMap = ownerMap;
        this.point = point;
    }

    protected abstract void confirmed();

    protected void setSize(int width, int height) {
        this.dialogWidth = width;
        this.dialogHeight = height;
    }

    @Override
    public void init() {
        this.left = (width - dialogWidth) / 2;
        this.top = (height - dialogHeight) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawBG();
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_RETURN) {
            handleEnterKey();
        }
    }

    protected void handleEnterKey() {
        this.confirmed();
    }

    protected void drawBG(int primary, int secondary) {
        ImageUtil.drawShape(left - 1, top - 1, left + dialogWidth + 1, top + dialogHeight + 1, secondary);
        ImageUtil.drawShape(left, top, left + dialogWidth, top + dialogHeight, primary);
    }

    protected void drawBG() {
        drawBG(0xFF000000, 0xFFFFFFFF);
    }
}
