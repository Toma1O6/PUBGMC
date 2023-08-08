package dev.toma.pubgmc.client.games.screen;

import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.client.gui.widget.TextFieldWidget;
import dev.toma.pubgmc.client.gui.widget.VanillaButtonWidget;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.C2S_AdjustCaptureZone;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.util.math.Vec3d;

import java.util.function.Predicate;

public class CaptureZoneGui extends MapPointDialogGui<CaptureZonePoint> {

    private TextFieldWidget nx, ny, nz;
    private TextFieldWidget px, py, pz;
    private TextFieldWidget label;
    private VanillaButtonWidget confirmButton;

    private TextFieldWidget[] inputFields;

    public CaptureZoneGui(GameMapInstance map, CaptureZonePoint point) {
        super(map, point);
        this.setSize(110, 80);
    }

    @Override
    public void init() {
        super.init();
        Vec3d nPos = point.getLeftScale();
        Vec3d pPos = point.getRightScale();
        Predicate<Character> textFilter = character -> Character.isDigit(character) || character == '.';
        nx = addWidget(new TextFieldWidget(left + 5, top + 5, 30, 20, String.valueOf(Math.abs(nPos.x)), 5).ghostText("x"));
        nx.withValidator(textFilter);
        nx.withCallback(this::onNumberInputChange);
        ny = addWidget(new TextFieldWidget(left + 40, top + 5, 30, 20, String.valueOf(Math.abs(nPos.y)), 5).ghostText("y"));
        ny.withValidator(textFilter);
        ny.withCallback(this::onNumberInputChange);
        nz = addWidget(new TextFieldWidget(left + 75, top + 5, 30, 20, String.valueOf(Math.abs(nPos.z)), 5).ghostText("z"));
        nz.withValidator(textFilter);
        nz.withCallback(this::onNumberInputChange);

        px = addWidget(new TextFieldWidget(left + 5, top + 30, 30, 20, String.valueOf(pPos.x), 5).ghostText("x"));
        px.withValidator(textFilter);
        px.withCallback(this::onNumberInputChange);
        py = addWidget(new TextFieldWidget(left + 40, top + 30, 30, 20, String.valueOf(pPos.y), 5).ghostText("y"));
        py.withValidator(textFilter);
        py.withCallback(this::onNumberInputChange);
        pz = addWidget(new TextFieldWidget(left + 75, top + 30, 30, 20, String.valueOf(pPos.z), 5).ghostText("z"));
        pz.withValidator(textFilter);
        pz.withCallback(this::onNumberInputChange);

        label = addWidget(new TextFieldWidget(left + 5, top + 55, 45, 20, point.getLabel() != null ? point.getLabel() : "", 8).ghostText("label"));
        confirmButton = addWidget(new VanillaButtonWidget(left + 55, top + 55, 50, 20, TextComponentHelper.CONFIRM.getFormattedText(), (widget, mouseX, mouseY, button) -> confirmed()));

        inputFields = new TextFieldWidget[] { nx, ny, nz, px, py, pz, label };
        updateConfirmButtonState();
    }

    @Override
    protected void confirmed() {
        if (this.isValid()) {
            Vec3d nPos = parseVec(nx, ny, nz, point.getLeftScale(), true);
            Vec3d pPos = parseVec(px, py, pz, point.getRightScale(), false);
            String label = this.label.getText();
            PacketHandler.sendToServer(new C2S_AdjustCaptureZone(ownerMap.getMapName(), point.getPointPosition(), nPos, pPos, label));
            mc.displayGuiScreen(null);
        }
    }

    private void onNumberInputChange(TextFieldWidget widget) {
        try {
            Double.parseDouble(widget.getText());
            widget.setErrorStatus(false);
        } catch (NumberFormatException e) {
            widget.setErrorStatus(true);
        }
    }

    private void updateConfirmButtonState() {
        confirmButton.setActive(isValid());
    }

    private Vec3d parseVec(TextFieldWidget x, TextFieldWidget y, TextFieldWidget z, Vec3d original, boolean invert) {
        return new Vec3d(
                parseDouble(x, original.x, invert),
                parseDouble(y, original.y, invert),
                parseDouble(z, original.z, invert)
        );
    }

    private double parseDouble(TextFieldWidget widget, double original, boolean invert) {
        try {
            double d = Double.parseDouble(widget.getText());
            if (invert) {
                d = -d;
            }
            return d;
        } catch (NumberFormatException e) {
            return original;
        }
    }

    private boolean isValid() {
        for (TextFieldWidget widget : inputFields) {
            if (widget.isInvalidInput()) {
                return false;
            }
        }
        return true;
    }
}
