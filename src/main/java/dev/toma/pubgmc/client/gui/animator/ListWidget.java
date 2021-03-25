package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.gui.GuiConfirm;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class ListWidget<T> extends Widget {

    final GuiWidgets parent;
    final List<T> list;
    final String listName;
    final GuiConfirm.Callback callback;
    Function<T, String> formatter = Object::toString;
    T element;
    int selected;
    int scrollIndex;
    int displayAmount;
    BooleanSupplier awaitConfirm = () -> false;

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

    public ListWidget<T> requireConfirm(BooleanSupplier awaitConfirm) {
        this.awaitConfirm = awaitConfirm;
        return this;
    }

    public ListWidget<T> withFormatter(Function<T, String> formatter) {
        this.formatter = formatter;
        return this;
    }

    public void refresh(List<T> list) {
        this.selected = -1;
        //this.element = null;
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
        boolean requiresConfirmation = awaitConfirm.getAsBoolean();
        if(requiresConfirmation) {
            if(id == selected) {
                GuiConfirm.display(parent, "Your last project isn't saved. Continue anyway?", list.get(selected).toString(), callback);
                selected = -1;
                return;
            }
        }
        selected = id;
        element = list.get(selected);
        if(!requiresConfirmation) {
            callback.call(true, parent);
            selected = -1;
        }
    }

    @Override
    public boolean canScrollTo(int delta) {
        int n = scrollIndex - delta;
        return n >= 0 && n <= list.size() - displayAmount;
    }

    @Override
    public void onScroll(int delta) {
        scrollIndex -= delta;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        FontRenderer renderer = mc.fontRenderer;
        if(listName != null) {
            int textSize = renderer.getStringWidth(listName);
            drawColorShape(x, y, x + textSize + 6, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
            renderer.drawString(listName, x + 3, y + 4, 0x999999);
        }
        drawColorShape(x, y + 15, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.4F);
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
            renderer.drawString(formatter.apply(list.get(i)), x + 3, yStart + 4, textColor);
        }
        if(list.size() > displayAmount) {
            renderScrollbar();
        }
    }

    public T getSelectedElement() {
        return element;
    }

    void renderScrollbar() {
        int y1 = y + 15;
        int y2 = y + height;
        drawColorShape(x + width - 2, y1, x + width, y2, 0.0F, 0.0F, 0.0F, 1.0F);
        double step = (height - 15.0) / list.size();
        int bar1 = (int) (scrollIndex * step);
        int bar2 = (int) ((displayAmount + scrollIndex) * step);
        drawColorShape(x + width - 2, y1 + bar1, x + width, y1 + bar2, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
