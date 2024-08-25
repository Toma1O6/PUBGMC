package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

public abstract class MapPointItem extends PMCItem implements dev.toma.pubgmc.api.item.MapPointItem {

    public MapPointItem(String name) {
        super(name);
        this.setCreativeTab(PMCTabs.TAB_MAP);
    }

    public abstract EnumActionResult handlePoiClick(PointClickContext context);

    public abstract EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map);

    protected boolean filterPoint(GameMapPoint point) {
        return true;
    }

    @Override
    public final EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return GameDataProvider.getGameData(worldIn).map(data -> {
            if (!player.isCreative())
                return EnumActionResult.FAIL;
            String activeMap = data.getActiveGameMapName();
            BlockPos interactionPos = pos.up();
            GameMapInstance clickedMap = null;
            for (Map.Entry<String, GameMapInstance> entry : data.getRegisteredGameMaps().entrySet()) {
                if (entry.getKey().equals(activeMap)) {
                    continue;
                }
                GameMapInstance map = entry.getValue();
                Optional<GameMapPoint> point = map.getPointAt(interactionPos);
                Optional<GameMapPoint> pointOpt = map.getPointAt(interactionPos).filter(this::filterPoint);
                if (pointOpt.isPresent()) {
                    return handlePoiClick(new PointClickContext(map, pointOpt.get(), data, player, worldIn, interactionPos, hand, facing, new Vec3d(hitX, hitY, hitZ)));
                }
                if (map.isWithin(pos) && !point.isPresent()) {
                    clickedMap = map;
                }
            }
            if (clickedMap != null) {
                return handlePoiCreation(data, worldIn, interactionPos, player, hand, clickedMap);
            } else if (!worldIn.isRemote) {
                ITextComponent component = new TextComponentTranslation("label.pubgmc.map_configuration.must_use_on_map");
                component.getStyle().setColor(TextFormatting.RED);
                player.sendStatusMessage(component, true);
            }
            return EnumActionResult.PASS;
        }).orElse(EnumActionResult.PASS);
    }

    public static final class PointClickContext {

        private final GameMapInstance map;
        private final GameMapPoint point;
        private final GameData data;
        private final EntityPlayer player;
        private final World world;
        private final BlockPos pos;
        private final EnumHand hand;
        private final EnumFacing facing;
        private final Vec3d hitVec;

        PointClickContext(GameMapInstance map, GameMapPoint point, GameData data, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Vec3d hitVec) {
            this.map = map;
            this.point = point;
            this.data = data;
            this.player = player;
            this.world = world;
            this.pos = pos;
            this.hand = hand;
            this.facing = facing;
            this.hitVec = hitVec;
        }

        public GameMapInstance getMap() {
            return map;
        }

        public GameMapPoint getPoint() {
            return point;
        }

        @SuppressWarnings("unchecked")
        public <G extends GameMapPoint> G castPoint() {
            return (G) point;
        }

        public GameData getData() {
            return data;
        }

        public EntityPlayer getPlayer() {
            return player;
        }

        public World getWorld() {
            return world;
        }

        public BlockPos getPos() {
            return pos;
        }

        public EnumHand getHand() {
            return hand;
        }

        public EnumFacing getFacing() {
            return facing;
        }

        public Vec3d getHitVec() {
            return hitVec;
        }

        public boolean isServerCall() {
            return !world.isRemote;
        }
    }
}
