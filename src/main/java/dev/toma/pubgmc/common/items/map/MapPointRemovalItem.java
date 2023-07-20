package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import net.minecraft.util.EnumActionResult;

public class MapPointRemovalItem extends MapPointItem {

    public MapPointRemovalItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        if (context.isServerCall()) {
            GameMap map = context.getMap();
            GameMapPoint point = context.getPoint();
            map.deletePoiAt(point.getPointPosition());
            context.getData().sendGameDataToClients();
        }
        return EnumActionResult.SUCCESS;
    }
}
