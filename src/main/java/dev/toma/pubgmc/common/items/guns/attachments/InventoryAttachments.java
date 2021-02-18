package dev.toma.pubgmc.common.items.guns.attachments;

import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.init.PMCRegistry;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryAttachments extends InventoryBasic {
    private boolean using = false;
    private ItemStack gun;

    public InventoryAttachments(int slots, ItemStack gun) {
        super("Weapon Attachments", true, slots);
        this.gun = gun;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        // put attachments from the gun into correct slots
        this.setInventorySlotContents(0, this.getBarrelAttachment(gun));
        this.setInventorySlotContents(1, this.getGripAttachment(gun));
        this.setInventorySlotContents(2, this.getMagazineAttachment(gun));
        this.setInventorySlotContents(3, this.getStockAttachment(gun));
        this.setInventorySlotContents(4, this.getScopeAttachment(gun));
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        resetNBT();
        for(int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack stack = this.getStackInSlot(i);
            if(!stack.isEmpty() && stack.getItem() instanceof ItemAttachment) {
                if(GunBase.canAttachAttachment((GunBase)gun.getItem(), (ItemAttachment)stack.getItem())) {
                    gun.getTagCompound().setInteger(IAttachment.Type.values()[i].getName(), ((ItemAttachment)stack.getItem()).getID(stack.getItem()));
                } else {
                    if(!player.world.isRemote) {
                        EntityItem item = new EntityItem(player.world, player.posX, player.posY + player.getEyeHeight(), player.posZ, stack.copy());
                        item.setPickupDelay(30);
                        player.world.spawnEntity(item);
                    }
                }
            }
        }
    }

    private void resetNBT() {
        NBTTagCompound nbt = gun.getTagCompound();
        for(int i = 0; i < IAttachment.Type.values().length; i++) {
            nbt.setInteger(IAttachment.Type.values()[i].getName(), 0);
        }
        gun.setTagCompound(nbt);
    }

    @Override
    public int getSizeInventory() {
        return 7;
    }

    private ItemStack getBarrelAttachment(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int attachment = stack.getTagCompound().getInteger("barrel") - 1;
            if(attachment < 0) {
                return ItemStack.EMPTY;
            }
            int weaponType = 0;
            if (gun.getGunType() == GunBase.GunType.SMG)
                weaponType = 1;
            else if (gun.getGunType() == GunBase.GunType.AR)
                weaponType = 2;
            else if (gun.getGunType() == GunBase.GunType.DMR || gun.getGunType() == GunBase.GunType.SR)
                weaponType = 3;
            Item[][] attachments = {
                    {PMCRegistry.PMCItems.SILENCER_PISTOL},
                    {PMCRegistry.PMCItems.SILENCER_SMG, PMCRegistry.PMCItems.COMPENSATOR_SMG},
                    {PMCRegistry.PMCItems.SILENCER_AR, PMCRegistry.PMCItems.COMPENSATOR_AR},
                    {PMCRegistry.PMCItems.SILENCER_SNIPER, PMCRegistry.PMCItems.COMPENSATOR_SNIPER}
            };

            return new ItemStack(attachments[weaponType][attachment]);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack getMagazineAttachment(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int magazine = stack.getTagCompound().getInteger("magazine") - 1;
            if(magazine < 0) {
                return ItemStack.EMPTY;
            }
            Item[][] mags =
                    {
                            {PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL},
                            {PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG, PMCRegistry.PMCItems.EXTENDED_MAG_SMG, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG},
                            {PMCRegistry.PMCItems.QUICKDRAW_MAG_AR, PMCRegistry.PMCItems.EXTENDED_MAG_AR, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR},
                            {PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER, PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER, PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER}
                    };
            int weaponType = 0;
            if (gun.getGunType() == GunBase.GunType.SMG)
                weaponType = 1;
            else if (gun.getGunType() == GunBase.GunType.AR)
                weaponType = 2;
            else if (gun.getGunType() == GunBase.GunType.DMR || gun.getGunType() == GunBase.GunType.SR)
                weaponType = 3;

            return new ItemStack(mags[weaponType][magazine]);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack getGripAttachment(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int grip = stack.getTagCompound().getInteger("grip");

            //cannot be 0 because of the check while attaching; anything bigger will simply return the AttachmentGripAngled grip
            return grip < 1 ? ItemStack.EMPTY : grip == 1 ? new ItemStack(PMCRegistry.PMCItems.GRIP_VERTICAL) : new ItemStack(PMCRegistry.PMCItems.GRIP_ANGLED);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack getScopeAttachment(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int scope = stack.getTagCompound().getInteger("scope");

            switch (scope) {
                case 1:
                    return new ItemStack(PMCRegistry.PMCItems.RED_DOT);
                case 2:
                    return new ItemStack(PMCRegistry.PMCItems.HOLOGRAPHIC);
                case 3:
                    return new ItemStack(PMCRegistry.PMCItems.SCOPE2X);
                case 4:
                    return new ItemStack(PMCRegistry.PMCItems.SCOPE4X);
                case 5:
                    return new ItemStack(PMCRegistry.PMCItems.SCOPE8X);
                case 6:
                    return new ItemStack(PMCRegistry.PMCItems.SCOPE15X);
            }
        }

        return ItemStack.EMPTY;
    }

    private ItemStack getStockAttachment(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            int id = stack.getTagCompound().getInteger("stock");

            if (id == 1 && (gun.getGunType() == GunBase.GunType.PISTOL || gun.getGunType() == GunBase.GunType.SR)) {
                return new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER);
            } else if (id == 1 && gun.getGunType() == GunBase.GunType.SHOTGUN) {
                return new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN);
            } else if (id == 2) {
                return new ItemStack(PMCRegistry.PMCItems.CHEEKPAD);
            }
        }

        return ItemStack.EMPTY;
    }

    private void detachAttachments(ItemStack stack) {
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();

            if (stack.hasTagCompound()) {
                NBTTagCompound att = stack.getTagCompound();

                if (att.getInteger("barrel") > 0) {
                    if (att.getInteger("barrel") == 1) {
                        switch (gun.getGunType()) {
                            case PISTOL:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.SILENCER_PISTOL));
                                break;

                            case SMG:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.SILENCER_SMG));
                                break;

                            case AR:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.SILENCER_AR));
                                break;

                            case DMR: case SR:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.SILENCER_SNIPER));
                                break;

                            default:
                                break;
                        }

                        att.setInteger("barrel", 0);
                    } else if (att.getInteger("barrel") == 2) {
                        switch (gun.getGunType()) {
                            case SMG:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_SMG));
                                break;

                            case AR:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_AR));
                                break;

                            case DMR: case SR:
                                setInventorySlotContents(2, new ItemStack(PMCRegistry.PMCItems.COMPENSATOR_SNIPER));
                                break;

                            default:
                                break;
                        }

                        att.setInteger("barrel", 0);
                    }
                }

                if (att.getInteger("scope") > 0) {
                    if (att.getInteger("scope") == 1) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.RED_DOT));
                    } else if (att.getInteger("scope") == 2) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.HOLOGRAPHIC));
                    } else if (att.getInteger("scope") == 3) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.SCOPE2X));
                    } else if (att.getInteger("scope") == 4) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.SCOPE4X));
                    } else if (att.getInteger("scope") == 5) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.SCOPE8X));
                    } else if (att.getInteger("scope") == 6) {
                        setInventorySlotContents(1, new ItemStack(PMCRegistry.PMCItems.SCOPE15X));
                    }

                    att.setInteger("scope", 0);
                }

                if (att.getInteger("grip") > 0) {
                    if (att.getInteger("grip") == 1) {
                        setInventorySlotContents(3, new ItemStack(PMCRegistry.PMCItems.GRIP_VERTICAL));
                    } else if (att.getInteger("grip") == 2) {
                        setInventorySlotContents(3, new ItemStack(PMCRegistry.PMCItems.GRIP_ANGLED));
                    }

                    att.setInteger("grip", 0);
                }

                if (att.getInteger("stock") > 0) {
                    if (att.getInteger("stock") == 1) {
                        switch (gun.getGunType()) {
                            case PISTOL: case SR:
                                setInventorySlotContents(5, new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER));
                                break;
                            case SHOTGUN:
                                setInventorySlotContents(5, new ItemStack(PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN));
                                break;
                            default:
                                break;
                        }
                    } else if (att.getInteger("stock") == 2) {
                        setInventorySlotContents(5, new ItemStack(PMCRegistry.PMCItems.CHEEKPAD));
                    }

                    att.setInteger("stock", 0);
                }

                if (att.getInteger("magazine") > 0) {
                    if (att.getInteger("magazine") == 1) {
                        switch (gun.getGunType()) {
                            case PISTOL:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL));
                                break;
                            case SMG:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG));
                                break;
                            case AR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_AR));
                                break;
                            case DMR: case SR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER));
                                break;
                            default:
                                break;
                        }
                    } else if (att.getInteger("magazine") == 2) {
                        switch (gun.getGunType()) {
                            case PISTOL:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL));
                                break;
                            case SMG:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SMG));
                                break;
                            case AR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_AR));
                                break;
                            case DMR: case SR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER));
                                break;
                            default:
                                break;
                        }
                    } else if (att.getInteger("magazine") == 3) {
                        switch (gun.getGunType()) {
                            case PISTOL:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL));
                                break;
                            case SMG:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG));
                                break;
                            case AR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR));
                                break;
                            case DMR: case SR:
                                setInventorySlotContents(4, new ItemStack(PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER));
                                break;
                            default:
                                break;
                        }
                    }

                    att.setInteger("magazine", 0);
                }
            } else {
                PUBGMCUtil.createNBT(stack);
            }
        }
    }
}
