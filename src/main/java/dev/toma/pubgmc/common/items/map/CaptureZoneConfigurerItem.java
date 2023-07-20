package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.client.games.screen.CaptureZoneGui;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CaptureZoneConfigurerItem extends MapPointItem {

    public CaptureZoneConfigurerItem(String name) {
        super(name);
    }

    @Override
    protected boolean filterPoint(GameMapPoint point) {
        return point.is(GameMapPoints.CAPTURE_ZONE);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        GameMap map = context.getMap();
        GameMapPoint point = context.getPoint();
        if (this.filterPoint(point) && !context.isServerCall()) {
            openScreen(map, context.castPoint());
        }
        return EnumActionResult.FAIL;
    }

    @SideOnly(Side.CLIENT)
    private void openScreen(GameMap map, CaptureZonePoint point) {
        Minecraft.getMinecraft().displayGuiScreen(new CaptureZoneGui(map, point));
    }
}
