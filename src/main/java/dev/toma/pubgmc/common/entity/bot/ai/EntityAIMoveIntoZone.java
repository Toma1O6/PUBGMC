package dev.toma.pubgmc.common.entity.bot.ai;

import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class EntityAIMoveIntoZone extends EntityAIBase {

    private EntityLiving entity;
    private BlockPos pos;

    private IGameData gameData;

    public EntityAIMoveIntoZone(EntityLiving entity) {
        this.entity = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        this.gameData = this.entity.world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if(this.gameData == null) {
            return false;
        } else if(!this.gameData.getCurrentGame().isRunning() || this.gameData.isInactiveGame()) {
            return false;
        }
        Game game = this.gameData.getCurrentGame();
        BlueZone zone = game.zone;
        if(zone == null || zone.currentBounds == null) {
            return false;
        }
        if(zone.isInsideZone(this.entity)) {
            return false;
        }
        int x = (int)(zone.currentBounds.min().x + (zone.currentBounds.max().x - zone.currentBounds.min().x) / 2);
        int z = (int)(zone.currentBounds.min().z + (zone.currentBounds.max().z - zone.currentBounds.min().z) / 2);
        int y = this.entity.world.getHeight(x, z);
        BlockPos pos = new BlockPos(x, y, z);
        if(!this.entity.world.isBlockLoaded(pos)) {
            double angle = Math.atan2(entity.posZ - z, entity.posX - x);
            double xAdd = Math.sin(angle) * 32;
            double zAdd = Math.cos(angle) * 32;
            x = (int)(entity.posX + xAdd);
            z = (int)(entity.posZ + zAdd);
            pos = new BlockPos(x, this.entity.world.getHeight(x, z), z);
            if(!this.entity.world.isBlockLoaded(pos)) {
                return false;
            }
        }
        this.entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.2D);
        return false;
    }

    @Override
    public void resetTask() {
        this.gameData = null;
        this.pos = null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        if(this.pos == null) return;
        this.entity.getNavigator().tryMoveToXYZ(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1.0D);
    }
}
