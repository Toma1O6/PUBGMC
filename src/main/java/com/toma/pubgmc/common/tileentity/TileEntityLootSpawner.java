package com.toma.pubgmc.common.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.PMCRegistry;
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

public class TileEntityLootSpawner extends TileEntitySync implements IInventory
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private String customName;
	private final Random rand = new Random();
	private int slot;
	private boolean randomAmmo = false;
	private List<GunType> weapons = new ArrayList<GunType>();
	private String gameID = "EMPTY";
	
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
		gameID = compound.hasKey("gameID") ? compound.getString("gameID") : "";
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		compound.setString("gameID", gameID);
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
	
	public void setGameID(String gameID)
	{
		this.gameID = gameID;
	}
	
	public String getGameID()
	{
		return gameID;
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
	public void generateLoot(boolean airdroploot, boolean addAmmo, boolean randomAmmo, double chanceMultiplier, List<GunType> weaponList)
	{
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
		if(Math.random() * 100 <= 10 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), RARE_HEAL.get(rand.nextInt(RARE_HEAL.size())));
		}
		
		if(Math.random() * 100 <= 45 * chanceMultiplier)
		{	
			//Actual gun gen
			if(ConfigPMC.worldSettings.enableGunLoot)
			{
				//Flare gun 0.5% spawn
				if(Math.random() * 100 <= 0.5 * chanceMultiplier)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.FLARE_GUN));
					if(addAmmo)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_FLARE, 1));
					}
				}
				
				//Sniper rifles 2% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 2 * chanceMultiplier && weapons.contains(GunType.SR))
				{
					addSRs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), SRS.get(rand.nextInt(SRS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//DMRs 3% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 3 * chanceMultiplier && weapons.contains(GunType.DMR))
				{
					addDMRs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), DMRS.get(rand.nextInt(DMRS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//Assault rifles 15% spawn, airdrop wep disabled
				else if(Math.random() * 100 <= 15 * chanceMultiplier && weapons.contains(GunType.AR))
				{
					addARs(airdroploot);
					
					setInventorySlotContents(getEmptySlot(), ARS.get(rand.nextInt(ARS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//SMGs 20% spawn 
				else if(Math.random() * 100 <= 20 * chanceMultiplier && weapons.contains(GunType.SMG))
				{
					addSMGs();
					
					setInventorySlotContents(getEmptySlot(), SMGS.get(rand.nextInt(SMGS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//Shotguns 35% spawn
				else if(Math.random() * 100 <= 35 * chanceMultiplier && weapons.contains(GunType.SHOTGUN))
				{
					addShotguns();
					
					setInventorySlotContents(getEmptySlot(), SHOTGUNS.get(rand.nextInt(SHOTGUNS.size())));
					if(addAmmo)
					{
						generateAmmoForCurrentWeapon(getStackInSlot(slot));
					}
				}
				
				//If none of the above is successful then pistol will be generated
				else if(weapons.contains(GunType.PISTOL))
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
		
		if(Math.random() * 100 <= 15 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), THROWABLES.get(rand.nextInt(THROWABLES.size())));
		}
		else if(Math.random() * 100 <= 20 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), WEARABLE.get(rand.nextInt(WEARABLE.size())));
		}
		else if(Math.random() * 100 <= 15 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), BACKPACKS.get(rand.nextInt(BACKPACKS.size())));
		}
		else if(Math.random() * 100 <= 25 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), COMMON_HEAL.get(rand.nextInt(COMMON_HEAL.size())));
		}
		else if(Math.random() * 100 <= 35 * chanceMultiplier)
		{
			setInventorySlotContents(getEmptySlot(), AMMO.get(rand.nextInt(AMMO.size())));
		}
		
		if(Math.random() * 100 <= 20 * chanceMultiplier)
		{
			addAttachments(airdroploot);
			setInventorySlotContents(getEmptySlot(), ATTACHMENTS.get(rand.nextInt(ATTACHMENTS.size())));
		}
		
		if(Math.random() <= 0.05)
		{
			setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.FUELCAN));
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
		int pistolAmmoCount = 15;
		int defAR = 30;
		int shotguns = 5;
		int awm = 10;
		
		if(randomAmmo)
		{
			pistolAmmoCount = rand.nextInt(16);
			defAR = rand.nextInt(31);
			shotguns = rand.nextInt(6);
			awm = rand.nextInt(11);
		}
		
		if(gun == PMCRegistry.Items.KAR98K || gun == PMCRegistry.Items.M24)
		{
			setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
			
			if(Math.random() * 100 <= 75)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
				
				if(Math.random() * 100 <= 25)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
				}
			}
			
			return;
		}
		
		if(gun.getGunType() != GunType.PISTOL)
		{
			if(gun.getAmmoType() == AmmoType.AMMO9MM)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, defAR));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, defAR));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, defAR));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO45ACP)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, defAR));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, defAR));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, defAR));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO12G)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_SHOTGUN, shotguns));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_SHOTGUN, shotguns));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_SHOTGUN, shotguns));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO556)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_556, defAR));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_556, defAR));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_556, defAR));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO762)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, defAR));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, defAR));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, defAR));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO300M)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_300M, awm));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_300M, awm));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_300M, awm));
					}
				}
			}
		}
		else
		{
			if(gun.getAmmoType() == AmmoType.AMMO9MM)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, pistolAmmoCount));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, pistolAmmoCount));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_9MM, pistolAmmoCount));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO45ACP)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, pistolAmmoCount));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, pistolAmmoCount));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_45ACP, pistolAmmoCount));
					}
				}
			}
			
			if(gun.getAmmoType() == AmmoType.AMMO762)
			{
				setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
				if(Math.random() * 100 <= 75)
				{
					setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
					
					if(Math.random() * 100 <= 25)
					{
						setInventorySlotContents(getEmptySlot(), new ItemStack(PMCRegistry.Items.AMMO_762, pistolAmmoCount));
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
		
		THROWABLES.add(new ItemStack(PMCRegistry.Items.GRENADE));
		THROWABLES.add(new ItemStack(PMCRegistry.Items.MOLOTOV));
		THROWABLES.add(new ItemStack(PMCRegistry.Items.SMOKE));
	}
	
	/**
	 * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
	 */
	private void addWearableLoot(int chance)
	{
		WEARABLE.clear();
		WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR1BODY));
		WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR1HELMET));
		
		if(chance >= 10)
		{
			WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR2BODY));
			WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR2HELMET));
			WEARABLE.add(new ItemStack(PMCRegistry.Items.NV_GOGGLES));
			
			if(chance >= 25)
			{
				WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR3BODY));
				WEARABLE.add(new ItemStack(PMCRegistry.Items.ARMOR3HELMET));
			}
		}
	}
	
	/**
	 * @param chance - if bigger than 10 >= + rare loot ; chance >= 25 + legendary loot
	 */
	private void addBackpackLoot(int chance)
	{
		BACKPACKS.clear();
		
		BACKPACKS.add(new ItemStack(PMCRegistry.Items.BACKPACK1));
		
		if(chance >= 10)
		{
			BACKPACKS.add(new ItemStack(PMCRegistry.Items.BACKPACK2));
			
			if(chance >= 25)
			{
				BACKPACKS.add(new ItemStack(PMCRegistry.Items.BACKPACK3));
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
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_9MM, rand.nextInt(30)));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_45ACP, rand.nextInt(30)));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_SHOTGUN, rand.nextInt(10)));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_556, rand.nextInt(30)));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_762, rand.nextInt(30)));
			
			if(Math.random() * 100 <= 3)
			{
				AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_FLARE, 1));
			}
			
			if(airdrop)
			{
				AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_300M, rand.nextInt(6)));
			}
		}
		
		else
		{
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_9MM, 30));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_45ACP, 30));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_SHOTGUN, 10));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_556, 30));
			AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_762, 30));
			
			if(Math.random() * 100 <= 3)
			{
				AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_FLARE, 1));
			}
			
			if(airdrop)
			{
				AMMO.add(new ItemStack(PMCRegistry.Items.AMMO_300M, 5));
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
		
		RARE_HEAL.add(new ItemStack(PMCRegistry.Items.FIRSTAIDKIT));
		
		if(chance >= 10)
		{
			RARE_HEAL.add(new ItemStack(PMCRegistry.Items.MEDKIT));
			RARE_HEAL.add(new ItemStack(PMCRegistry.Items.ADRENALINESYRINGE));
		}
	}
	
	private void addCommonHealing()
	{
		COMMON_HEAL.clear();
		
		COMMON_HEAL.add(new ItemStack(PMCRegistry.Items.BANDAGE, 5));
		COMMON_HEAL.add(new ItemStack(PMCRegistry.Items.ENERGYDRINK));
		COMMON_HEAL.add(new ItemStack(PMCRegistry.Items.PAINKILLERS));
	}
	
	private void addPistols()
	{
		PISTOLS.clear();
		
		PISTOLS.add(new ItemStack(PMCRegistry.Items.P92));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.P1911));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.R1895));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.R45));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.P18C));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.WIN94));
		PISTOLS.add(new ItemStack(PMCRegistry.Items.SCORPION));
	}
	
	private void addShotguns()
	{
		SHOTGUNS.clear();
		
		SHOTGUNS.add(new ItemStack(PMCRegistry.Items.SAWED_OFF));
		SHOTGUNS.add(new ItemStack(PMCRegistry.Items.S1897));
		SHOTGUNS.add(new ItemStack(PMCRegistry.Items.S686));
		SHOTGUNS.add(new ItemStack(PMCRegistry.Items.S12K));
	}
	
	private void addSMGs()
	{
		SMGS.clear();
		
		SMGS.add(new ItemStack(PMCRegistry.Items.MICROUZI));
		SMGS.add(new ItemStack(PMCRegistry.Items.UMP45));
		SMGS.add(new ItemStack(PMCRegistry.Items.VECTOR));
		SMGS.add(new ItemStack(PMCRegistry.Items.TOMMY_GUN));
		SMGS.add(new ItemStack(PMCRegistry.Items.BIZON));
	}
	
	private void addARs(boolean airdrop)
	{
		ARS.clear();
		
		ARS.add(new ItemStack(PMCRegistry.Items.M16A4));
		ARS.add(new ItemStack(PMCRegistry.Items.M416));
		ARS.add(new ItemStack(PMCRegistry.Items.SCAR_L));
		ARS.add(new ItemStack(PMCRegistry.Items.G36C));
		ARS.add(new ItemStack(PMCRegistry.Items.QBZ));
		ARS.add(new ItemStack(PMCRegistry.Items.AKM));
		ARS.add(new ItemStack(PMCRegistry.Items.BERYL_M762));
		ARS.add(new ItemStack(PMCRegistry.Items.MK47_MUTANT));
		ARS.add(new ItemStack(PMCRegistry.Items.DP28));
		
		if(airdrop)
		{
			ARS.add(new ItemStack(PMCRegistry.Items.AUG));
			ARS.add(new ItemStack(PMCRegistry.Items.GROZA));
			ARS.add(new ItemStack(PMCRegistry.Items.M249));
		}
	}
	
	private void addDMRs(boolean airdrop)
	{
		DMRS.clear();
		
		DMRS.add(new ItemStack(PMCRegistry.Items.VSS));
		DMRS.add(new ItemStack(PMCRegistry.Items.MINI14));
		DMRS.add(new ItemStack(PMCRegistry.Items.QBU));
		DMRS.add(new ItemStack(PMCRegistry.Items.SKS));
		DMRS.add(new ItemStack(PMCRegistry.Items.SLR));
		
		if(airdrop)
		{
			DMRS.add(new ItemStack(PMCRegistry.Items.MK14));
		}
	}
	
	private void addSRs(boolean airdrop)
	{
		SRS.clear();
		
		SRS.add(new ItemStack(PMCRegistry.Items.KAR98K));
		SRS.add(new ItemStack(PMCRegistry.Items.M24));
		
		if(airdrop)
		{
			SRS.add(new ItemStack(PMCRegistry.Items.AWM));
		}
	}
	
	private void addBarrelAttachments()
	{
		int chance = rand.nextInt(26);
		
		BARREL_ATT.clear();
		
		BARREL_ATT.add(new ItemStack(PMCRegistry.Items.SILENCER_PISTOL));
		BARREL_ATT.add(new ItemStack(PMCRegistry.Items.SILENCER_SMG));
		BARREL_ATT.add(new ItemStack(PMCRegistry.Items.COMPENSATOR_SMG));
		
		if(chance >= 10)
		{
			BARREL_ATT.add(new ItemStack(PMCRegistry.Items.SILENCER_AR));
			BARREL_ATT.add(new ItemStack(PMCRegistry.Items.COMPENSATOR_AR));
			
			if(chance >= 20)
			{
				BARREL_ATT.add(new ItemStack(PMCRegistry.Items.SILENCER_SNIPER));
				BARREL_ATT.add(new ItemStack(PMCRegistry.Items.COMPENSATOR_SNIPER));
			}
		}
	}
	
	private void addGrips()
	{
		GRIP_ATT.clear();
		
		GRIP_ATT.add(new ItemStack(PMCRegistry.Items.GRIP_ANGLED));
		GRIP_ATT.add(new ItemStack(PMCRegistry.Items.GRIP_VERTICAL));
	}
	
	private void addScopes(boolean airdrop)
	{
		int chance = rand.nextInt(26);
		SCOPE_ATT.clear();
		
		SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.RED_DOT));
		SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.HOLOGRAPHIC));
		
		if(chance >= 5)
		{
			SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.SCOPE2X));
			
			if(chance >= 10)
			{
				SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.SCOPE4X));
				
				if(chance >= 18)
				{
					SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.SCOPE8X));
					
					if(chance >= 25 && airdrop)
					{
						SCOPE_ATT.add(new ItemStack(PMCRegistry.Items.SCOPE15X));
					}
				}
			}
		}
	}
	
	private void addMagazines()
	{
		MAG_ATT.clear();
		
		int chance = rand.nextInt(25);
		
		MAG_ATT.add(new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_PISTOL));
		MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_MAG_PISTOL));
		MAG_ATT.add(new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_SMG));
		
		if(chance >= 10)
		{
			MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_PISTOL));
			MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_MAG_SMG));
			MAG_ATT.add(new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_AR));
			MAG_ATT.add(new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_SNIPER));
			
			if(chance >= 20)
			{
				MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SMG));
				MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_MAG_AR));
				MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_AR));
				MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_MAG_SNIPER));
				
				if(chance >= 25)
				{
					MAG_ATT.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SNIPER));
				}
			}
		}
	}
	
	private void addStockAtachments()
	{
		STOCK_ATT.clear();
		int chance = rand.nextInt(11);
		
		STOCK_ATT.add(new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SHOTGUN));
		if(chance >= 5)
		{
			STOCK_ATT.add(new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SNIPER));
			STOCK_ATT.add(new ItemStack(PMCRegistry.Items.CHEEKPAD));
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
