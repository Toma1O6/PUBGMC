package com.toma.pubgmc.common.tileentity;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.IGameTileEntity;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.items.armor.ItemGhillie;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.config.common.CFGEnumAirdropLoot;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class TileEntityBigAirdrop extends TileEntity implements IInventoryTileEntity, ITickable, IAirdropTileEntity, IGameTileEntity {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(18, ItemStack.EMPTY);
    private int slot;
    private short timer;

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Airdrop");
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return this.getDisplayName().getFormattedText();
    }

    @Override
    public void generateLoot() {
        clear();
        if(ConfigPMC.common.world.airdropLoot == CFGEnumAirdropLoot.NOTHING) {
            return;
        }
        slot = -1;
        //setup
        generateWeaponLoot();
        generateAtachments();
        generateHeals();

        //2 sets of armor
        createArmorLoot();
        if(ConfigPMC.common.world.airdropLoot == CFGEnumAirdropLoot.ALL) {
            List<ItemStack> weapons = this.generateWeaponLoot();
            List<ItemStack> attachments = this.generateAtachments();

            setInventorySlotContents(nextID(), weapons.get(Pubgmc.rng().nextInt(weapons.size())));
            addAmmoToCurrentWeapon();
            setInventorySlotContents(nextID(), weapons.get(Pubgmc.rng().nextInt(weapons.size())));
            addAmmoToCurrentWeapon();
            setInventorySlotContents(nextID(), attachments.get(Pubgmc.rng().nextInt(attachments.size())));
            setInventorySlotContents(nextID(), attachments.get(Pubgmc.rng().nextInt(attachments.size())));
        }
        List<ItemStack> heals = this.generateHeals();
        setInventorySlotContents(nextID(), heals.get(Pubgmc.rng().nextInt(heals.size())));
        setInventorySlotContents(nextID(), heals.get(Pubgmc.rng().nextInt(heals.size())));

        generateGhillie();
    }

    private void addAmmoToCurrentWeapon() {
        switch (((GunBase) getStackInSlot(slot).getItem()).getAmmoType()) {
            case AMMO556: {
                for (int i = 0; i < 1 + Pubgmc.rng().nextInt(3); i++) {
                    setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.AMMO_556, 30));
                }

                break;
            }

            case AMMO762: {
                for (int i = 0; i < 1 + Pubgmc.rng().nextInt(3); i++) {
                    setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.AMMO_762, 30));
                }

                break;
            }

            case AMMO300M: {
                setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.AMMO_300M, 10));
                setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.AMMO_300M, 10));
                break;
            }

            default:
                break;
        }
    }

    private List<ItemStack> generateWeaponLoot() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemStack(PMCRegistry.PMCItems.AUG));
        list.add(new ItemStack(PMCRegistry.PMCItems.M249));
        list.add(new ItemStack(PMCRegistry.PMCItems.GROZA));
        list.add(new ItemStack(PMCRegistry.PMCItems.MK14));
        list.add(new ItemStack(PMCRegistry.PMCItems.AWM));
        return list;
    }

    private List<ItemStack> generateAtachments() {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR));
        list.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER));
        list.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_AR));
        list.add(new ItemStack(PMCRegistry.PMCItems.SILENCER_SNIPER));
        list.add(new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_SNIPER));
        list.add(new ItemStack(PMCRegistry.PMCItems.SCOPE4X));
        list.add(new ItemStack(PMCRegistry.PMCItems.SCOPE8X));
        list.add(new ItemStack(PMCRegistry.PMCItems.SCOPE15X));
        return list;
    }

    private List<ItemStack> generateHeals() {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(PMCRegistry.PMCItems.PAINKILLERS));
        list.add(new ItemStack(PMCRegistry.PMCItems.FIRSTAIDKIT));
        list.add(new ItemStack(PMCRegistry.PMCItems.MEDKIT));
        list.add(new ItemStack(PMCRegistry.PMCItems.ADRENALINESYRINGE));
        return list;
    }

    private void createArmorLoot() {
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.ARMOR3HELMET));
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.ARMOR3HELMET));
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.ARMOR3BODY));
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.ARMOR3BODY));
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.BACKPACK3));
        setInventorySlotContents(nextID(), new ItemStack(PMCRegistry.PMCItems.BACKPACK3));
    }

    private void generateGhillie() {
        if (Math.random() * 100 <= 25) {
            NBTTagCompound nbt = new NBTTagCompound();
            Integer[] ints = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null).getGhillieSuitsColorVariants().toArray(new Integer[0]);
            nbt.setInteger("ghillieColor", ints.length == 0 ? ItemGhillie.DEFAULT_COLOR : ints[Pubgmc.rng().nextInt(ints.length)]);
            ItemStack stack = new ItemStack(PMCRegistry.PMCItems.GHILLIE_SUIT);
            stack.setTagCompound(nbt);
            setInventorySlotContents(nextID(), stack);
        }
    }

    private int nextID() {
        if (slot != 17) {
            slot++;
        }
        return slot;
    }

    @Override
    public void update() {
        if (timer++ >= 3 && world.isRemote && !inventory.isEmpty()) {
            timer = 0;
            world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.5, pos.getY() + 1.3, pos.getZ() + 0.5, 0.1, 0.06, 0.1, 0);
            world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.4, pos.getY() + 1.3, pos.getZ() + 0.6, 0.09, 0.04, 0.11, 0);
            world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.6, pos.getY() + 1.3, pos.getZ() + 0.4, 0.11, 0.05, 0.09, 0);
        }
    }

    private String hash;

    @Override
    public void setGameHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String getGameHash() {
        return hash;
    }

    @Override
    public void onLoaded() {
        world.scheduleBlockUpdate(pos, blockType, 3, 0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("hash", hash);
        ItemStackHelper.saveAllItems(compound, inventory);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        hash = compound.getString("hash");
        ItemStackHelper.loadAllItems(compound, inventory);
    }
}
