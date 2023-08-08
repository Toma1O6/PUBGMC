package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.client.games.screen.PartialMapGui;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.PartialPlayAreaPoint;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class SubMapConfigurerItem extends MapPointItem {

    public SubMapConfigurerItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        GameMapInstance map = context.getMap();
        if (!context.isServerCall()) {
            openScreen(map, context.castPoint());
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map) {
        PartialPlayAreaPoint point = new PartialPlayAreaPoint(pos, map);
        map.setMapPoint(pos, point);
        data.sendGameDataToClients();
        return EnumActionResult.SUCCESS;
    }

    @Override
    protected boolean filterPoint(GameMapPoint point) {
        return point.is(GameMapPoints.PARTIAL_PLAY_AREA);
    }

    @SideOnly(Side.CLIENT)
    private void openScreen(GameMapInstance map, PartialPlayAreaPoint point) {
        Minecraft.getMinecraft().displayGuiScreen(new PartialMapGui(map, point));
    }
}
