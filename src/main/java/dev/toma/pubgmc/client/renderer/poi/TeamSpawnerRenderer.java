package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.util.TeamType;

public class TeamSpawnerRenderer extends SimplePoiRenderer<TeamSpawnerPoint> {

    @Override
    protected int getPrimaryColor(TeamSpawnerPoint point) {
        return point.getTeamType() == TeamType.RED ? 0xFFFF0000 : 0xFF0000FF;
    }

    @Override
    protected int getSecondaryColor(TeamSpawnerPoint point) {
        return point.getTeamType() == TeamType.RED ? 0x44AA0000 : 0x440000AA;
    }
}
