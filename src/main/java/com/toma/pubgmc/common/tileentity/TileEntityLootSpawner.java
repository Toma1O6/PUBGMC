package com.toma.pubgmc.common.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.TileEntitySync;
import com.toma.pubgmc.util.handlers.ConfigHandler;

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

public class TileEntityLootSpawner extends TileEntitySync implements IInventory
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private String customName;
	private final Random rand = new Random();
	private int slot;
	
	//Loot related - guns
	private static final List<ItemStack> PISTOLS = new ArrayList<ItemStack>();
	private static final List<ItemStack> SHOTGUNS = new ArrayList<ItemStack>();
	private static final List<ItemStack> SMGS = new ArrayList<ItemStack>();
	private static final List<ItemStack> ARS = new ArrayList<ItemStack>();
	private static final List<ItemStack> DMRS = new ArrayList<ItemStack>();
	private static final List<ItemStack> SRS = new ArrayList<ItemStack>();
	
	//healing
	private static final List<ItemStack> COMMON_HEAL = new ArrayList<ItemStack>();
	private static final List<ItemStack> RARE_HEAL = new ArrayList<ItemStack>();
	
	//Wearable
	private static final List<ItemStack> WEARABLE = new ArrayList<ItemStack>();
	private static final List<ItemStack> BACKPACKS = new ArrayList<ItemStack>();
	
	//Ammo & grenades
	private static final List<ItemStack> AMMO = new ArrayList<ItemStack>();
	private static final List<ItemStack> THROWABLES = new ArrayList<ItemStack>();
	
	//Attachments
	private static final List<ItemStack> ATTACHMENTS = new ArrayList<ItemStack>();
	private static final List<ItemStack> BARREL_ATT = new ArrayList<ItemStack>();
	private static final List<ItemStack> GRIP_ATT = new ArrayList<ItemStack>();
	private static final List<ItemStack> SCOPE_ATT = new ArrayList<ItemStack>();
	private static final List<ItemStack> MAG_ATT = new ArrayList<ItemStack>();
	private static final List<ItemStack> STOCK_ATT = new ArrayList<ItemStack>();
	
	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container.lootspawner";
	}
	
	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() 
	{
		return this.inventory.size();
	}
	
	@Override
	public boolean isEmpty() 
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty()) return false;
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && index + 1 == 1 && !flag)
		{
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);

		}
	}
	
	//To keep all items when state changes
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
	}
	
	public String getGuiID() 
	{
		return Pubgmc.MOD_ID + ":loot_spawner";
	}
	
	@Override
	public int getField(int id) 
	{
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
		
	}
	
	@Override
	public int getFieldCount()
	{
		return 9;
	}
	
	@Override
	public void clear() 
	{
		this.inventory.clear();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}
	
	/**
	 * Generate loot
	 * 
	 * @param airdroploot - enable airdrop weapons in the loot + awm ammo will spawn
	 * @param addAmmo - Decides if ammo will be generated with guns
	 * @param lootType - 0 = all, 1 = pistol, 2 = shotguns, 3 = smgs, 4 = ar + lmg, 5 = dmr, 6 = sr, 7 = dmr + sr
	 */
	public void generateLoot(boolean airdroploot, boolean addAmmo, int lootType)
	{
		slot = -1;
		this.inventory.clear();
		
		addGrenadeLoot();
		addBackpackLoot(rand.nextInt(26));
		addCommonHealing();
		addWearableLoot(rand.nextInt(26));
		addAmmoLoot(false, airdroploot);
		
		addRareHealing(rand.nextInt(12));
		
		//Rare meds
		if(Math.random() * 100 <= 10)
		{
			setInventorySlotContents(getEmptySlot(), RARE_HEAL.get(rand.nextInt(RARE_HEAL.size())));
		}
		
		if(Math.random() * 100 <= 45)
		{	
			//Actual gun gen
			if(ConfigHandler.enableGunLoot)
			{
				//Flare gun 1% spawn
				if(Math.random() * 100 <= 0.5)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.FLARE_GUN));
					if(addAmmo)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_FLARE, 1));
					}
				}
				
				//Sniper rifles 5% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 5 && (lootType == 0 || lootType == 6 || lootType == 7))
				{
					addSRs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), SRS.get(rand.nextInt(SRS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//DMRs 10% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 10 && (lootType == 0 || lootType == 5 || lootType == 7))
				{
					addDMRs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), DMRS.get(rand.nextInt(DMRS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//Assault rifles 20% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 20 && (lootType == 0 || lootType == 4))
				{
					addARs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), ARS.get(rand.nextInt(ARS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//SMGs 20% spawn 
				else if(Math.random() * 100 <= 20 && (lootType == 0 || lootType == 3))
				{
					addSMGs();
					
					setInventorySlotContents(getEmptySlot(), SMGS.get(rand.nextInt(SMGS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//Shotguns 35% spawn
				else if(Math.random() * 100 <= 35 && (lootType == 0 || lootType == 2))
				{
					addShotguns();
					
					setInventorySlotContents(getEmptySlot(), SHOTGUNS.get(rand.nextInt(SHOTGUNS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//If none of the above is successful then pistol will be generated
				else if(lootType == 0 || lootType == 1)
				{
					addPistols();
					
					setInventorySlotContents(getEmptySlot(), PISTOLS.get(rand.nextInt(PISTOLS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
			}
		}
		
		if(Math.random() * 100 <= 15)
		{
			setInventorySlotContents(getEmptySlot(), THROWABLES.get(rand.nextInt(THROWABLES.size())));
		}
		else if(Math.random() * 100 <= 20)
		{
			setInventorySlotContents(getEmptySlot(), WEARABLE.get(rand.nextInt(WEARABLE.size())));
		}
		else if(Math.random() * 100 <= 15)
		{
			setInventorySlotContents(getEmptySlot(), BACKPACKS.get(rand.nextInt(BACKPACKS.size())));
		}
		else if(Math.random() * 100 <= 25)
		{
			setInventorySlotContents(getEmptySlot(), COMMON_HEAL.get(rand.nextInt(COMMON_HEAL.size())));
		}
		else if(Math.random() * 100 <= 35)
		{
			setInventorySlotContents(getEmptySlot(), AMMO.get(rand.nextInt(AMMO.size())));
		}
		
		if(Math.random() * 100 <= 20)
		{
			addAttachments(airdroploot);
			setInventorySlotContents(getEmptySlot(), ATTACHMENTS.get(rand.nextInt(ATTACHMENTS.size())));
		}
	}
	
	private int getEmptySlot()
	{
		slot++;
		return slot;
	}
	
	/** Generate random ammo based on the weapon **/
	private void generateAmmoForCurrentWeapon(ItemStack stack)
	{
		GunBase gun = (GunBase)stack.getItem();
		if(gun.getGunType() != GunType.PISTOL)
		{
			if(gun.getAmmoType() == AmmoType.AMMO9MM)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 30));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 30));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 30));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO45ACP)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 30));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 30));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 30));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO12G)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_SHOTGUN, 5));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_SHOTGUN, 5));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_SHOTGUN, 5));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO556)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_556, 30));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_556, 30));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_556, 30));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO762)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 30));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 30));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 30));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO300M)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_300M, 10));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_300M, 10));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_300M, 10));
					}
				}
			}
		}
		else
		{
			if(gun.getAmmoType() == AmmoType.AMMO9MM)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 15));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 15));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_9MM, 15));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO45ACP)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 15));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 15));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_45ACP, 15));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO762)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 15));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 15));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCItems.AMMO_762, 15));
					}
				}
			}
		}
	}
	
	/**
	 * Generate random grenade
	 */
	private void addGrenadeLoot()
	{
		THROWABLES.clear();
		
		THROWABLES.add(new ItemStack(PMCItems.GRENADE));
		THROWABLES.add(new ItemStack(PMCItems.MOLOTOV));
		THROWABLES.add(new ItemStack(PMCItems.SMOKE));
	}
	
	/**
	 * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
	 */
	private void addWearableLoot(int chance)
	{
		WEARABLE.clear();
		WEARABLE.add(new ItemStack(PMCItems.ARMOR1BODY));
		WEARABLE.add(new ItemStack(PMCItems.ARMOR1HELMET));
		
		if(chance >= 10)
		{
			WEARABLE.add(new ItemStack(PMCItems.ARMOR2BODY));
			WEARABLE.add(new ItemStack(PMCItems.ARMOR2HELMET));
			WEARABLE.add(new ItemStack(PMCItems.NV_GOGGLES));
			
			if(chance >= 25)
			{
				WEARABLE.add(new ItemStack(PMCItems.ARMOR3BODY));
				WEARABLE.add(new ItemStack(PMCItems.ARMOR3HELMET));
			}
		}
	}
	
	/**
	 * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
	 */
	private void addBackpackLoot(int chance)
	{
		BACKPACKS.clear();
		
		BACKPACKS.add(new ItemStack(PMCItems.BACKPACK1));
		
		if(chance >= 10)
		{
			BACKPACKS.add(new ItemStack(PMCItems.BACKPACK2));
			
			if(chance >= 25)
			{
				BACKPACKS.add(new ItemStack(PMCItems.BACKPACK3));
			}
		}
	}
	
	/**
	 * Generate random ammo 
	 * @param randomCount - generate random count of the ammo
	 */
	private void addAmmoLoot(boolean randomCount, boolean airdrop)
	{
		AMMO.clear();
		
		if(randomCount)
		{
			int count = rand.nextInt(30);
		}
		else
		{
			AMMO.add(new ItemStack(PMCItems.AMMO_9MM, 30));
			AMMO.add(new ItemStack(PMCItems.AMMO_45ACP, 30));
			AMMO.add(new ItemStack(PMCItems.AMMO_SHOTGUN, 10));
			AMMO.add(new ItemStack(PMCItems.AMMO_556, 30));
			AMMO.add(new ItemStack(PMCItems.AMMO_762, 30));
			AMMO.add(new ItemStack(PMCItems.AMMO_FLARE, 1));
			
			if(airdrop)
			{
				AMMO.add(new ItemStack(PMCItems.AMMO_300M, 5));
			}
		}
	}
	
	/**
	 * First aid loot gen
	 * @param chance - +10 for medkits and syringes
	 */
	private void addRareHealing(int chance)
	{
		RARE_HEAL.clear();
		
		RARE_HEAL.add(new ItemStack(PMCItems.FIRSTAIDKIT));
		
		if(chance >= 10)
		{
			RARE_HEAL.add(new ItemStack(PMCItems.MEDKIT));
			RARE_HEAL.add(new ItemStack(PMCItems.ADRENALINESYRINGE));
		}
	}
	
	private void addCommonHealing()
	{
		COMMON_HEAL.clear();
		
		COMMON_HEAL.add(new ItemStack(PMCItems.BANDAGE, 5));
		COMMON_HEAL.add(new ItemStack(PMCItems.ENERGYDRINK));
		COMMON_HEAL.add(new ItemStack(PMCItems.PAINKILLERS));
	}
	
	private void addPistols()
	{
		PISTOLS.clear();
		
		PISTOLS.add(new ItemStack(PMCItems.P92));
		PISTOLS.add(new ItemStack(PMCItems.P1911));
		PISTOLS.add(new ItemStack(PMCItems.R1895));
		PISTOLS.add(new ItemStack(PMCItems.R45));
		PISTOLS.add(new ItemStack(PMCItems.P18C));
		PISTOLS.add(new ItemStack(PMCItems.WIN94));
	}
	
	private void addShotguns()
	{
		SHOTGUNS.clear();
		
		SHOTGUNS.add(new ItemStack(PMCItems.SAWED_OFF));
		SHOTGUNS.add(new ItemStack(PMCItems.S1897));
		SHOTGUNS.add(new ItemStack(PMCItems.S686));
		SHOTGUNS.add(new ItemStack(PMCItems.S12K));
	}
	
	private void addSMGs()
	{
		SMGS.clear();
		
		SMGS.add(new ItemStack(PMCItems.MICROUZI));
		SMGS.add(new ItemStack(PMCItems.UMP9));
		SMGS.add(new ItemStack(PMCItems.VECTOR));
		SMGS.add(new ItemStack(PMCItems.TOMMY_GUN));
	}
	
	private void addARs(boolean airdrop)
	{
		ARS.clear();
		
		ARS.add(new ItemStack(PMCItems.M16A4));
		ARS.add(new ItemStack(PMCItems.M416));
		ARS.add(new ItemStack(PMCItems.SCAR_L));
		ARS.add(new ItemStack(PMCItems.QBZ));
		ARS.add(new ItemStack(PMCItems.AKM));
		ARS.add(new ItemStack(PMCItems.BERYL_M762));
		ARS.add(new ItemStack(PMCItems.MK47_MUTANT));
		ARS.add(new ItemStack(PMCItems.DP28));
		
		if(airdrop)
		{
			ARS.add(new ItemStack(PMCItems.AUG));
			ARS.add(new ItemStack(PMCItems.GROZA));
			ARS.add(new ItemStack(PMCItems.M249));
		}
	}
	
	private void addDMRs(boolean airdrop)
	{
		DMRS.clear();
		
		DMRS.add(new ItemStack(PMCItems.VSS));
		DMRS.add(new ItemStack(PMCItems.MINI14));
		DMRS.add(new ItemStack(PMCItems.QBU));
		DMRS.add(new ItemStack(PMCItems.SKS));
		DMRS.add(new ItemStack(PMCItems.SLR));
		
		if(airdrop)
		{
			DMRS.add(new ItemStack(PMCItems.MK14));
		}
	}
	
	private void addSRs(boolean airdrop)
	{
		SRS.clear();
		
		SRS.add(new ItemStack(PMCItems.KAR98K));
		SRS.add(new ItemStack(PMCItems.M24));
		
		if(airdrop)
		{
			SRS.add(new ItemStack(PMCItems.AWM));
		}
	}
	
	private void addBarrelAttachments()
	{
		int chance = rand.nextInt(26);
		
		BARREL_ATT.clear();
		
		BARREL_ATT.add(new ItemStack(PMCItems.SILENCER_PISTOL));
		BARREL_ATT.add(new ItemStack(PMCItems.SILENCER_SMG));
		BARREL_ATT.add(new ItemStack(PMCItems.COMPENSATOR_SMG));
		
		if(chance >= 10)
		{
			BARREL_ATT.add(new ItemStack(PMCItems.SILENCER_AR));
			BARREL_ATT.add(new ItemStack(PMCItems.COMPENSATOR_AR));
			
			if(chance >= 20)
			{
				BARREL_ATT.add(new ItemStack(PMCItems.SILENCER_SNIPER));
				BARREL_ATT.add(new ItemStack(PMCItems.COMPENSATOR_SNIPER));
			}
		}
	}
	
	private void addGrips()
	{
		GRIP_ATT.clear();
		
		GRIP_ATT.add(new ItemStack(PMCItems.GRIP_ANGLED));
		GRIP_ATT.add(new ItemStack(PMCItems.GRIP_VERTICAL));
	}
	
	private void addScopes(boolean airdrop)
	{
		int chance = rand.nextInt(26);
		SCOPE_ATT.clear();
		
		SCOPE_ATT.add(new ItemStack(PMCItems.RED_DOT));
		SCOPE_ATT.add(new ItemStack(PMCItems.HOLOGRAPHIC));
		
		if(chance >= 5)
		{
			SCOPE_ATT.add(new ItemStack(PMCItems.SCOPE2X));
			
			if(chance >= 10)
			{
				SCOPE_ATT.add(new ItemStack(PMCItems.SCOPE4X));
				
				if(chance >= 18)
				{
					SCOPE_ATT.add(new ItemStack(PMCItems.SCOPE8X));
					
					if(chance >= 25 && airdrop)
					{
						SCOPE_ATT.add(new ItemStack(PMCItems.SCOPE15X));
					}
				}
			}
		}
	}
	
	private void addMagazines()
	{
		MAG_ATT.clear();
		
		int chance = rand.nextInt(25);
		
		MAG_ATT.add(new ItemStack(PMCItems.QUICKDRAW_MAG_PISTOL));
		MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_MAG_PISTOL));
		MAG_ATT.add(new ItemStack(PMCItems.QUICKDRAW_MAG_SMG));
		
		if(chance >= 10)
		{
			MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL));
			MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_MAG_SMG));
			MAG_ATT.add(new ItemStack(PMCItems.QUICKDRAW_MAG_AR));
			MAG_ATT.add(new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER));
			
			if(chance >= 20)
			{
				MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG));
				MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_MAG_AR));
				MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_AR));
				MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_MAG_SNIPER));
				
				if(chance >= 25)
				{
					MAG_ATT.add(new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER));
				}
			}
		}
	}
	
	private void addStockAtachments()
	{
		STOCK_ATT.clear();
		int chance = rand.nextInt(11);
		
		STOCK_ATT.add(new ItemStack(PMCItems.BULLET_LOOPS_SHOTGUN));
		if(chance >= 5)
		{
			STOCK_ATT.add(new ItemStack(PMCItems.BULLET_LOOPS_SNIPER));
			STOCK_ATT.add(new ItemStack(PMCItems.CHEEKPAD));
		}
	}
	
	private void addAttachments(boolean airdrop)
	{
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
