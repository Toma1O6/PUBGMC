package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.interfaces.ElementProvider;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3d;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class GuiModifyFrame extends GuiWidgets {

    int guiLeft;
    int guiTop;
    int xSize;
    int ySize;
    final GuiAnimator parent;
    final MutableKeyFrame frame;
    final AnimationElement frameOwner;

    public GuiModifyFrame(GuiAnimator animator, MutableKeyFrame frame, AnimationElement frameOwner) {
        this.parent = animator;
        this.frame = frame;
        this.frameOwner = frameOwner;
        this.xSize = 170;
        this.ySize = 100;
    }

    @Override
    public void init() {
        this.guiLeft = (width - xSize) / 2;
        this.guiTop = 0;

        addWidget(new DecimalInputWidget(guiLeft + 5, guiTop + 15, 50, 20, frame, () -> frame.move.x, (frame1, value) -> frame1.setMove(new Vec3d(value, frame1.move.y, frame1.move.z))));
        addWidget(new DecimalInputWidget(guiLeft + 60, guiTop + 15, 50, 20, frame, () -> frame.move.y, (frame1, value) -> frame1.setMove(new Vec3d(frame1.move.x, value, frame1.move.z))));
        addWidget(new DecimalInputWidget(guiLeft + 115, guiTop + 15, 50, 20, frame, () -> frame.move.z, (frame1, value) -> frame1.setMove(new Vec3d(frame1.move.x, frame1.move.y, value))));

        addWidget(new DecimalInputWidget(guiLeft + 5, guiTop + 50, 50, 20, frame, () -> frame.rotate.x, (frame1, value) -> frame1.setRotate(new Vec3d(value, frame1.rotate.y, frame1.rotate.z))));
        addWidget(new DecimalInputWidget(guiLeft + 60, guiTop + 50, 50, 20, frame, () -> frame.rotate.y, (frame1, value) -> frame1.setRotate(new Vec3d(frame1.rotate.x, value, frame1.rotate.z))));
        addWidget(new DecimalInputWidget(guiLeft + 115, guiTop + 50, 50, 20, frame, () -> frame.rotate.z, (frame1, value) -> frame1.setRotate(new Vec3d(frame1.rotate.x, frame1.rotate.y, value))));

        addWidget(new ButtonWidget(guiLeft + 5, guiTop + ySize - 25, 50, 20, "Continue", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(parent)));
        addWidget(new ButtonWidget(guiLeft + 60, guiTop + ySize - 25, 50, 20, "Delete", (widget, mouseX, mouseY, button) -> {
            AnimatorCache.project.remove(frameOwner, frame);
            mc.displayGuiScreen(parent);
        }));
        addWidget(new ButtonWidget(guiLeft + 115, guiTop + ySize - 25, 50, 20, "Copy", (widget, mouseX, mouseY, button) -> {
            Item item = mc.player.getHeldItemMainhand().getItem();
            List<AnimationElement> list = new ArrayList<>(AnimationElement.getBaseElements());
            if(item.getTileEntityItemStackRenderer() instanceof ElementProvider) {
                ElementProvider provider = (ElementProvider) item.getTileEntityItemStackRenderer();
                list.addAll(provider.getElements());
            }
            list.removeIf(element -> element == frameOwner);
            mc.displayGuiScreen(new ElementList(parent, list, frame).onListInit(listWidget -> listWidget.withFormatter(AnimationElement::getLocalizedName)));
        }));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        parent.drawScreen(mouseX, mouseY, partialTicks);
        Widget.drawColorShape(guiLeft, guiTop, guiLeft + xSize, guiTop + ySize, 0.0F, 0.0F, 0.0F, 0.6F);
        FontRenderer renderer = mc.fontRenderer;
        renderer.drawStringWithShadow("Position", guiLeft + 5, guiTop + 5, 0xFFFFFF);
        renderer.drawStringWithShadow("Rotation", guiLeft + 5, guiTop + 40, 0xFFFFFF);
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    static class DecimalInputWidget extends Widget {

        static final DecimalFormat NUMBER_FORMAT;
        static final Pattern DECIMAL_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        static final Pattern CHARACTER_PATTERN = Pattern.compile("[0-9.-]");
        final MutableKeyFrame frame;
        String num;
        boolean valid;
        final Setter setter;

        public DecimalInputWidget(int x, int y, int width, int height, MutableKeyFrame frame, Supplier<Double> supplier, Setter setter) {
            super(x, y, width, height);
            this.frame = frame;
            this.setter = setter;
            num = NUMBER_FORMAT.format(supplier.get());
            validateAndSet();
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if(valid) {
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
            } else {
                drawColorShape(x, y, x + width, y + height, 0.8F, 0.0F, 0.0F, 1.0F);
            }
            drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
            mc.fontRenderer.drawString(num, x + 3, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF, false);
        }

        @Override
        public boolean isFocusable() {
            return true;
        }

        @Override
        public void onKeyPress(char character, int keycode) {
            if(keycode == 14 && !num.isEmpty()) {
                num = num.substring(0, num.length() - 1);
                validateAndSet();
            } else {
                if(CHARACTER_PATTERN.matcher(String.valueOf(character)).matches()) {
                    int i = num.replaceAll("[-.]", "").length();
                    if(i < 6) {
                        num += character;
                        validateAndSet();
                    }
                }
            }
        }

        void validateAndSet() {
            try {
                if(DECIMAL_PATTERN.matcher(num).matches()) {
                    double d = Double.parseDouble(num);
                    setter.set(frame, d);
                    valid = true;
                } else
                    valid = false;
            } catch (NumberFormatException nfe) {
                valid = false;
            }
        }

        static {
            NUMBER_FORMAT = new DecimalFormat("#.####");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            NUMBER_FORMAT.setDecimalFormatSymbols(symbols);
        }

        interface Setter {
            void set(MutableKeyFrame frame, double value);
        }
    }

    static class ElementList extends GuiSelectList<AnimationElement> {

        final KeyFrame frame;

        ElementList(GuiAnimator parent, List<AnimationElement> elementList, KeyFrame frame) {
            super(parent, "Select target element", "", elementList, null);
            this.frame = frame;
        }

        @Override
        protected void onConfirm() {
            GuiAnimator animator = (GuiAnimator) parent;
            AnimationElement element = widget.getSelectedElement();
            if(element != null) {
                animator.timeline.insertElement(element, MutableKeyFrame.fromImmutable(frame));
            }
            mc.displayGuiScreen(parent);
        }

        @Override
        protected void onCancel() {
            mc.displayGuiScreen(parent);
        }
    }
}
