package dev.toma.pubgmc.client.gui.menu;

import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ITickable;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiWidgets extends GuiScreen implements ITickable {

    protected List<Widget> widgets = new ArrayList<>();
    protected List<ITickable> tickables = new ArrayList<>();

    @Override
    public final void initGui() {
        super.initGui();
        widgets.clear();
        this.init();
    }

    public void init() {

    }

    @Override
    public void update() {
        for (ITickable tickable : tickables) {
            tickable.update();
        }
    }

    public <W extends Widget> W addWidget(W widget) {
        widgets.add(widget);
        if(widget instanceof ITickable) {
            this.addTickListener((ITickable) widget);
        }
        return widget;
    }

    public void addTickListener(ITickable tickable) {
        tickables.add(tickable);
    }

    public void drawWidgets(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        for (Widget widget : widgets) {
            widget.render(mc, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        unfocusWidgets();
        for (Widget widget : widgets) {
            if(widget.handleClicked(mouseX, mouseY, mouseButton)) {
                mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                break;
            }
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for (Widget widget : widgets) {
            if(widget.handleDragged(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
                break;
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (Widget widget : widgets) {
            if(widget.handleKeyPressed(typedChar, keyCode)) {
                break;
            }
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void handleMouseInput() throws IOException {
        int delta = Integer.signum(Mouse.getEventDWheel());
        int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        for (Widget widget : widgets) {
            if(widget.handleMouseScrolled(mouseX, mouseY, delta)) {
                break;
            }
        }
        super.handleMouseInput();
    }

    public void unfocusWidgets() {
        widgets.forEach(Widget::unfocus);
    }
}
