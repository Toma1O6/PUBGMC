package dev.toma.pubgmc.client.games.screen;

import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.client.gui.widget.EnumWidget;
import dev.toma.pubgmc.client.gui.widget.VanillaButtonWidget;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.C2S_AdjustTeamSpawner;
import dev.toma.pubgmc.util.helper.TextComponentHelper;

public class TeamSpawnerGui extends MapPointDialogGui<TeamSpawnerPoint> {

    private EnumWidget<TeamType> teamSelector;

    public TeamSpawnerGui(GameMapInstance map, TeamSpawnerPoint point) {
        super(map, point);
        this.setSize(80, 55);
    }

    @Override
    public void init() {
        super.init();
        teamSelector = addWidget(new EnumWidget<>(left + 5, top + 5, 70, 20, point.getTeamType(), Enum::name));
        addWidget(new VanillaButtonWidget(left + 5, top + 30, 70, 20, TextComponentHelper.CONFIRM.getFormattedText(), (widget, mouseX, mouseY, button) -> confirmed()));
    }

    @Override
    protected void confirmed() {
        PacketHandler.sendToServer(new C2S_AdjustTeamSpawner(ownerMap.getMapName(), point.getPointPosition(), teamSelector.getValue()));
        mc.displayGuiScreen(null);
    }
}
