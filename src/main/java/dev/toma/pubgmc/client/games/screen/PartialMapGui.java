package dev.toma.pubgmc.client.games.screen;

import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.client.gui.widget.TextFieldWidget;
import dev.toma.pubgmc.client.gui.widget.VanillaButtonWidget;
import dev.toma.pubgmc.common.games.map.PartialPlayAreaPoint;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.c2s.C2S_PacketAdjustPartialPlayZone;
import dev.toma.pubgmc.util.helper.TextComponentHelper;

import java.util.function.Predicate;

public class PartialMapGui extends MapPointDialogGui<PartialPlayAreaPoint> {

    private TextFieldWidget fromX, fromZ;
    private TextFieldWidget toX, toZ;
    private TextFieldWidget label;
    private VanillaButtonWidget confirmButton;

    private TextFieldWidget[] inputFields;

    public PartialMapGui(GameMapInstance map, PartialPlayAreaPoint point) {
        super(map, point);
        this.setSize(175, 80);
    }

    @Override
    public void init() {
        super.init();
        Position2 min = point.getPositionMin(1.0F);
        Position2 max = point.getPositionMax(1.0F);
        Predicate<Character> textFilter = character -> Character.isDigit(character) || character == '.' || character == '-';
        fromX = addWidget(new TextFieldWidget(left + 5, top + 5, 80, 20, String.valueOf(min.getX()), 16).ghostText("From X"));
        fromX.withValidator(textFilter);
        fromX.withCallback(this::onNumberInputChange);
        fromZ = addWidget(new TextFieldWidget(left + 90, top + 5, 80, 20, String.valueOf(min.getZ()), 16).ghostText("From Z"));
        fromZ.withValidator(textFilter);
        fromZ.withCallback(this::onNumberInputChange);

        toX = addWidget(new TextFieldWidget(left + 5, top + 30, 80, 20, String.valueOf(max.getX()), 16).ghostText("To X"));
        toX.withValidator(textFilter);
        toX.withCallback(this::onNumberInputChange);
        toZ = addWidget(new TextFieldWidget(left + 90, top + 30, 80, 20, String.valueOf(max.getZ()), 16).ghostText("To Z"));
        toZ.withValidator(textFilter);
        toZ.withCallback(this::onNumberInputChange);

        label = addWidget(new TextFieldWidget(left + 5, top + 55, 110, 20, point.getMapName(), 20).ghostText("Name"));
        confirmButton = addWidget(new VanillaButtonWidget(left + 120, top + 55, 50, 20, TextComponentHelper.CONFIRM.getFormattedText(), (widget, mouseX, mouseY, button) -> confirmed()));

        inputFields = new TextFieldWidget[] { fromX, fromZ, toX, toZ, label };
        updateConfirmButtonState();
    }

    @Override
    protected void confirmed() {
        if (this.isValid()) {
            Position2 from = parsePos(fromX, fromZ, point.getPositionMin(1.0F));
            Position2 to = parsePos(toX, toZ, point.getPositionMax(1.0F));
            String label = this.label.getText();
            PacketHandler.sendToServer(new C2S_PacketAdjustPartialPlayZone(ownerMap.getMapName(), point.getPointPosition(), from, to, label));
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

    private Position2 parsePos(TextFieldWidget x, TextFieldWidget z, Position2 original) {
        return new Position2(
                parseDouble(x, original.getX()),
                parseDouble(z, original.getZ())
        );
    }

    private double parseDouble(TextFieldWidget widget, double original) {
        try {
            return Double.parseDouble(widget.getText());
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
