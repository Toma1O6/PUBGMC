package dev.toma.pubgmc.common.entity.bot;

import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.common.items.equipment.ItemBulletproofArmor;
import dev.toma.pubgmc.common.items.guns.AmmoType;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.heal.ItemHealing;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.TileEntityUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.UUID;

public class EntityAIPlayer extends EntityCreature implements LivingGameEntity, IEntityAdditionalSpawnData {

    private final InventoryBasic inventory = new InventoryBasic("container.aiPlayer", false, 9);
    private int variant;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.enablePersistence();
        this.setSize(0.6F, 1.95F);
        this.variant = worldIn.rand.nextInt(4);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted % 20 == 0) {
            GameHelper.validateGameEntityStillValid(this);
        }
    }

    public void clearAI() {
        // TODO implement
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
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
        // Add Default tasks such as move, loot and shoot
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
        compound.setInteger("variant", this.variant);
        compound.setUniqueId("gameId", gameId);
        compound.setTag("inventory", SerializationHelper.inventoryToNbt(inventory));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        variant = compound.getInteger("variant");
        gameId = compound.getUniqueId("gameId");
        SerializationHelper.inventoryFromNbt(inventory, compound.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(variant);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        variant = additionalData.readInt();
    }

    public InventoryBasic getInventory() {
        return inventory;
    }

    public int getVariant() {
        return variant;
    }

    /**
     * Return value if still needs to loot something (Bigger value = Bigger chance to loot more), >= 10 = needs to loot
     **/
    // TODO rework for loadout system
    public int lootFromLootSpawner(TileEntityLootGenerator lootSpawner) {
        boolean needsGun = !this.hasGun();
        boolean needsHelmet = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
        boolean needsVest = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
        boolean needsMeds = !(this.inventory.getStackInSlot(0).getItem() instanceof ItemHealing);
        boolean needsAmmo = false;
        GunBase lootedGun = null;
        int needToLoot = 0;
        for (int i = 0; i < lootSpawner.getSizeInventory(); i++) {
            ItemStack stack = lootSpawner.getStackInSlot(i);
            if (needsGun) {
                if (stack.getItem() instanceof GunBase && stack.getItem() != PMCItems.FLARE_GUN) {
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
                        if (inventory.getStackInSlot(j).isEmpty()) {
                            inventory.setInventorySlotContents(j, stack.copy());
                            lootSpawner.removeStackFromSlot(i);
                            break;
                        }
                    }
                }
            }
            if (needsHelmet) {
                if (stack.getItem() instanceof ItemBulletproofArmor) {
                    ItemBulletproofArmor armorBase = (ItemBulletproofArmor) stack.getItem();
                    if (armorBase.armorType == EntityEquipmentSlot.HEAD) {
                        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, stack.copy());
                        lootSpawner.removeStackFromSlot(i);
                        needsHelmet = false;
                    }
                }
            }
            if (needsVest) {
                if (stack.getItem() instanceof ItemBulletproofArmor) {
                    ItemBulletproofArmor armorBase = (ItemBulletproofArmor) stack.getItem();
                    if (armorBase.armorType == EntityEquipmentSlot.CHEST) {
                        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, stack.copy());
                        lootSpawner.removeStackFromSlot(i);
                        needsVest = false;
                    }
                }
            }
            if (needsMeds) {
                if (stack.getItem() instanceof ItemHealing) {
                    this.inventory.setInventorySlotContents(0, stack.copy());
                    lootSpawner.removeStackFromSlot(i);
                    needsMeds = false;
                }
            }
        }
        IBlockState state = this.world.getBlockState(lootSpawner.getPos());
        TileEntityUtil.syncToClient(lootSpawner);
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
            ItemStack stack = this.inventory.getStackInSlot(i);
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

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        setDead();
    }
}
