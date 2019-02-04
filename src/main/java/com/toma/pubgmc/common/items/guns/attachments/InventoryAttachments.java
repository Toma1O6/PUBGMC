package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryAttachments extends InventoryBasic
{
	private ItemStack prevWepStack;
	private ItemAttachment attach;
	private GunType type;
	private boolean using = false;
	
	public InventoryAttachments()
	{
		super("Weapon Crafting Table", true, 7);
	}
	
	@Override
	public void markDirty()
	{
		ItemStack gun = getStackInSlot(0);
		ItemStack gunToDetach = getStackInSlot(6);
		if(using)
		{
			return;
		}
		
		if(gunToDetach.getItem() instanceof GunBase)
		{
			using = true;
			detachAttachments(gunToDetach);
			using = false;
		}
		
		if(gun != prevWepStack || getStackInSlot(1).getItem() != Items.AIR || getStackInSlot(2).getItem() != Items.AIR || getStackInSlot(3).getItem() != Items.AIR || getStackInSlot(4).getItem() != Items.AIR || getStackInSlot(5).getItem() != Items.AIR)
		{
			using = true;
			
			PUBGMCUtil.createNBT(gun);
			
			if(gun.hasTagCompound())
			{
				//Scopes
				if(getStackInSlot(1).getItem() instanceof ItemAttachment)
				{
					ItemAttachment attach = (ItemAttachment)getStackInSlot(1).getItem();

					if(((GunBase)gun.getItem()).acceptedAttachments().contains(attach))
					{
						gun.getTagCompound().setInteger("scope", attach.getID(getStackInSlot(1).getItem()));
						removeStackFromSlot(1);
					}
				}
				
				//Barrel
				if(getStackInSlot(2).getItem() instanceof ItemAttachment)
				{
					ItemAttachment attach = (ItemAttachment)getStackInSlot(2).getItem();
					if(((GunBase)gun.getItem()).acceptedAttachments().contains(attach))
					{
						gun.getTagCompound().setInteger("barrel", attach.getID(getStackInSlot(2).getItem()));
						removeStackFromSlot(2);
					}
				}
				
				//Grip
				if(getStackInSlot(3).getItem() instanceof ItemAttachment)
				{
					ItemAttachment attach = (ItemAttachment)getStackInSlot(3).getItem();
					if(((GunBase)gun.getItem()).acceptedAttachments().contains(attach))
					{
						gun.getTagCompound().setInteger("grip", attach.getID(getStackInSlot(3).getItem()));
						removeStackFromSlot(3);
					}
				}
				
				//Magazines
				if(getStackInSlot(4).getItem() instanceof ItemAttachment)
				{
					ItemAttachment attach = (ItemAttachment)getStackInSlot(4).getItem();
					
					if(((GunBase)gun.getItem()).acceptedAttachments().contains(attach))
					{
						gun.getTagCompound().setInteger("magazine", attach.getID(getStackInSlot(4).getItem()));
						removeStackFromSlot(4);
					}
				}
				
				//Stock
				if(getStackInSlot(5).getItem() instanceof ItemAttachment)
				{
					ItemAttachment attach = (ItemAttachment)getStackInSlot(5).getItem();
					
					if(((GunBase)gun.getItem()).acceptedAttachments().contains(attach))
					{
						gun.getTagCompound().setInteger("stock", attach.getID(getStackInSlot(5).getItem()));
						removeStackFromSlot(5);
					}
				}
				
				prevWepStack = gun;
			}
			
			using = false;
		}
	}
	
	@Override
	public int getSizeInventory() 
	{
		return 7;
	}
	
	private void detachAttachments(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			
			if(stack.hasTagCompound())
			{
				NBTTagCompound att = stack.getTagCompound();
				
				if(att.getInteger("barrel") > 0)
				{
					if(att.getInteger("barrel") == 1)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(2, new ItemStack(PMCItems.SILENCER_PISTOL));
							break;
							
							case SMG: setInventorySlotContents(2, new ItemStack(PMCItems.SILENCER_SMG));
							break;
							
							case AR: setInventorySlotContents(2, new ItemStack(PMCItems.SILENCER_AR));
							break;
							
							case DMR: setInventorySlotContents(2, new ItemStack(PMCItems.SILENCER_SNIPER));
							break;
							
							case SR: setInventorySlotContents(2, new ItemStack(PMCItems.SILENCER_SNIPER));
							break;
							
							default: break;
						}
						
						att.setInteger("barrel", 0);
					}
					
					else if(att.getInteger("barrel") == 2)
					{
						switch(gun.getGunType())
						{
							case SMG: setInventorySlotContents(2, new ItemStack(PMCItems.COMPENSATOR_SMG));
							break;
							
							case AR: setInventorySlotContents(2, new ItemStack(PMCItems.COMPENSATOR_AR));
							break;
							
							case DMR: setInventorySlotContents(2, new ItemStack(PMCItems.COMPENSATOR_SNIPER));
							break;
							
							case SR: setInventorySlotContents(2, new ItemStack(PMCItems.COMPENSATOR_SNIPER));
							break;
							
							default: break;
						}
						
						att.setInteger("barrel", 0);
					}
				}
				
				if(att.getInteger("scope") > 0)
				{
					if(att.getInteger("scope") == 1)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.RED_DOT));
					}
					
					else if(att.getInteger("scope") == 2)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.HOLOGRAPHIC));
					}
					
					else if(att.getInteger("scope") == 3)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.SCOPE2X));
					}
					
					else if(att.getInteger("scope") == 4)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.SCOPE4X));
					}
					
					else if(att.getInteger("scope") == 5)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.SCOPE8X));
					}
					
					else if(att.getInteger("scope") == 6)
					{
						setInventorySlotContents(1, new ItemStack(PMCItems.SCOPE15X));
					}
					
					att.setInteger("scope", 0);
				}
				
				if(att.getInteger("grip") > 0)
				{
					if(att.getInteger("grip") == 1)
					{
						setInventorySlotContents(3, new ItemStack(PMCItems.GRIP_VERTICAL));
					}
					
					else if(att.getInteger("grip") == 2)
					{
						setInventorySlotContents(3, new ItemStack(PMCItems.GRIP_ANGLED));
					}
					
					att.setInteger("grip", 0);
				}
				
				if(att.getInteger("stock") > 0)
				{
					if(att.getInteger("stock") == 1)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(5, new ItemStack(PMCItems.BULLET_LOOPS_SNIPER)); break;
							case SHOTGUN: setInventorySlotContents(5, new ItemStack(PMCItems.BULLET_LOOPS_SHOTGUN)); break;
							case SR: setInventorySlotContents(5, new ItemStack(PMCItems.BULLET_LOOPS_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("stock") == 2)
					{
						setInventorySlotContents(5, new ItemStack(PMCItems.CHEEKPAD));
					}
					
					att.setInteger("stock", 0);
				}
				
				if(att.getInteger("magazine") > 0)
				{
					if(att.getInteger("magazine") == 1)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCItems.QUICKDRAW_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCItems.QUICKDRAW_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCItems.QUICKDRAW_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCItems.QUICKDRAW_MAG_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("magazine") == 2)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_MAG_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("magazine") == 3)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)); break;
							default: break;
						}
					}
					
					att.setInteger("magazine", 0);
				}
			}
			
			else
			{
				PUBGMCUtil.createNBT(stack);
			}
		}
	}
}
