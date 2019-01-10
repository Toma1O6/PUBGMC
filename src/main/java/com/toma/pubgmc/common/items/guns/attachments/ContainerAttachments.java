package com.toma.pubgmc.common.items.guns.attachments;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.client.util.slots.SlotAttachment;
import com.toma.pubgmc.client.util.slots.SlotWeapon;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAttachments extends Container
{
	private InventoryAttachments inv;
	private InventoryPlayer playerInv;
	private List<Slot> slots = new ArrayList<Slot>();
	
	
	public ContainerAttachments(InventoryPlayer playerInv)
	{
		inv = new InventoryAttachments();
		slots.clear();
		this.playerInv = playerInv;
		
		addSlotToContainer(new SlotWeapon(inv, 0, 80, 31));
		slots.add(new SlotWeapon(0));
		
		addSlotToContainer(new SlotAttachment(inv, 1, 80, 12, Type.SCOPE));
		slots.add(new SlotAttachment(Type.SCOPE, 1));
		
		addSlotToContainer(new SlotAttachment(inv, 2, 20, 31, Type.BARREL));
		slots.add(new SlotAttachment(Type.BARREL, 2));
		
		addSlotToContainer(new SlotAttachment(inv, 3, 42, 50, Type.GRIP));
		slots.add(new SlotAttachment(Type.GRIP, 3));
		
		addSlotToContainer(new SlotAttachment(inv, 4, 80, 50, Type.MAGAZINE));
		slots.add(new SlotAttachment(Type.MAGAZINE, 4));
		
		addSlotToContainer(new SlotAttachment(inv, 5, 124, 31, Type.STOCK));
		slots.add(new SlotAttachment(Type.STOCK, 5));
		
		addSlotToContainer(new SlotWeapon(inv, 6, 152, 55));
		slots.add(new SlotWeapon(6));
		
		//Block slots
		for(int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(playerInv, x + 9, x * 18 + 8, 88));
		}
		for(int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(playerInv, x + 18, x * 18 + 8, 106));
		}
		for(int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(playerInv, x + 27, x * 18 + 8, 124));
		}
		
		//Player slots
		for(int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(playerInv, x, x * 18 + 8, 146));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			//Getting the weapons and attachments into right place in the container
			if(index >= 7 && index <= 42)
			{
				if(slot.getStack().getItem() instanceof GunBase)
				{
					if(inventorySlots.get(0).getHasStack())
					{
						return ItemStack.EMPTY;
					}
					
					else 
					{
						inventorySlots.get(0).putStack(stack);
						slot.putStack(ItemStack.EMPTY);
					}
				}
				
				else if(slot.getStack().getItem() instanceof ItemAttachment)
				{
					if(((ItemAttachment)slot.getStack().getItem()).getType() == Type.SCOPE)
					{
						if(inventorySlots.get(1).getHasStack())
						{
							return ItemStack.EMPTY;
						}
						
						else
						{
							inventorySlots.get(1).putStack(stack);
							slot.putStack(ItemStack.EMPTY);
						}
					}
					
					else if(((ItemAttachment)slot.getStack().getItem()).getType() == Type.BARREL)
					{
						if(inventorySlots.get(2).getHasStack())
						{
							return ItemStack.EMPTY;
						}
						
						else
						{
							inventorySlots.get(2).putStack(stack);
							slot.putStack(ItemStack.EMPTY);
						}
					}
					
					else if(((ItemAttachment)slot.getStack().getItem()).getType() == Type.GRIP)
					{
						if(inventorySlots.get(3).getHasStack())
						{
							return ItemStack.EMPTY;
						}
						
						else
						{
							inventorySlots.get(3).putStack(stack);
							slot.putStack(ItemStack.EMPTY);
						}
					}
					
					else if(((ItemAttachment)slot.getStack().getItem()).getType() == Type.MAGAZINE)
					{
						if(inventorySlots.get(4).getHasStack())
						{
							return ItemStack.EMPTY;
						}
						
						else
						{
							inventorySlots.get(4).putStack(stack);
							slot.putStack(ItemStack.EMPTY);
						}
					}
					
					else if(((ItemAttachment)slot.getStack().getItem()).getType() == Type.STOCK)
					{
						if(inventorySlots.get(5).getHasStack())
						{
							return ItemStack.EMPTY;
						}
						
						else
						{
							inventorySlots.get(5).putStack(stack);
							slot.putStack(ItemStack.EMPTY);
						}
					}
					
					else return ItemStack.EMPTY;
				}
			}
			
			else
			{
				if(!this.mergeItemStack(stack1, 7, 43, false))
				{
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(stack1, stack);
			}
			
			//Getting items from the gui
			if(index >= 0 && index <= 6)
			{
                if (!this.mergeItemStack(stack1, 7, 43, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack1, stack);
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		if(!playerIn.world.isRemote)
		{
			InventoryHelper.dropInventoryItems(playerIn.world, playerIn, inv);
		}
	}
}
