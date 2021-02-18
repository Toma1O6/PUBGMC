package dev.toma.pubgmc.common.entity.bot.ai;

import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityAISearchLoot extends EntityAIBase {

    private EntityAIPlayer aiPlayer;
    private final float chance;
    private float modifier;
    private final List<BlockPos> checkedLootSpawners = new ArrayList<>();

    @Nullable
    private BlockPos pos;

    public EntityAISearchLoot(EntityAIPlayer entityAIPlayer, float chance) {
        this.aiPlayer = entityAIPlayer;
        this.chance = chance;
        this.modifier = chance;
        this.setMutexBits(1);
    }

    public BlockPos findNearestUncheckedSpawner() {
        int smallestDist = Integer.MAX_VALUE;
        TileEntityLootGenerator closest = null;
        for(TileEntity tileEntity : this.aiPlayer.world.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityLootGenerator) {
                TileEntityLootGenerator lootSpawner = (TileEntityLootGenerator) tileEntity;
                if(lootSpawner == null || lootSpawner.getPos() == null) continue;
                boolean flag = checkedLootSpawners.contains(lootSpawner.getPos());
                if(flag) continue;
                int distance = (int) PUBGMCUtil.getDistanceToBlockPos3D(this.aiPlayer.getPosition(), lootSpawner.getPos());
                if(distance < smallestDist) {
                    smallestDist = distance;
                    closest = lootSpawner;
                }
            }
        }
        if(closest == null) {
            // disable this task completely
            this.modifier = 0.0F;
            return null;
        }
        return closest.getPos();
    }

    @Override
    public boolean shouldExecute() {
        float f = this.aiPlayer.getRNG().nextFloat();
        if(f <= modifier) {
            BlockPos pos = this.findNearestUncheckedSpawner();
            if(pos == null) {
                return false;
            }
            this.pos = pos;
            return true;
        }
        this.pos = null;
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.aiPlayer.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        if(pos == null) return;
        this.aiPlayer.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY() + 1, pos.getZ(), 1.0D);
    }

    @Override
    public void resetTask() {
        if(PUBGMCUtil.getDistanceToBlockPos(this.aiPlayer.getPosition(), this.pos) > 10) {
            return;
        }
        TileEntity tileEntity = this.aiPlayer.world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityLootGenerator) {
            TileEntityLootGenerator lootSpawner = (TileEntityLootGenerator) tileEntity;
            int i = this.aiPlayer.lootFromLootSpawner(lootSpawner);
            this.modifier = i == 0 ? 0 : i < 5 ? this.chance * 0.1F : i >= 10 ? this.chance * 2.5F : modifier;
            this.checkedLootSpawners.add(this.pos);
            this.pos = null;
        }
    }
}
