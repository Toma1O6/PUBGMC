package com.toma.pubgmc.common.entity.ai;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.world.BlueZone;
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
        } else if(this.gameData.isInactiveGame()) {
            return false;
        }
        Game game = this.gameData.getCurrentGame();
        BlueZone zone = game.zone;
        if(zone == null || zone.nextBounds == null) {
            return false;
        }
        if(zone.isInsideZone(this.entity)) {
            return false;
        }
        // TODO do not use
        int x = (int)zone.nextBounds.min().x + this.entity.getRNG().nextInt(Math.abs((int)zone.nextBounds.max().x - (int)zone.nextBounds.min().x));
        int z = (int)zone.nextBounds.min().z + this.entity.getRNG().nextInt(Math.abs((int)zone.nextBounds.max().z - (int)zone.nextBounds.min().z));
        int y = this.entity.world.getHeight(x, z);
        this.pos = new BlockPos(x, y, z);
        if(!this.entity.world.isBlockLoaded(this.pos)) {
            return false;
        }
        this.entity.getNavigator().tryMoveToXYZ(x, y, z, 1.2D);
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
