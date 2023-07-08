package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.CheckboxWidget;
import dev.toma.pubgmc.client.gui.widget.InputFieldWidget;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiConnectAnimations extends GuiWidgets {

    final GuiAnimator parent;
    final List<ConnectionSpec> specs = new ArrayList<>();
    CheckboxWidget consumeFrames;

    public GuiConnectAnimations(GuiAnimator animator) {
        this.parent = animator;
    }

    @Override
    public void init() {
        int j = 0;
        for (ConnectionSpec spec : specs) {
            SpecWidget widget = new SpecWidget(10, 10 + j * 25, width - 20, 20, spec);
            widget.addWidgets(this);
            ++j;
        }
        if (j < 8) {
            addWidget(new ButtonWidget(10, 10 + j * 25, 60, 20, "Add", (widget, mouseX, mouseY, button) -> {
                ConnectionSpec spec = new ConnectionSpec(1.0F, 1, AnimationSpec.EMPTY_SPEC);
                specs.add(spec);
                initGui();
            }));
        }
        addWidget(new ButtonWidget(75, 10 + j * 25, 80, 20, "Convert", (widget, mouseX, mouseY, button) -> {
            AnimationSpec spec = convert();
            String name = "converted";
            GuiAnimator.WrappedAnimationSpec wrappedAnimationSpec = new GuiAnimator.WrappedAnimationSpec(name, spec);
            AnimatorCache.project = new AnimationProject(wrappedAnimationSpec);
            mc.displayGuiScreen(parent);
        }));
        addWidget(new ButtonWidget(160, 10 + j * 25, 60, 20, "Back", (widget, mouseX, mouseY, button) -> mc.displayGuiScreen(parent)));
        consumeFrames = addWidget(new CheckboxWidget(225, 10 + j * 25, 80, 20, "Ignore first frames", (state, mouseX, mouseY, widget) -> {
        }, 20));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    protected void delete(ConnectionSpec spec) {
        specs.remove(spec);
        initGui();
    }

    public static AnimationSpec convert(List<ConnectionSpec> specs, boolean consumeFrames) {
        Map<AnimationElement, List<KeyFrame>> map = new HashMap<>();
        float min = 0.0F;
        int j = 0;
        for (ConnectionSpec connectionSpec : normalize(specs)) {
            AnimationSpec animSpec = connectionSpec.animationSpec;
            float max = connectionSpec.endpoint;
            for (Map.Entry<AnimationElement, List<KeyFrame>> entry : animSpec.getFrameDefs().entrySet()) {
                List<KeyFrame> modifiedList = map.computeIfAbsent(entry.getKey(), element -> new ArrayList<>());
                if (consumeFrames && j > 0 && modifiedList.size() > 1) {
                    modifiedList.remove(0);
                }
                for (KeyFrame frame : entry.getValue()) {
                    float end = frame.endPoint();
                    float modifiedEnd = (max - min) * end + min;
                    boolean hasRotation = !frame.rotateTarget().equals(Vec3d.ZERO);
                    modifiedList.add(hasRotation ? KeyFrame.rotate(modifiedEnd, frame.moveTarget(), frame.rotateTarget()) : KeyFrame.move(modifiedEnd, frame.moveTarget()));
                }
            }
            min = max;
            ++j;
        }
        return new AnimationSpec(map);
    }

    private AnimationSpec convert() {
        return convert(specs, consumeFrames.isSelected());
    }

    public static List<ConnectionSpec> normalize(List<ConnectionSpec> specs) {
        List<ConnectionSpec> list = new ArrayList<>();
        float lastEnd = 0.0F;
        for (ConnectionSpec spec : specs) {
            if (spec.repeatCount > 1) {
                float pool = spec.endpoint - lastEnd;
                double modifier = 1.0 / spec.repeatCount;
                for (int i = 1; i <= spec.repeatCount; i++) {
                    double d = modifier * i;
                    float endpoint = lastEnd + (float) (pool * d);
                    ConnectionSpec copy = new ConnectionSpec(endpoint, 1, spec.animationSpec);
                    list.add(copy);
                }
            } else {
                list.add(spec);
            }
            lastEnd = spec.endpoint;
        }
        return list;
    }

    private class SpecWidget extends Widget {

        final ConnectionSpec spec;
        InputFieldWidget.StringFieldWidget name;
        InputFieldWidget.DoubleFieldWidget endpoint;
        InputFieldWidget.IntegerFieldWidget repeats;
        ButtonWidget delete;

        private SpecWidget(int x, int y, int width, int height, ConnectionSpec spec) {
            super(x, y, width, height);
            this.spec = spec;
            this.name = new InputFieldWidget.StringFieldWidget(x, y, 200, 20, "", this::validateAnimation).charValidator(GuiOpenAnimation.PATTERN);
            name.setBackgroundText("Animation path");
            this.endpoint = new InputFieldWidget.DoubleFieldWidget(x + 205, y, 50, 20, spec.endpoint, 0.01, 1.0, this::setEndpoint);
            endpoint.setBackgroundText("0.0-1.0");
            this.repeats = new InputFieldWidget.IntegerFieldWidget(x + 260, y, 50, 20, spec.repeatCount, 1, 15, this::setRepeats);
            repeats.setBackgroundText("Repeats");
            this.delete = new ButtonWidget(x + 315, y, width - 315, 20, "Delete", (widget, mouseX, mouseY, button) -> GuiConnectAnimations.this.delete(spec));
        }

        void addWidgets(GuiWidgets widgets) {
            widgets.addWidget(name);
            widgets.addWidget(endpoint);
            widgets.addWidget(repeats);
            widgets.addWidget(delete);
        }

        @Override
        public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        }

        @Override
        public boolean handleClicked(int mouseX, int mouseY, int button) {
            return false;
        }

        private void validateAnimation(InputFieldWidget<String> widget, String key) {
            GuiAnimator.WrappedAnimationSpec specContainer = AnimatorCache.animations.get(key);
            if (specContainer == null) {
                widget.invalidate();
                return;
            }
            this.spec.animationSpec = specContainer.spec;
        }

        private void setEndpoint(InputFieldWidget<Double> field, double value) {
            this.spec.endpoint = (float) value;
        }

        private void setRepeats(InputFieldWidget<Integer> field, int value) {
            this.spec.repeatCount = value;
        }
    }

    public static class ConnectionSpec {

        private float endpoint;
        private int repeatCount;
        private AnimationSpec animationSpec;

        public ConnectionSpec(float endpoint, int repeatCount, AnimationSpec spec) {
            this.endpoint = endpoint;
            this.repeatCount = repeatCount;
            this.animationSpec = spec;
        }
    }
}
