package com.toma.pubgmc.common.tileentity;

import com.toma.pubgmc.api.IGameTileEntity;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.blocks.BlockLootSpawner.LootType;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.util.TileEntitySync;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO stop using IInventory
public class TileEntityLootSpawner extends TileEntitySync implements IInventory, IGameTileEntity {
    //Loot related - guns
    private static final List<ItemStack> PISTOLS = new ArrayList<>();
    private static final List<ItemStack> SHOTGUNS = new ArrayList<>();
    private static final List<ItemStack> SMGS = new ArrayList<>();
    private static final List<ItemStack> ARS = new ArrayList<>();
    private static final List<ItemStack> DMRS = new ArrayList<>();
    private static final List<ItemStack> SRS = new ArrayList<>();
    //healing
    private static final List<ItemStack> COMMON_HEAL = new ArrayList<>();
    private static final List<ItemStack> RARE_HEAL = new ArrayList<>();
    //Wearable
    private static final List<ItemStack> WEARABLE = new ArrayList<>();
    private static final List<ItemStack> BACKPACKS = new ArrayList<>();
    //Ammo & grenades
    private static final List<ItemStack> AMMO = new ArrayList<>();
    private static final List<ItemStack> THROWABLES = new ArrayList<>();
    //Attachments
    private static final List<ItemStack> ATTACHMENTS = new ArrayList<>();
    private static final List<ItemStack> BARREL_ATT = new ArrayList<>();
    private static final List<ItemStack> GRIP_ATT = new ArrayList<>();
    private static final List<ItemStack> SCOPE_ATT = new ArrayList<>();
    private static final List<ItemStack> MAG_ATT = new ArrayList<>();
    private static final List<ItemStack> STOCK_ATT = new ArrayList<>();
    private final Random rand = new Random();
    private NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private String customName;
    private int slot;
    private boolean randomAmmo = false;
    private List<GunType> weapons = new ArrayList<GunType>();
    private String gameID = "EMPTY";

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.lootspawner";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack) this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = (ItemStack) this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
        if (index == 0 && index + 1 == 1 && !flag) {
            ItemStack stack1 = (ItemStack) this.inventory.get(index + 1);

        }
    }

    //To keep all items when state changes
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);

        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
        gameID = compound.hasKey("gameID") ? compound.getString("gameID") : "";
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);

        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        compound.setString("gameID", gameID);
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    public String getGuiID() {
        return Pubgmc.MOD_ID + ":loot_spawner";
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public String getGameHash() {
        return gameID;
    }

    @Override
    public void setGameHash(String hash) {
        this.gameID = hash;
    }

    @Override
    public void onLoaded() {
        TileEntityLootSpawner te = TileEntityLootSpawner.this;
        IWorldData data = te.world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        te.generateLoot(data.hasAirdropWeapons(), data.isAmmoLootEnabled(), data.isRandomAmmoCountEnabled(), data.getLootChanceMultiplier(), data.getWeaponList());
        world.notifyBlockUpdate(pos, PMCRegistry.PMCBlocks.LOOT_SPAWNER.getDefaultState(), PMCRegistry.PMCBlocks.LOOT_SPAWNER.getDefaultState(), 3);
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    /**
     * Generate loot
     *
     * @param airdroploot - enable airdrop weapons in the loot + awm ammo will spawn
     * @param addAmmo     - Decides if ammo will be generated with guns
     */
    public void generateLoot(boolean airdroploot, boolean addAmmo, boolean randomAmmo, double chanceMultiplier, List<GunType> weaponList) {
        LootType type = LootType.getTypeFromState(world.getBlockState(pos));
        slot = -1;
        this.inventory.clear();
        this.weapons = weaponList;
        this.randomAmmo = randomAmmo;

        addGrenadeLoot();
        addBackpackLoot(rand.nextInt(26));
        addCommonHealing();
        addWearableLoot(rand.nextInt(26));
        addAmmoLoot(randomAmmo, airdroploot);

        addRareHealing(rand.nextInt(12));

        //Rare meds
        if (Math.random() * 100 <= 10 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), RARE_HEAL.get(rand.nextInt(RARE_HEAL.size())));
        }

        if (Math.random() * 100 <= 45 * chanceMultiplier * type.getLootMultiplier()) {
            //Actual gun gen
            if (ConfigPMC.common.world.enableGunLoot) {
                //Flare gun 0.5% spawn
                if (Math.random() * 100 <= 0.5 * chanceMultiplier * type.getLootMultiplier()) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.FLARE_GUN));
                    if (addAmmo) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_FLARE, 1));
                    }
                }

                //Sniper rifles 2% spawn, airdrop wep disabled
                else if (Math.random() * 100 <= 2 * chanceMultiplier * type.getLootMultiplier() && weapons.contains(GunType.SR)) {
                    addSRs(airdroploot);

                    setInventorySlotContents(getEmptySlot(), SRS.get(rand.nextInt(SRS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }

                //DMRs 3% spawn, airdrop wep disabled
                else if (Math.random() * 100 <= 3 * chanceMultiplier * type.getLootMultiplier() && weapons.contains(GunType.DMR)) {
                    addDMRs(airdroploot);

                    setInventorySlotContents(getEmptySlot(), DMRS.get(rand.nextInt(DMRS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }

                //Assault rifles 15% spawn, airdrop wep disabled
                else if (Math.random() * 100 <= 15 * chanceMultiplier * type.getLootMultiplier() && weapons.contains(GunType.AR)) {
                    addARs(airdroploot);

                    setInventorySlotContents(getEmptySlot(), ARS.get(rand.nextInt(ARS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }

                //SMGs 20% spawn
                else if (Math.random() * 100 <= 20 * chanceMultiplier * type.getLootMultiplier() && weapons.contains(GunType.SMG)) {
                    addSMGs();

                    setInventorySlotContents(getEmptySlot(), SMGS.get(rand.nextInt(SMGS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }

                //Shotguns 35% spawn
                else if (Math.random() * 100 <= 35 * chanceMultiplier * type.getLootMultiplier() && weapons.contains(GunType.SHOTGUN)) {
                    addShotguns();

                    setInventorySlotContents(getEmptySlot(), SHOTGUNS.get(rand.nextInt(SHOTGUNS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }

                //If none of the above is successful then pistol will be generated
                else if (weapons.contains(GunType.PISTOL)) {
                    addPistols();

                    setInventorySlotContents(getEmptySlot(), PISTOLS.get(rand.nextInt(PISTOLS.size())));
                    if (addAmmo) {
                        generateAmmoForCurrentWeapon(getStackInSlot(slot));
                    }
                }
            }
        }

        if (Math.random() * 100 <= 15 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), THROWABLES.get(rand.nextInt(THROWABLES.size())));
        } else if (Math.random() * 100 <= 20 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), WEARABLE.get(rand.nextInt(WEARABLE.size())));
        } else if (Math.random() * 100 <= 15 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), BACKPACKS.get(rand.nextInt(BACKPACKS.size())));
        } else if (Math.random() * 100 <= 25 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), COMMON_HEAL.get(rand.nextInt(COMMON_HEAL.size())));
        } else if (Math.random() * 100 <= 35 * chanceMultiplier * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), AMMO.get(rand.nextInt(AMMO.size())));
        }

        if (Math.random() * 100 <= 20 * chanceMultiplier * type.getLootMultiplier()) {
            addAttachments(airdroploot);
            setInventorySlotContents(getEmptySlot(), ATTACHMENTS.get(rand.nextInt(ATTACHMENTS.size())));
        }

        if (Math.random() <= 0.05 * type.getLootMultiplier()) {
            setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.FUELCAN));
        }
    }

    private int getEmptySlot() {
        slot++;
        return slot;
    }

    /**
     * Generate random ammo based on the weapon
     **/
    private void generateAmmoForCurrentWeapon(ItemStack stack) {
        GunBase gun = (GunBase) stack.getItem();
        int pistolAmmoCount = 15;
        int defAR = 30;
        int shotguns = 5;
        int awm = 10;

        if (randomAmmo) {
            pistolAmmoCount = rand.nextInt(16);
            defAR = rand.nextInt(31);
            shotguns = rand.nextInt(6);
            awm = rand.nextInt(11);
        }

        if (gun == PMCRegistry.PMCItems.KAR98K || gun == PMCRegistry.PMCItems.M24) {
            setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));

            if (Math.random() * 100 <= 75) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));

                if (Math.random() * 100 <= 25) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));
                }
            }

            return;
        }

        if (gun.getGunType() != GunType.PISTOL) {
            if (gun.getAmmoType() == AmmoType.AMMO9MM) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, defAR));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, defAR));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, defAR));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO45ACP) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, defAR));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, defAR));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, defAR));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO12G) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_SHOTGUN, shotguns));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_SHOTGUN, shotguns));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_SHOTGUN, shotguns));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO556) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_556, defAR));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_556, defAR));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_556, defAR));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO762) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, defAR));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, defAR));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, defAR));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO300M) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_300M, awm));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_300M, awm));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_300M, awm));
                    }
                }
            }
        } else {
            if (gun.getAmmoType() == AmmoType.AMMO9MM) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, pistolAmmoCount));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, pistolAmmoCount));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, pistolAmmoCount));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO45ACP) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, pistolAmmoCount));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, pistolAmmoCount));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, pistolAmmoCount));
                    }
                }
            }

            if (gun.getAmmoType() == AmmoType.AMMO762) {
                setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));
                if (Math.random() * 100 <= 75) {
                    setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));

                    if (Math.random() * 100 <= 25) {
                        setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, pistolAmmoCount));
                    }
                }
            }
        }
    }

    /**
     * Generate random grenade
     */
    private void addGrenadeLoot() {
        THROWABLES.clear();

        THROWABLES.add(new ItemStack(PMCRegistry.PMCItems.GRENADE));
        THROWABLES.add(new ItemStack(PMCRegistry.PMCItems.MOLOTOV));
        THROWABLES.add(new ItemStack(PMCRegistry.PMCItems.SMOKE));
    }

    /**
     * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
     */
    private void addWearableLoot(int chance) {
        WEARABLE.clear();
        WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR1BODY));
        WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR1HELMET));

        if (chance >= 10) {
            WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR2BODY));
            WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR2HELMET));
            WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.NV_GOGGLES));

            if (chance >= 25) {
                WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR3BODY));
                WEARABLE.add(new ItemStack(PMCRegistry.PMCItems.ARMOR3HELMET));
            }
        }
    }

    /**
     * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
     */
    private void addBackpackLoot(int chance) {
        BACKPACKS.clear();

        BACKPACKS.add(new ItemStack(PMCRegistry.PMCItems.BACKPACK1));

        if (chance >= 10) {
            BACKPACKS.add(new ItemStack(PMCRegistry.PMCItems.BACKPACK2));

            if (chance >= 25) {
                BACKPACKS.add(new ItemStack(PMCRegistry.PMCItems.BACKPACK3));
            }
        }
    }

    /**
     * Generate random ammo
     *
     * @param randomCount - generate random count of the ammo
     */
    private void addAmmoLoot(boolean randomCount, boolean airdrop) {
        AMMO.clear();

        if (randomCount) {
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, rand.nextInt(30)));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, rand.nextInt(30)));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_SHOTGUN, rand.nextInt(10)));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_556, rand.nextInt(30)));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_762, rand.nextInt(30)));

            if (Math.random() * 100 <= 3) {
                AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_FLARE, 1));
            }

            if (airdrop) {
                AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_300M, rand.nextInt(6)));
            }
        } else {
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_9MM, 30));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_45ACP, 30));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_SHOTGUN, 10));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_556, 30));
            AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_762, 30));

            if (Math.random() * 100 <= 3) {
                AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_FLARE, 1));
            }

            if (airdrop) {
                AMMO.add(new ItemStack(PMCRegistry.PMCItems.AMMO_300M, 5));
            }
        }
    }

    /**
     * First aid loot gen
     *
     * @param chance - +10 for medkits and syringes
     */
    private void addRareHealing(int chance) {
        RARE_HEAL.clear();

        RARE_HEAL.add(new ItemStack(PMCRegistry.PMCItems.FIRSTAIDKIT));

        if (chance >= 10) {
            RARE_HEAL.add(new ItemStack(PMCRegistry.PMCItems.MEDKIT));
            RARE_HEAL.add(new ItemStack(PMCRegistry.PMCItems.ADRENALINESYRINGE));
        }
    }

    private void addCommonHealing() {
        COMMON_HEAL.clear();

        COMMON_HEAL.add(new ItemStack(PMCRegistry.PMCItems.BANDAGE, 5));
        COMMON_HEAL.add(new ItemStack(PMCRegistry.PMCItems.ENERGYDRINK));
        COMMON_HEAL.add(new ItemStack(PMCRegistry.PMCItems.PAINKILLERS));
    }

    private void addPistols() {
        PISTOLS.clear();

        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.P92));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.P1911));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.R1895));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.R45));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.P18C));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.WIN94));
        PISTOLS.add(new ItemStack(PMCRegistry.PMCItems.SCORPION));
        PISTOLS.add(new ItemStack(PMCItems.DEAGLE));
    }

    private void addShotguns() {
        SHOTGUNS.clear();

        SHOTGUNS.add(new ItemStack(PMCRegistry.PMCItems.SAWED_OFF));
        SHOTGUNS.add(new ItemStack(PMCRegistry.PMCItems.S1897));
        SHOTGUNS.add(new ItemStack(PMCRegistry.PMCItems.S686));
        SHOTGUNS.add(new ItemStack(PMCRegistry.PMCItems.S12K));
    }

    private void addSMGs() {
        SMGS.clear();

        SMGS.add(new ItemStack(PMCRegistry.PMCItems.MICROUZI));
        SMGS.add(new ItemStack(PMCRegistry.PMCItems.UMP45));
        SMGS.add(new ItemStack(PMCRegistry.PMCItems.VECTOR));
        SMGS.add(new ItemStack(PMCRegistry.PMCItems.TOMMY_GUN));
        SMGS.add(new ItemStack(PMCRegistry.PMCItems.BIZON));
        SMGS.add(new ItemStack(PMCItems.MP5K));
    }

    private void addARs(boolean airdrop) {
        ARS.clear();

        ARS.add(new ItemStack(PMCRegistry.PMCItems.M16A4));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.M416));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.SCAR_L));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.G36C));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.QBZ));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.AKM));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.BERYL_M762));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.MK47_MUTANT));
        ARS.add(new ItemStack(PMCRegistry.PMCItems.DP28));

        if (airdrop) {
            ARS.add(new ItemStack(PMCRegistry.PMCItems.AUG));
            ARS.add(new ItemStack(PMCRegistry.PMCItems.GROZA));
            ARS.add(new ItemStack(PMCRegistry.PMCItems.M249));
        }
    }

    private void addDMRs(boolean airdrop) {
        DMRS.clear();

        DMRS.add(new ItemStack(PMCRegistry.PMCItems.VSS));
        DMRS.add(new ItemStack(PMCRegistry.PMCItems.MINI14));
        DMRS.add(new ItemStack(PMCRegistry.PMCItems.QBU));
        DMRS.add(new ItemStack(PMCRegistry.PMCItems.SKS));
        DMRS.add(new ItemStack(PMCRegistry.PMCItems.SLR));

        if (airdrop) {
            DMRS.add(new ItemStack(PMCRegistry.PMCItems.MK14));
        }
    }

    private void addSRs(boolean airdrop) {
        SRS.clear();

        SRS.add(new ItemStack(PMCRegistry.PMCItems.KAR98K));
        SRS.add(new ItemStack(PMCRegistry.PMCItems.M24));

        if (airdrop) {
            SRS.add(new ItemStack(PMCRegistry.PMCItems.AWM));
        }
    }

    private void addBarrelAttachments() {
        int chance = rand.nextInt(26);

        BARREL_ATT.clear();

        BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_PISTOL));
        BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_SMG));
        BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_SMG));

        if (chance >= 10) {
            BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_AR));
            BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_AR));

            if (chance >= 20) {
                BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_SNIPER));
                BARREL_ATT.add(new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_SNIPER));
            }
        }
    }

    private void addGrips() {
        GRIP_ATT.clear();

        GRIP_ATT.add(new ItemStack(PMCRegistry.PMCItems.GRIP_ANGLED));
        GRIP_ATT.add(new ItemStack(PMCRegistry.PMCItems.GRIP_VERTICAL));
    }

    private void addScopes(boolean airdrop) {
        int chance = rand.nextInt(26);
        SCOPE_ATT.clear();

        SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.RED_DOT));
        SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.HOLOGRAPHIC));

        if (chance >= 5) {
            SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.SCOPE2X));

            if (chance >= 10) {
                SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.SCOPE4X));

                if (chance >= 18) {
                    SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.SCOPE8X));

                    if (chance >= 25 && airdrop) {
                        SCOPE_ATT.add(new ItemStack(PMCRegistry.PMCItems.SCOPE15X));
                    }
                }
            }
        }
    }

    private void addMagazines() {
        MAG_ATT.clear();

        int chance = rand.nextInt(25);

        MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL));
        MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL));
        MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG));

        if (chance >= 10) {
            MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL));
            MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SMG));
            MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_AR));
            MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER));

            if (chance >= 20) {
                MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG));
                MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_AR));
                MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR));
                MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER));

                if (chance >= 25) {
                    MAG_ATT.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER));
                }
            }
        }
    }

    private void addStockAtachments() {
        STOCK_ATT.clear();
        int chance = rand.nextInt(11);

        STOCK_ATT.add(new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN));
        if (chance >= 5) {
            STOCK_ATT.add(new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER));
            STOCK_ATT.add(new ItemStack(PMCRegistry.PMCItems.CHEEKPAD));
        }
    }

    private void addAttachments(boolean airdrop) {
        ATTACHMENTS.clear();

        addBarrelAttachments();
        addScopes(airdrop);
        addGrips();
        addMagazines();
        addStockAtachments();

        ATTACHMENTS.addAll(BARREL_ATT);
        ATTACHMENTS.addAll(GRIP_ATT);
        ATTACHMENTS.addAll(SCOPE_ATT);
        ATTACHMENTS.addAll(MAG_ATT);
        ATTACHMENTS.addAll(STOCK_ATT);
    }
}
