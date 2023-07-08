package dev.toma.pubgmc.client.gui.hands;

import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.client.renderer.MutableRenderConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiHandPlacer extends GuiWidgets {

    public static MutableRenderConfig left = new MutableRenderConfig();
    public static MutableRenderConfig right = new MutableRenderConfig();
    RenderInfo renderInfo;

    @Override
    public void init() {
        if (renderInfo == null) {
            renderInfo = new RenderInfo(5, 5, 155, 185, this);
        }
        addWidget(new ButtonWidget(width - 145, 5, 140, 20, "Copy to clipboard", (widget, mouseX, mouseY, button) -> {
            String string = "return Pair.of(\n" +
                    left.toString() + ",\n" +
                    right.toString() + "\n);";
            setClipboardString(string);
        }));
        addWidget(new ButtonWidget(width - 200, 5, 50, 20, "Reset", (widget, mouseX, mouseY, button) -> {
            left.reset();
            right.reset();
        }));
        addWidget(renderInfo);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    static class RenderInfo extends FloatingWidget {

        ButtonWidget left;
        ButtonWidget right;
        String handInfo = "Left hand";
        MutableRenderConfig config = GuiHandPlacer.left;

        RenderInfo(int x, int y, int width, int height, GuiScreen parent) {
            super(x, y, width, height, parent);
            refresh();
        }

        void refresh() {
            widgets.clear();
            int half = width / 2;
            left = add(new ButtonWidget(x + 5, y + 10, half - 10, 20, "Left", (widget, mouseX, mouseY, button) -> {
                handInfo = "Left hand";
                config = GuiHandPlacer.left;
                refresh();
            }));
            right = add(new ButtonWidget(x + half + 5, y + 10, half - 10, 20, "Right", (widget, mouseX, mouseY, button) -> {
                handInfo = "Right hand";
                config = GuiHandPlacer.right;
                refresh();
            }));
            add(new NumberClusterWidget(this, x, y + 55, 155, 30, 0.1F, "Position", this::move));
            add(new NumberClusterWidget(this, x, y + 100, 155, 30, 10.0F, "Rotation", this::rotate));
            add(new NumberClusterWidget(this, x, y + 145, 155, 30, 0.1F, "Scale", this::scale));
        }

        void move(int i, MutableRenderConfig cfg, float f) {
            switch (i) {
                case 0:
                    cfg.addPosition(f, 0.0F, 0.0F);
                    break;
                case 1:
                    cfg.addPosition(0.0F, f, 0.0F);
                    break;
                case 2:
                    cfg.addPosition(0.0F, 0.0F, f);
                    break;
            }
        }

        void rotate(int i, MutableRenderConfig cfg, float f) {
            switch (i) {
                case 0:
                    cfg.addRotation(f, 0.0F, 0.0F);
                    break;
                case 1:
                    cfg.addRotation(0.0F, f, 0.0F);
                    break;
                case 2:
                    cfg.addRotation(0.0F, 0.0F, f);
                    break;
            }
        }

        void scale(int i, MutableRenderConfig cfg, float f) {
            switch (i) {
                case 0:
                    cfg.addScale(f, 0.0F, 0.0F);
                    break;
                case 1:
                    cfg.addScale(0.0F, f, 0.0F);
                    break;
                case 2:
                    cfg.addScale(0.0F, 0.0F, f);
                    break;
            }
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
            FontRenderer renderer = mc.fontRenderer;
            renderer.drawString("Mode: " + handInfo, x + 5, y + 33, 0xFFFFFF);
            super.render(mc, mouseX, mouseY, partialTicks);
        }
    }

    static class NumberClusterWidget extends Widget {

        final String name;
        final NumberWidget<MutableRenderConfig> nx;
        final NumberWidget<MutableRenderConfig> ny;
        final NumberWidget<MutableRenderConfig> nz;

        public NumberClusterWidget(RenderInfo info, int x, int y, int width, int height, float value, String name, Applicator applicator) {
            super(x, y, width, height);
            this.name = name;
            this.nx = new NumberWidget<>(x + 5, y + 10, value, "x", info.config, (mutableRenderConfig, f) -> applicator.applyOnObject(0, mutableRenderConfig, f));
            this.ny = new NumberWidget<>(x + 55, y + 10, value, "y", info.config, (mutableRenderConfig, f) -> applicator.applyOnObject(1, mutableRenderConfig, f));
            this.nz = new NumberWidget<>(x + 105, y + 10, value, "z", info.config, (mutableRenderConfig, f) -> applicator.applyOnObject(2, mutableRenderConfig, f));
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            mc.fontRenderer.drawString(name, x + 5, y, 0xFFFFFF);
            nx.render(mc, mouseX, mouseY, partialTicks);
            ny.render(mc, mouseX, mouseY, partialTicks);
            nz.render(mc, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return nx.handleClicked(mouseX, mouseY, button) || ny.handleClicked(mouseX, mouseY, button) || nz.handleClicked(mouseX, mouseY, button);
        }

        interface Applicator {
            void applyOnObject(int id, MutableRenderConfig cfg, float value);
        }
    }

    static class NumberWidget<T> extends Widget {

        final float value;
        final float quarter;
        final float tenth;
        final float percent;
        final String name;
        final ButtonWidget subtract, add;
        final Setter<T> setter;

        NumberWidget(int x, int y, float value, String name, T t, Setter<T> setter) {
            super(x, y, 45, 20);
            this.value = value;
            this.quarter = value * 0.25F;
            this.tenth = value * 0.1F;
            this.percent = value * 0.01F;
            this.name = name;
            this.setter = setter;
            subtract = new ButtonWidget(x, y, 20, 20, "<<", (widget, mouseX, mouseY, button) -> modify(t, -1));
            add = new ButtonWidget(x + 25, y, 20, 20, ">>", (widget, mouseX, mouseY, button) -> modify(t, 1));
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            int tw = mc.fontRenderer.getStringWidth(name);
            mc.fontRenderer.drawString(name, x + (width - tw) / 2.0F, y + height, 0xFFFFFF, false);
            subtract.render(mc, mouseX, mouseY, partialTicks);
            add.render(mc, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return subtract.handleClicked(mouseX, mouseY, button) || add.handleClicked(mouseX, mouseY, button);
        }

        void modify(T t, int i) {
            float f;
            if (GuiScreen.isAltKeyDown()) {
                f = percent * i;
            } else if (GuiScreen.isCtrlKeyDown()) {
                f = tenth * i;
            } else if (GuiScreen.isShiftKeyDown()) {
                f = quarter * i;
            } else {
                f = value * i;
            }
            setter.set(t, f);
        }

        interface Setter<T> {
            void set(T t, float f);
        }
    }

    static class FloatingWidget extends Widget {

        final GuiScreen parent;
        protected final List<Widget> widgets = new ArrayList<>();

        public FloatingWidget(int x, int y, int width, int height, GuiScreen parent) {
            super(x, y, width, height);
            this.parent = parent;
        }

        public <W extends Widget> W add(W widget) {
            widgets.add(widget);
            return widget;
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            widgets.forEach(widget -> widget.render(mc, mouseX, mouseY, partialTicks));
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            widgets.forEach(Widget::unfocus);
            for (Widget widget : widgets) {
                if (widget.handleClicked(mouseX, mouseY, button)) {
                    if (widget.isFocusable()) {
                        widget.focusWidget();
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public void onKeyPress(char character, int keycode) {
            for (Widget widget : widgets) {
                if (widget.handleKeyPressed(character, keycode)) {
                    break;
                }
            }
        }
    }
}
