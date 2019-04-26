package com.toma.pubgmc.common.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileEntityGunWorkbench extends TileEntity implements IInventoryTileEntity
{
	private static final List<Item> GUNS = new ArrayList<Item>();
	private static final List<Item> AMMO = new ArrayList<Item>();
	private static final List<Item> ATTACHMENT = new ArrayList<Item>();
	private static final List<Item> CLOTHING = new ArrayList<Item>();
	private static final List<Item> HEALING = new ArrayList<Item>();
	private static final List<Item> THROWABLES = new ArrayList<Item>();
	private static final List<Item> OTHER = new ArrayList<Item>();
	
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private CraftMode mode;
	private static final int OUTPUT = 8;
	private int id;
	
	@Override
	public NonNullList<ItemStack> getInventory()
	{
		return inventory;
	}
	
	@Override
	public String getName() 
	{
		return "container.gun_workbench";
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(this.getName());
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.size();
	}
	
	public void craft(int id, CraftMode mode)
	{
		setCraftMode(mode);
		boolean craft = false;
		
		if(getStackInSlot(OUTPUT).getItem() instanceof ItemAmmo && getCurrentCraftingMode() == CraftMode.AMMO)
		{
			ItemStack stack = getStackInSlot(OUTPUT);
			ItemAmmo ammo = (ItemAmmo)stack.getItem();
			
			if(ammo == getItemByID(id))
			{
				if(stack.getCount() + ammo.getCraftCount(ammo) > 30)
				{
					return;
				}
			}
		}
		
		else if(!getStackInSlot(OUTPUT).isEmpty())
		{
			return;
		}
		
		if(getItemByID(id) instanceof ICraftable)
		{
			final List<ItemStack> recipe = ((ICraftable)getItemByID(id)).getCraftingRecipe();
			
			for(int i = 0; i < recipe.size(); i++)
			{
				ItemStack recipeStack = recipe.get(i);
				ItemStack ingredientStack = getStackInSlot(i);
				
				if(ItemStack.areItemsEqual(recipeStack, ingredientStack))
				{
					if(ingredientStack.getCount() >= recipeStack.getCount())
					{
						craft = true;
					}
					else return;
				}
				
				else return;
			}
		}
		
		if(craft)
		{
			//Stack removal
			Item toCraft = getItemByID(id);
			List<ItemStack> recipe = ((ICraftable)toCraft).getCraftingRecipe();
			for(int i = 0; i < getCraftingInventory(); i++)
			{
				ItemStack ing = getStackInSlot(i);
				
				for(int j = 0; j < recipe.size(); j++)
				{
					ItemStack craftStack = recipe.get(j);
					
					if(ing.getItem() == craftStack.getItem())
					{
						ing.shrink(craftStack.getCount());
					}
				}
			}
			
			//Put the right item and amount into the output slot
			if(((ICraftable)getItemByID(id)) instanceof ItemAmmo)
			{
				setInventorySlotContents(OUTPUT, new ItemStack(getItemByID(id), getStackInSlot(OUTPUT).getCount() + ((ItemAmmo)getItemByID(id)).getCraftCount(getItemByID(id))));
			}
			
			else setInventorySlotContents(OUTPUT, new ItemStack(getItemByID(id)));
		}
	}
	
	public int getCraftingInventory()
	{
		return getSizeInventory() - 1;
	}
	
	public Item getItemByID(int id)
	{
		if(getCurrentCraftingMode() == CraftMode.GUN)
		{
			if(id < GUNS.size())
			{
				return GUNS.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.AMMO)
		{
			if(id < AMMO.size())
			{
				return AMMO.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.ATTACHMENT)
		{
			if(id < ATTACHMENT.size())
			{
				return ATTACHMENT.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.CLOTHING)
		{
			if(id < CLOTHING.size())
			{
				return CLOTHING.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.HEALING)
		{
			if(id < HEALING.size())
			{
				return HEALING.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.THROWABLES)
		{
			if(id < THROWABLES.size())
			{
				return THROWABLES.get(id);
			}
		}
		
		else if(getCurrentCraftingMode() == CraftMode.OTHER)
		{
			if(id < OTHER.size())
			{
				return OTHER.get(id);
			}
		}
		
		return Items.AIR;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		return compound;
	}
	
	public int setID(int id)
	{
		return this.id = id;
	}
	
	public enum CraftMode
	{	
		GUN("Gun", TileEntityGunWorkbench.GUNS),
		AMMO("Ammo", TileEntityGunWorkbench.AMMO),
		ATTACHMENT("Attachment", TileEntityGunWorkbench.ATTACHMENT),
		CLOTHING("Clothing", TileEntityGunWorkbench.CLOTHING),
		HEALING("Healing", TileEntityGunWorkbench.HEALING),
		THROWABLES("Throwables", TileEntityGunWorkbench.THROWABLES),
		OTHER("Other", TileEntityGunWorkbench.OTHER);
		
		private String name;
		private Collection group;
		
		private CraftMode(String name, final Collection group) 
		{
			this.name = name;
			this.group = group;
		}
		
		public String getName()
		{
			return name;
		}
		
		public Collection getGroup()
		{
			return group;
		}
	}
	
	public CraftMode nextMode()
	{
		int i = mode.ordinal();
		if(i+1 < mode.values().length)
		{
			i++;
			return setCraftMode(mode.values()[i]);
		}
		else
		{
			return setCraftMode(mode.values()[0]);
		}
	}
	
	public CraftMode getCurrentCraftingMode()
	{
		return mode;
	}
	
	public CraftMode setCraftMode(CraftMode mode)
	{
		return this.mode = mode;
	}
}
