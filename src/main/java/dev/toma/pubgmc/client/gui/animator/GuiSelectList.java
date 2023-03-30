package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.gui.GuiConfirm;

import java.util.List;
import java.util.function.Consumer;

public class GuiSelectList<T> extends GuiConfirm {

    private final List<T> list;
    private final Callback<T> callback;
    protected ListWidget<T> widget;
    protected Consumer<ListWidget<T>> createCallback = tListWidget -> {};

    public GuiSelectList(GuiAnimator parent, String title, String desc, List<T> list, Callback<T> callback) {
        super(parent, title, desc, null);
        this.list = list;
        this.callback = callback;
    }

    public GuiSelectList<T> onListInit(Consumer<ListWidget<T>> createCallback) {
        this.createCallback = createCallback;
        return this;
    }

    @Override
    public void init() {
        super.init();
        widget = addWidget(new ListWidget<>((GuiAnimator) parent, guiLeft + 5, guiTop + 20, xSize - 10, ySize - 50, list, null, (b, s) -> {
            if(b)
                onConfirm();
            else
                onCancel();
        }));
        createCallback.accept(widget);
    }

    @Override
    protected void onConfirm() {
        callback.call(true, (GuiAnimator) parent, widget.getSelectedElement());
    }

    @Override
    protected void onCancel() {
        callback.call(false, (GuiAnimator) parent, widget.getSelectedElement());
    }

    public interface Callback<T> {
        void call(boolean confirm, GuiAnimator parent, T t);
    }
}
