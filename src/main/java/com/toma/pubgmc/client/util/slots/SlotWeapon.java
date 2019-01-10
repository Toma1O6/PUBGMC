package com.toma.pubgmc.client.util.slots;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.attachments.InventoryAttachments;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWeapon extends Slot
{	
	public SlotWeapon(int id)
	{
		super(new InventoryAttachments(), id, 0, 0);
	}
	
	public SlotWeapon(InventoryAttachments inv, int id, int x, int y)
	{
		super(inv, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return stack.getItem() instanceof GunBase;
	}
}
