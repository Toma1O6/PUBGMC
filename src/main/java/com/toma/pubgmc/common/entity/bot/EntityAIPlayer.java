package com.toma.pubgmc.common.entity.bot;

import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.entity.bot.ai.EntityAIGunAttack;
import com.toma.pubgmc.common.entity.bot.ai.EntityAIMoveIntoZone;
import com.toma.pubgmc.common.entity.bot.ai.EntityAISearchLoot;
import com.toma.pubgmc.common.items.armor.ArmorBase;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.heal.ItemHealing;
import com.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.UUID;

// TODO implement task types
public class EntityAIPlayer extends EntityCreature {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private String hash;
    private int variant;

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.enablePersistence();
        this.setSize(0.6F, 1.95F);
        this.setCanPickUpLoot(true);
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        this.hash = gameData == null ? "empty" : gameData.getGameID();
        this.variant = worldIn.rand.nextInt(4);
    }

    public EntityAIPlayer(World world, BlockPos pos) {
        this(world);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public static NonNullList<ItemStack> getBasicInventory() {
        return NonNullList.withSize(9, ItemStack.EMPTY);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(!world.isRemote) {
            if(ticksExisted % 20 == 0 && !world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getGameID().equals(hash)) {
                this.setDead();
            }
        }
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }

    // TODO check if death crate can be spawned
    @Override
    public void onDeath(DamageSource cause) {
        UUID uuid = this.getUniqueID();
        EntityAISearchLoot.GLOBAL_LOOT_CACHE.remove(uuid);
        super.onDeath(cause);
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    protected boolean canDropLoot() {
        return false;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIGunAttack(this));
        this.tasks.addTask(3, new EntityAISearchLoot(this, 0.05F));
        this.tasks.addTask(4, new EntityAIMoveIntoZone(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D, 0.0001F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10, true, false, EntitySelectors.IS_ALIVE));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAIPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128.0D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setString("hash", this.hash);
        compound.setInteger("variant", this.variant);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.hash = compound.hasKey("hash") ? compound.getString("hash") : "empty";
        this.variant = compound.getInteger("variant");
    }

    public int getVariant() {
        return variant;
    }

    /**
     * Return value if still needs to loot something (Bigger value = Bigger chance to loot more), >= 10 = needs to loot
     **/
    public int lootFromLootSpawner(TileEntityLootGenerator lootSpawner) {
        boolean needsGun = !this.hasGun();
        boolean needsHelmet = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
        boolean needsVest = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
        boolean needsMeds = !(this.inventory.get(0).getItem() instanceof ItemHealing);
        boolean needsAmmo = false;
        GunBase lootedGun = null;
        int needToLoot = 0;
        for (int i = 0; i < lootSpawner.getSizeInventory(); i++) {
            ItemStack stack = lootSpawner.getStackInSlot(i);
            if (needsGun) {
                if (stack.getItem() instanceof GunBase && stack.getItem() != PMCRegistry.PMCItems.FLARE_GUN) {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    EntityItem item = new EntityItem(world, this.posX, this.posY, this.posZ, stack.copy());
                    item.setPickupDelay(0);
                    this.world.spawnEntity(item);
                    lootedGun = (GunBase) stack.getItem();
                    needsGun = false;
                    needsAmmo = true;
                    lootSpawner.removeStackFromSlot(i);
                }
            }
            if (needsAmmo || (this.hasGun() && this.needsAmmo())) {
                GunBase gun = lootedGun != null ? lootedGun : this.getGun();
                if (gun.getAmmoType().ammo() == stack.getItem()) {
                    for (int j = 1; j < 6; j++) {
                        if (inventory.get(j).isEmpty()) {
                            inventory.set(j, stack.copy());
                            lootSpawner.removeStackFromSlot(i);
                            break;
                        }
                    }
                }
            }
            if (needsHelmet) {
                if (stack.getItem() instanceof ArmorBase) {
                    ArmorBase armorBase = (ArmorBase) stack.getItem();
                    if (armorBase.getEquipmentSlot() == EntityEquipmentSlot.HEAD) {
                        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, stack.copy());
                        lootSpawner.removeStackFromSlot(i);
                        needsHelmet = false;
                    }
                }
            }
            if (needsVest) {
                if (stack.getItem() instanceof ArmorBase) {
                    ArmorBase armorBase = (ArmorBase) stack.getItem();
                    if (armorBase.getEquipmentSlot() == EntityEquipmentSlot.CHEST) {
                        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, stack.copy());
                        lootSpawner.removeStackFromSlot(i);
                        needsVest = false;
                    }
                }
            }
            if (needsMeds) {
                if (stack.getItem() instanceof ItemHealing) {
                    this.inventory.set(0, stack.copy());
                    lootSpawner.removeStackFromSlot(i);
                    needsMeds = false;
                }
            }
        }
        IBlockState state = this.world.getBlockState(lootSpawner.getPos());
        this.world.notifyBlockUpdate(lootSpawner.getPos(), state, state, 3);
        needToLoot = needToLoot + (needsGun ? 10 : 0) + (needsAmmo() ? 10 : 0) + (needsHelmet ? 5 : 0) + (needsVest ? 5 : 0) + (needsMeds ? 3 : 0);
        return needToLoot;
    }

    protected boolean needsAmmo() {
        if (!this.hasGun()) {
            return false;
        }
        int totalAmmoCount = 0;
        AmmoType ammoType = this.getGun().getAmmoType();
        for (int i = 1; i < 6; i++) {
            ItemStack stack = this.inventory.get(i);
            if (ammoType.ammo() == stack.getItem()) {
                totalAmmoCount += stack.getCount();
            }
        }
        return totalAmmoCount < 60;
    }

    public boolean hasGun() {
        return this.getHeldItemMainhand().getItem() instanceof GunBase;
    }

    public GunBase getGun() {
        return (GunBase) this.getHeldItemMainhand().getItem();
    }
}
