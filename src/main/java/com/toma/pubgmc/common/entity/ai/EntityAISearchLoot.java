package com.toma.pubgmc.common.entity.ai;

import com.toma.pubgmc.common.entity.EntityAIPlayer;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityAISearchLoot extends EntityAIBase {

    private EntityAIPlayer aiPlayer;
    private float chance;

    @Nullable
    private BlockPos pos;

    public EntityAISearchLoot(EntityAIPlayer entityAIPlayer, float chance) {
        this.aiPlayer = entityAIPlayer;
        this.chance = chance;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        float f = this.aiPlayer.getRNG().nextFloat();
        if(f <= chance && !(this.aiPlayer.getHeldItemMainhand().getItem() instanceof GunBase)) {
            List<TileEntityLootSpawner> spawners = new ArrayList<>();
            for (TileEntity tileEntity : aiPlayer.world.loadedTileEntityList) {
                if(tileEntity instanceof TileEntityLootSpawner) {
                    spawners.add((TileEntityLootSpawner) tileEntity);
                }
            }
            if(spawners.isEmpty()) return false;
            BlockPos pos = spawners.get(aiPlayer.getRNG().nextInt(spawners.size())).getPos();
            if(pos != null) {
                this.pos = pos;
                return true;
            }
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
        this.aiPlayer.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0D);
    }

    @Override
    public void resetTask() {
        if(PUBGMCUtil.getDistanceToBlockPos(this.aiPlayer.getPosition(), this.pos) > 10) {
            return;
        }
        TileEntity tileEntity = this.aiPlayer.world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityLootSpawner) {
            TileEntityLootSpawner lootSpawner = (TileEntityLootSpawner) tileEntity;
            ItemStack stack = ItemStack.EMPTY;
            for(int i = 0; i < lootSpawner.getSizeInventory(); i++) {
                ItemStack s = lootSpawner.getStackInSlot(i);
                if(s.getItem() instanceof GunBase) {
                    stack = s.copy();
                    lootSpawner.removeStackFromSlot(i);
                    IBlockState state = this.aiPlayer.world.getBlockState(pos);
                    this.aiPlayer.world.notifyBlockUpdate(this.pos, state, state, 3);
                    break;
                }
            }
            if(stack.isEmpty()) {
                EntityAIPlayer.GLOBAL_LOOT_CACHE.get(this.aiPlayer.getUniqueID()).add(this.pos);
            }
            this.pos = null;
            this.aiPlayer.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
        }
    }
}
