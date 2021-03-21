package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.gui.GuiConfirm;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.List;

public class ListWidget<T> extends Widget {

    final GuiWidgets parent;
    final List<T> list;
    final String listName;
    final GuiConfirm.Callback callback;
    T element;
    int selected;
    int scrollIndex;
    int displayAmount;
    boolean awaitConfirm;

    public <G extends GuiWidgets & IPopupHandler> ListWidget(G parent, int x, int y, int width, int height, List<T> list, String listName, GuiConfirm.Callback callback) {
        super(x, y, width, height);
        this.selected = -1;
        this.parent = parent;
        this.list = list;
        this.listName = listName;
        this.displayAmount = (height - 15) / 15;
        this.callback = callback;
    }

    public List<T> getList() {
        return list;
    }

    public ListWidget<T> requireConfirm() {
        this.awaitConfirm = true;
        return this;
    }

    public void refresh(List<T> list) {
        this.selected = -1;
        this.element = null;
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public boolean processClicked(int mouseX, int mouseY, int button) {
        if(mouseX >= x && mouseX <= x + width - 2) {
            if(mouseY >= y + 15) {
                int i = list.size();
                if(i < displayAmount) {
                    return mouseY <= y + (i + 1) * 15;
                }
                return mouseY <= y + height;
            }
        }
        return false;
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        int start = y + 15;
        int pos = mouseY - start;
        int id = scrollIndex;
        while (pos > 15) {
            pos -= 15;
            ++id;
        }
        if(awaitConfirm) {
            if(id == selected) {
                // TODO ask for confirmation only when project is not saved
                GuiConfirm.display(parent, "Do you want to open new animation project?", list.get(selected).toString(), callback);
                selected = -1;
                return;
            }
        }
        selected = id;
        element = list.get(selected);
        if(!awaitConfirm) {
            callback.call(true, parent);
        }
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        FontRenderer renderer = mc.fontRenderer;
        int textSize = renderer.getStringWidth(listName);
        drawColorShape(x, y, x + textSize + 6, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
        drawColorShape(x, y + 15, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.4F);
        renderer.drawString(listName, x + 3, y + 4, 0x999999);
        for (int i = scrollIndex; i < Math.min(scrollIndex + displayAmount, list.size()); i++) {
            int j = i - scrollIndex;
            int yStart = y + 15 + j * 15;
            int textColor = 0x999999;
            if(i == selected) {
                textColor = 0x333333;
                drawColorShape(x, yStart, x + width - 2, yStart + 15, 1.0F, 1.0F, 1.0F, 0.5F);
            } else if(mouseX >= x && mouseX <= x + width - 2 && mouseY >= yStart && mouseY <= yStart + 15) {
                drawColorShape(x, yStart, x + width - 2, yStart + 15, 1.0F, 1.0F, 1.0F, 0.2F);
                textColor = 0x333333;
            }
            renderer.drawString(list.get(i).toString(), x + 3, yStart + 4, textColor);
        }
    }

    public T getSelectedElement() {
        return element;
    }
}
