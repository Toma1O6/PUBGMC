package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.client.games.screen.TeamSpawnerGui;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.util.TeamType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeamSpawnerConfigurerItem extends MapPointItem {

    public TeamSpawnerConfigurerItem(String name) {
        super(name);
    }

    @Override
    protected boolean filterPoint(GameMapPoint point) {
        return point.is(GameMapPoints.TEAM_SPAWNER);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        GameMapInstance map = context.getMap();
        GameMapPoint point = context.getPoint();
        if (this.filterPoint(point) && !context.isServerCall()) {
            openScreen(map, context.castPoint());
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map) {
        TeamSpawnerPoint point = new TeamSpawnerPoint(pos, TeamType.RED);
        map.setMapPoint(pos, point);
        data.sendGameDataToClients();
        return EnumActionResult.SUCCESS;
    }

    @SideOnly(Side.CLIENT)
    private void openScreen(GameMapInstance map, TeamSpawnerPoint point) {
        Minecraft.getMinecraft().displayGuiScreen(new TeamSpawnerGui(map, point));
    }
}
