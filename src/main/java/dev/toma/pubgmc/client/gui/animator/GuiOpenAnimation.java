package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.TextFieldWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class GuiOpenAnimation extends GuiWidgets {

    public static final Pattern PATTERN = Pattern.compile("[a-zA-Z0-9:_\\s]");
    final GuiAnimator parent;
    TextFieldWidget textField;

    public GuiOpenAnimation(GuiAnimator animator) {
        this.parent = animator;
    }

    @Override
    public void init() {
        addWidget(new ButtonWidget(10, height - 25, width - 20, 20, "Back", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(parent)));
        textField = addWidget(new TextFieldWidget(10, 10, width - 20, 20, null, 60).withValidator(c -> PATTERN.matcher(String.valueOf(c)).matches()).ghostText("Filter by name"));
        addWidget(new FilteredStringSelectionWidget(10, 35, width - 20, height - 65));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Widget.drawColorShape(0, 0, width, height, 0.0F, 0.0F, 0.0F, 0.6F);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    class FilteredStringSelectionWidget extends Widget {

        String filter = "";
        int scrollIndex;
        int displayAmount;
        final Set<String> paths;
        protected final List<String> filteredPaths = new ArrayList<>();

        public FilteredStringSelectionWidget(int x, int y, int width, int height) {
            super(x, y, width, height);
            displayAmount = height / 15;
            paths = AnimatorCache.animations.keySet();
            requestUpdate();
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.3F);
            String string = GuiOpenAnimation.this.textField.getText();
            if (!filter.equals(string)) {
                filter = string;
                requestUpdate();
            }
            FontRenderer renderer = mc.fontRenderer;
            for (int i = scrollIndex; i < scrollIndex + displayAmount; i++) {
                if (i >= filteredPaths.size()) break;
                String path = filteredPaths.get(i);
                int j = i - scrollIndex;
                renderer.drawString(path, x + 3, 3 + y + j * 15, 0xFFFFFF);
            }
            if (isMouseOver(mouseX, mouseY)) {
                int clamped = (mouseY - y) / 15;
                int idx = clamped + scrollIndex;
                if (idx < filteredPaths.size()) {
                    if (clamped < displayAmount) {
                        drawColorShape(x, y + clamped * 15, x + width, y + clamped * 15 + 15, 1.0F, 1.0F, 1.0F, 0.3F);
                    }
                }
            }
            renderScrollbar();
        }

        void renderScrollbar() {
            double step = height / (double) filteredPaths.size();
            drawColorShape(x + width + 3, y, x + width + 5, y + height, 0.0F, 0.0F, 0.0F, 1.0F);
            int y1 = y + (int) (scrollIndex * step);
            int y2 = y + Math.min((int) ((scrollIndex + displayAmount) * step), height);
            drawColorShape(x + width + 3, y1, x + width + 5, y2, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        @Override
        public void onClick(int mouseX, int mouseY, int button) {
            int clamped = (mouseY - y) / 15;
            int idx = clamped + scrollIndex;
            if (idx < filteredPaths.size() && clamped < displayAmount) {
                IPopupHandler handler = GuiOpenAnimation.this.parent;
                String key = filteredPaths.get(idx);
                GuiAnimator.WrappedAnimationSpec spec = AnimatorCache.animations.get(key);
                if (spec == null) {
                    AnimatorCache.refreshAnimations(ClientProxy.getAnimationLoader(), handler);
                    mc.displayGuiScreen(GuiOpenAnimation.this.parent);
                    handler.sendError("File {} not found!", key);
                    return;
                }
                AnimatorCache.project = new AnimationProject(spec);
                handler.sendText("File {} has been loaded", key);
                mc.displayGuiScreen(GuiOpenAnimation.this.parent);
            }
        }

        @Override
        public void onScroll(int delta) {
            scrollIndex -= delta;
        }

        @Override
        public boolean canScrollTo(int delta) {
            int n = scrollIndex - delta;
            return n >= 0 && n <= filteredPaths.size() - displayAmount;
        }

        void requestUpdate() {
            filteredPaths.clear();
            scrollIndex = 0;
            if (filter.isEmpty()) {
                filteredPaths.addAll(paths);
            } else {
                for (String str : paths) {
                    if (str.contains(filter)) {
                        filteredPaths.add(str);
                    }
                }
            }
        }
    }
}
