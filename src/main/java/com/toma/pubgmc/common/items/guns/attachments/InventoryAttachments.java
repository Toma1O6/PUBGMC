package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
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
						if(gun.getTagCompound().getInteger("scope") > 0)
						{
							setInventorySlotContents(6, getScopeAttachment(gun));
						}
						
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
						if(gun.getTagCompound().getInteger("barrel") > 0)
						{
							setInventorySlotContents(6, getBarrelAttachment(gun));
						}
						
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
						if(gun.getTagCompound().getInteger("grip") > 0)
						{
							setInventorySlotContents(6, getGripAttachment(gun));
						}
						
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
						if(gun.getTagCompound().getInteger("magazine") > 0)
						{
							setInventorySlotContents(6, getMagazineAttachment(gun));
						}
						
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
						if(gun.getTagCompound().getInteger("stock") > 0)
						{
							setInventorySlotContents(6, getStockAttachment(gun));
						}
						
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
	
	private ItemStack getBarrelAttachment(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			int attachment = stack.getTagCompound().getInteger("barrel")-1;
			int weaponType = 0;
			if(gun.getGunType() == GunType.SMG)
				weaponType = 1;
			else if(gun.getGunType() == GunType.AR)
				weaponType = 2;
			else if(gun.getGunType() == GunType.DMR || gun.getGunType() == GunType.SR)
				weaponType = 3;
			Item[][] attachments =
			{
				{PMCRegistry.Items.SILENCER_PISTOL},
				{PMCRegistry.Items.SILENCER_SMG, PMCRegistry.Items.COMPENSATOR_SMG},
				{PMCRegistry.Items.SILENCER_AR, PMCRegistry.Items.COMPENSATOR_AR},
				{PMCRegistry.Items.SILENCER_SNIPER, PMCRegistry.Items.COMPENSATOR_SNIPER}
			};
			
			return new ItemStack(attachments[weaponType][attachment]);
		}
		return ItemStack.EMPTY;
	}
	
	private ItemStack getMagazineAttachment(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			int magazine = stack.getTagCompound().getInteger("magazine")-1;
			Item[][] mags = 
			{
				{PMCRegistry.Items.QUICKDRAW_MAG_PISTOL,PMCRegistry.Items.EXTENDED_MAG_PISTOL,PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_PISTOL},
				{PMCRegistry.Items.QUICKDRAW_MAG_SMG, PMCRegistry.Items.EXTENDED_MAG_SMG, PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SMG},
				{PMCRegistry.Items.QUICKDRAW_MAG_AR, PMCRegistry.Items.EXTENDED_MAG_AR, PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_AR},
				{PMCRegistry.Items.QUICKDRAW_MAG_SNIPER, PMCRegistry.Items.EXTENDED_MAG_SNIPER, PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SNIPER}
			};
			int weaponType = 0;
			if(gun.getGunType() == GunType.SMG)
				weaponType = 1;
			else if(gun.getGunType() == GunType.AR)
				weaponType = 2;
			else if(gun.getGunType() == GunType.DMR || gun.getGunType() == GunType.SR)
				weaponType = 3;
			
			return new ItemStack(mags[weaponType][magazine]);
		}
		return ItemStack.EMPTY;
	}
	
	private ItemStack getGripAttachment(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			int grip = stack.getTagCompound().getInteger("grip");
			
			//cannot be 0 because of the check while attaching; anything bigger will simply return the angled grip
			return grip == 1 ? new ItemStack(PMCRegistry.Items.GRIP_VERTICAL) : new ItemStack(PMCRegistry.Items.GRIP_ANGLED);
		}
		return ItemStack.EMPTY;
	}
	
	private ItemStack getScopeAttachment(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			int scope = stack.getTagCompound().getInteger("scope");
			
			switch(scope)
			{
				case 1: return new ItemStack(PMCRegistry.Items.RED_DOT);
				case 2: return new ItemStack(PMCRegistry.Items.HOLOGRAPHIC);
				case 3: return new ItemStack(PMCRegistry.Items.SCOPE2X);
				case 4: return new ItemStack(PMCRegistry.Items.SCOPE4X);
				case 5: return new ItemStack(PMCRegistry.Items.SCOPE8X);
				case 6: return new ItemStack(PMCRegistry.Items.SCOPE15X);
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	private ItemStack getStockAttachment(ItemStack stack)
	{
		if(stack.getItem() instanceof GunBase)
		{
			GunBase gun = (GunBase)stack.getItem();
			int id = stack.getTagCompound().getInteger("stock");
			
			if(id == 1 && (gun.getGunType() == GunType.PISTOL || gun.getGunType() == GunType.SR))
			{
				return new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SNIPER);
			}
			
			else if(id == 1 && gun.getGunType() == GunType.SHOTGUN)
			{
				return new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SHOTGUN);
			}
			
			else if(id == 2)
			{
				return new ItemStack(PMCRegistry.Items.CHEEKPAD);
			}
		}
		
		return ItemStack.EMPTY;
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
							case PISTOL: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.SILENCER_PISTOL));
							break;
							
							case SMG: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.SILENCER_SMG));
							break;
							
							case AR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.SILENCER_AR));
							break;
							
							case DMR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.SILENCER_SNIPER));
							break;
							
							case SR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.SILENCER_SNIPER));
							break;
							
							default: break;
						}
						
						att.setInteger("barrel", 0);
					}
					
					else if(att.getInteger("barrel") == 2)
					{
						switch(gun.getGunType())
						{
							case SMG: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.COMPENSATOR_SMG));
							break;
							
							case AR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.COMPENSATOR_AR));
							break;
							
							case DMR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.COMPENSATOR_SNIPER));
							break;
							
							case SR: setInventorySlotContents(2, new ItemStack(PMCRegistry.Items.COMPENSATOR_SNIPER));
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
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.RED_DOT));
					}
					
					else if(att.getInteger("scope") == 2)
					{
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.HOLOGRAPHIC));
					}
					
					else if(att.getInteger("scope") == 3)
					{
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.SCOPE2X));
					}
					
					else if(att.getInteger("scope") == 4)
					{
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.SCOPE4X));
					}
					
					else if(att.getInteger("scope") == 5)
					{
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.SCOPE8X));
					}
					
					else if(att.getInteger("scope") == 6)
					{
						setInventorySlotContents(1, new ItemStack(PMCRegistry.Items.SCOPE15X));
					}
					
					att.setInteger("scope", 0);
				}
				
				if(att.getInteger("grip") > 0)
				{
					if(att.getInteger("grip") == 1)
					{
						setInventorySlotContents(3, new ItemStack(PMCRegistry.Items.GRIP_VERTICAL));
					}
					
					else if(att.getInteger("grip") == 2)
					{
						setInventorySlotContents(3, new ItemStack(PMCRegistry.Items.GRIP_ANGLED));
					}
					
					att.setInteger("grip", 0);
				}
				
				if(att.getInteger("stock") > 0)
				{
					if(att.getInteger("stock") == 1)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(5, new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SNIPER)); break;
							case SHOTGUN: setInventorySlotContents(5, new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SHOTGUN)); break;
							case SR: setInventorySlotContents(5, new ItemStack(PMCRegistry.Items.BULLET_LOOPS_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("stock") == 2)
					{
						setInventorySlotContents(5, new ItemStack(PMCRegistry.Items.CHEEKPAD));
					}
					
					att.setInteger("stock", 0);
				}
				
				if(att.getInteger("magazine") > 0)
				{
					if(att.getInteger("magazine") == 1)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.QUICKDRAW_MAG_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("magazine") == 2)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_MAG_SNIPER)); break;
							default: break;
						}
					}
					
					else if(att.getInteger("magazine") == 3)
					{
						switch(gun.getGunType())
						{
							case PISTOL: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_PISTOL)); break;
							case SMG: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SMG)); break;
							case AR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_AR)); break;
							case DMR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SNIPER)); break;
							case SR: setInventorySlotContents(4, new ItemStack(PMCRegistry.Items.EXTENDED_QUICKDRAW_MAG_SNIPER)); break;
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
