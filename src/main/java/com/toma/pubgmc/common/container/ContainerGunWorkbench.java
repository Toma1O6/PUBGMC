package com.toma.pubgmc.common.container;

import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGunWorkbench extends Container
{
	private final TileEntityGunWorkbench te;
	
	public ContainerGunWorkbench(TileEntityGunWorkbench te, InventoryPlayer playerInv)
	{
		this.te = te;
		
		//slots
		this.addSlotToContainer(new Slot(te, 8, 118, 72));
		
		for(int x = 0; x < 4; x++)
		{
			this.addSlotToContainer(new Slot(te, x, x*18 + 8, 22));
		}
		
		for(int x = 0; x < 4; x++)
		{
			this.addSlotToContainer(new Slot(te, x + 4, x*18 + 8, 40));
		}
		
		///player
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 111 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 169));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.te);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = this.listeners.get(i);
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        
        if(slot != null && slot.getHasStack())
        {
        	ItemStack stack1 = slot.getStack();
        	stack = stack1.copy();

        	if(index == 0)
        	{
        		if(!this.mergeItemStack(stack1, 9, 44, false))
        		{
        			return ItemStack.EMPTY;
        		}
        		
        		slot.onSlotChange(stack1, stack);
        	}
            if(index > 8 && index <= 44)
            {
            	if(!this.mergeItemStack(stack1, 1, 9, false))
            	{
            		return ItemStack.EMPTY;
            	}
            	
            	slot.onSlotChange(stack1, stack);
            }
            
            else if(index > 0 && index <= 8)
            {
            	if(!this.mergeItemStack(stack1, 9, 44, false))
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
			InventoryHelper.dropInventoryItems(playerIn.world, playerIn, te);
		}
	}
}
