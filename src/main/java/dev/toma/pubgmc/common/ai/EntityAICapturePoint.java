package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.CaptureZones;
import dev.toma.pubgmc.api.game.Game;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EntityAICapturePoint extends EntityAIBase {

    private final EntityLiving taskOwner;
    private final Supplier<List<BlockPos>> pointList;

    private BlockPos target;
    private int updateInterval;

    public EntityAICapturePoint(EntityLiving taskOwner, Supplier<List<BlockPos>> pointListProvider) {
        this.taskOwner = taskOwner;
        this.pointList = pointListProvider;
        setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        GameData data = GameDataProvider.getGameData(taskOwner.world).orElse(null);
        if (data == null)
            return false;
        Game<?> activeGame = data.getCurrentGame();
        if (!(activeGame instanceof CaptureZones)) {
            return false;
        }
        CaptureZones zones = (CaptureZones) activeGame;
        updateTarget(zones);
        return target != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return target != null && this.shouldExecute();
    }

    @Override
    public void startExecuting() {
        updateInterval = 20;
        taskOwner.getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), 1.1F);
    }

    @Override
    public void resetTask() {
        target = null;
    }

    @Override
    public void updateTask() {
        if (--updateInterval <= 0) {
            updateInterval = 20;
            GameDataProvider.getGameData(taskOwner.world).ifPresent(data -> {
                Game<?> activeGame = data.getCurrentGame();
                if (!(activeGame instanceof CaptureZones)) {
                    return;
                }
                CaptureZones zones = (CaptureZones) activeGame;
                if (zones.shouldCaptureOrDefend(target, taskOwner)) {
                    target = null;
                    taskOwner.getNavigator().clearPath();
                } else {
                    if (taskOwner.getDistanceSq(target) < 16) {
                        taskOwner.getNavigator().clearPath();
                    } else {
                        taskOwner.getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), 1.1F);
                    }
                }
            });
        }
    }

    private void updateTarget(CaptureZones zones) {
        List<BlockPos> positions = new ArrayList<>();
        List<BlockPos> capturePositions = pointList.get();
        for (BlockPos pos : capturePositions) {
            if (zones.shouldCaptureOrDefend(pos, taskOwner)) {
                positions.add(pos);
            }
        }
        if (positions.isEmpty()) {
            this.target = null;
        } else {
            BlockPos closest = null;
            double dist = Double.MAX_VALUE;
            for (BlockPos pos : positions) {
                double d = taskOwner.getDistanceSq(pos);
                if (d < dist) {
                    closest = pos;
                    dist = d;
                }
            }
            this.target = closest;
        }
    }
}
