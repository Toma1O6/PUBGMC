package com.toma.pubgmc.common.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TileEntityAirdrop extends TileEntity implements IInventory, ITickable
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private String customName;
	private int i = 0;
	private int slot;
	private final Random rand = new Random();
	
	private static final List<ItemStack> ATTACHMENTS = new ArrayList<ItemStack>();
	private static final List<ItemStack> HEALS = new ArrayList<ItemStack>();
	
	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container.airdrop";
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
		return Pubgmc.MOD_ID + "airdrop";
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
	
	private void addAtachments()
	{
		ATTACHMENTS.clear();
		int chance = rand.nextInt(15);
		
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_AR));
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SNIPER));
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.SCOPE8X));
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.SILENCER_AR));
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.SILENCER_SNIPER));
		ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.SCOPE4X));
		
		if(chance >= 10)
		{
			ATTACHMENTS.add(new ItemStack(PMCRegistry.Items.SCOPE15X));
		}
	}
	
	private void addHeals()
	{
		HEALS.clear();
		
		HEALS.add(new ItemStack(PMCRegistry.Items.ADRENALINESYRINGE));
		HEALS.add(new ItemStack(PMCRegistry.Items.FIRSTAIDKIT));
		HEALS.add(new ItemStack(PMCRegistry.Items.MEDKIT));
	}
	
	private int nextSlot()
	{
		slot++;
		return slot;
	}
	
	/** Used for airdrop loot gen **/
	public void generateLoot()
	{
		clear();
		slot = -1;
		
		// Create armor loot which is same in every drop; 1 medical loot, 2 gun and med loot 
		if(ConfigPMC.worldSettings.airdropLootGen == 1)
		{
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.ARMOR3HELMET));
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.ARMOR3BODY));
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.BACKPACK3));
			
			int chance = rand.nextInt(10);
			if(chance < 3)
			{
				setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.MEDKIT));
			}
			
			if(chance >= 3 && chance < 7)
			{
				setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.FIRSTAIDKIT));
			}
			
			if(chance >= 7)
			{
				setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.ADRENALINESYRINGE));
			}
			
			generateGhillie();
		}
		
		if(ConfigPMC.worldSettings.airdropLootGen == 2)
		{
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.ARMOR3HELMET));
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.ARMOR3BODY));
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.BACKPACK3));
			addAtachments();
			addHeals();
			
			int chance = rand.nextInt(5);
			switch(chance)
			{
				case 0:
				{
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AUG));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_556, 30));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_556, 30));
					break;
				}
				
				case 1:
				{
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.M249));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_556, 50));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_556, 50));
					break;
				}
				
				case 2:
				{
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.GROZA));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_762, 30));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_762, 30));
					break;
				}
				
				case 3:
				{
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.MK14));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_762, 30));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_762, 30));
					break;
				}
				
				case 4:
				{
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AWM));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_300M, 10));
					setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.AMMO_300M, 10));
					break;
				}
			}
			
			if(Math.random() * 100 <= 65)
			{
				setInventorySlotContents(nextSlot(), ATTACHMENTS.get(rand.nextInt(ATTACHMENTS.size())));
			}
			if(Math.random() * 100 <= 35)
			{
				setInventorySlotContents(nextSlot(), HEALS.get(rand.nextInt(HEALS.size())));
			}
			
			generateGhillie();
		}
	}
	
	private void generateGhillie()
	{
		if(Math.random() * 100 <= 25)
		{
			setInventorySlotContents(nextSlot(), new ItemStack(PMCRegistry.Items.GHILLIE_SUIT));
		}
	}

	@Override
	public void update()
	{
		World world = getWorld();
		BlockPos pos = getPos();
		if(world.isRemote)
		{
			i++;
			
			if(i >= 2)
			{
				i = 0;
				world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 0.1, 0.06, 0.1, 0);
				world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.4, pos.getY() + 1.1, pos.getZ() + 0.6, 0.09, 0.04, 0.11, 0);
				world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 0.6, pos.getY() + 1.1, pos.getZ() + 0.4, 0.11, 0.05, 0.09, 0);
			}
		}
		
		if(!world.isRemote)
		{
			if(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), ConfigPMC.worldSettings.aidropRange, false) instanceof EntityPlayer)
			{
				
			}
		
			else
			{
				world.setBlockToAir(pos);
			}
		}
	}
}
