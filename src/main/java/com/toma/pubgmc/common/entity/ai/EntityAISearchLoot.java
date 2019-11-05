package com.toma.pubgmc.common.entity.ai;

import com.toma.pubgmc.common.entity.EntityAIPlayer;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EntityAISearchLoot extends EntityAIBase {

    public static final HashMap<UUID, List<BlockPos>> GLOBAL_LOOT_CACHE = new HashMap<>();

    private EntityAIPlayer aiPlayer;
    private final float chance;
    private float modifier;

    @Nullable
    private BlockPos pos;

    public EntityAISearchLoot(EntityAIPlayer entityAIPlayer, float chance) {
        this.aiPlayer = entityAIPlayer;
        this.chance = chance;
        this.modifier = chance;
        this.setMutexBits(1);
        GLOBAL_LOOT_CACHE.put(entityAIPlayer.getUniqueID(), new ArrayList<>());
    }

    public BlockPos findNearestUncheckedSpawner() {
        List<BlockPos> checkedBlocks = GLOBAL_LOOT_CACHE.get(this.aiPlayer.getUniqueID());
        if(checkedBlocks == null) {
            GLOBAL_LOOT_CACHE.put(this.aiPlayer.getUniqueID(), new ArrayList<>());
            checkedBlocks = GLOBAL_LOOT_CACHE.get(this.aiPlayer.getUniqueID());
        }
        int smallestDist = Integer.MAX_VALUE;
        TileEntityLootSpawner closest = null;
        for(TileEntity tileEntity : this.aiPlayer.world.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityLootSpawner) {
                TileEntityLootSpawner lootSpawner = (TileEntityLootSpawner) tileEntity;
                if(lootSpawner == null || lootSpawner.getPos() == null) continue;
                boolean flag = checkedBlocks.contains(lootSpawner.getPos());
                if(flag) continue;
                int distance = (int)PUBGMCUtil.getDistanceToBlockPos3D(this.aiPlayer.getPosition(), lootSpawner.getPos());
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
        if(tileEntity instanceof TileEntityLootSpawner) {
            TileEntityLootSpawner lootSpawner = (TileEntityLootSpawner) tileEntity;
            int i = this.aiPlayer.lootFromLootSpawner(lootSpawner);
            this.modifier = i == 0 ? 0 : i < 5 ? this.chance * 0.1F : i >= 10 ? this.chance * 2.5F : modifier;
            GLOBAL_LOOT_CACHE.get(this.aiPlayer.getUniqueID()).add(this.pos);
            this.pos = null;
        }
    }
}
