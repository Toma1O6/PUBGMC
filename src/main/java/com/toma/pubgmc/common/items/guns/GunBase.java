package com.toma.pubgmc.common.items.guns;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketFiremode;
import com.toma.pubgmc.common.network.sp.PacketCreateNBT;
import com.toma.pubgmc.common.network.sp.PacketReloadingSP;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.ICraftable;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/**
 * @author Toma1O6
 * This is the core class for all guns
 */
public abstract class GunBase extends PMCItem implements ICraftable
{
	public static final List<GunBase> GUNS = new ArrayList<GunBase>();
	
	private float damage = 1.0f;
	private double velocity = 1.0;
	private double gravity = 0.075;
	private int gravityStart;
	private float horizontal_recoil = 0f;
	private float vertical_recoil = 0f;
	private int ammo;
	private double reloadTime = 100;
	private int reloadDelay = 10;
	private int rate = 10;
	private AmmoType ammotype;
	private Firemode firemode;
	private boolean canSwitchFiremode;
	private boolean canShoot;
	private boolean burst = false;
	private boolean auto = false;
	private boolean silenced;
	private float gun_volume, gun_volume_s;
	private ReloadType reloadType;
	private GunType gunType;
	private boolean hasTwoRoundBurst = false;
	private SoundEvent gun_shoot, gun_silenced;
	protected List<Item> attachments = new ArrayList<Item>();
	
	private ItemAmmo ammoItem;
	private int ammoCount = 0;
	
	public GunBase(String name)
	{
		super(name);
		setCreativeTab(Pubgmc.pmcitemstab);
		setMaxStackSize(1);
		GUNS.add(this);
		TileEntityGunWorkbench.WEAPONS.add(this);
	}
	
	/**
	 * The weapon reload sound
	 * @return - the reload sound
	 */
	public abstract SoundEvent getWeaponReloadSound();
	
	/**
	 * Check if gun can accept attachment, used in attachment inventory
	 * @param attachment - the attachment item
	 * @return if the attachment is supported by the weapon
	 */
	public List<Item> acceptedAttachments()
	{
		return attachments;
	}
	
	/**
	 * Gets maximum possible amount of bullet gun can have loaded
	 * @param stack - the gun itemstack
	 * @return the weapon ammo limit
	 */
	public abstract int getWeaponAmmoLimit(ItemStack stack);
	
	/**
	 * Used to spawn bullet entity, called from packet
	 * 
	 * @param world
	 * @param player
	 * @param stack
	 */
	public void shoot(World world, EntityPlayer player, ItemStack stack)
	{
		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        if(this.hasAmmo(stack) || player.capabilities.isCreativeMode && !data.isReloading())
        {
        	//NOW IT IS HANDLED THRU CLIENT PACKET INSIDE SHOOT PACKET
        	//world.playSound(null, player.posX, player.posY, player.posZ, playWeaponSound(stack), SoundCategory.PLAYERS, playWeaponSoundVolume(stack), 1.0f);
        	
        	if(!world.isRemote)
        	{
                EntityBullet bullet = new EntityBullet(world, player, this);
                world.spawnEntity(bullet);
                if(!player.capabilities.isCreativeMode)
                {
                    stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
        	}
        }
	}
	
	/**
	 * Function for getting the right volume of the weapon
	 * @param stack - the weapon
	 * @return volume
	 */
	public float playWeaponSoundVolume(ItemStack stack)
	{
		float volume = 1f;
		
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("barrel") == 1)
			{
				volume = getGunSilencedVolume();
			}
			else volume = getGunVolume();
		}
		
		else PUBGMCUtil.createNBT(stack);
		return volume;
	}
	
	/**
	 * Function for playing the right weapon sound
	 * If silencer is equipped the silenced sound will be played 
	 * 
	 * @param stack - the itemstack which is shooting
	 * @return the soundEvent
	 */
	public SoundEvent playWeaponSound(ItemStack stack)
	{
		SoundEvent event = SoundEvents.BLOCK_LEVER_CLICK;
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("barrel") == 1)
			{
				if(getGunSilencedSound() != null)
				{
					event = getGunSilencedSound();
				}
			}
			
			else
			{
				event = getGunSound();
			}
		}
		else
		{
			PUBGMCUtil.createNBT(stack);
			event = getGunSound();
		}
		
		return event;
	}
	
	/**
	 * Function called for refilling the weapons with bullets
	 * @param player - the player who is reloading
	 */
	public void reload(EntityPlayer player)
	{
		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		if(ConfigHandler.enableGuns)
		{
			ItemStack heldItem = player.getHeldItemMainhand();
			
			if(heldItem.getItem() instanceof GunBase)
			{
				data.setAiming(false);
				GunBase gun = (GunBase)heldItem.getItem();
				
				if((heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem) || !hasPlayerAmmoForGun(player, gun)) && data.isReloading())
				{
					data.setReloading(false);
					PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
				}
				
				if(heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
				{
					if(gun.getReloadType() == ReloadType.MAGAZINE)
					{
						while((hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
						{
							int ammoInGun = heldItem.getTagCompound().getInteger("ammo");
							int ammoToFill = gun.getWeaponAmmoLimit(heldItem) - ammoInGun;
							int ammoInInventory = ammoCount;
							
							if(ammoToFill > ammoInInventory) ammoToFill = ammoInInventory;
							
							if(!player.capabilities.isCreativeMode)
							{
								ItemAmmo ammo = (ItemAmmo)ammoItem.getAmmoItem();
								player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, ammoToFill, null);
							}
							
							heldItem.getTagCompound().setInteger("ammo", ammoInGun + ammoToFill);
						}
						
						data.setReloading(false);
						PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
					}
					
					else if(gun.getReloadType() == ReloadType.SINGLE)
					{
						if(hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode)
						{
							//If the gun is already full and player still atempts to reload, cancel it
							if(heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem))
							{
								data.setReloading(false);
								PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
							}
							
							if(heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
							{
								ItemAmmo ammo = (ItemAmmo)ammoItem.getAmmoItem();
								
								if(!player.capabilities.isCreativeMode)
								{
									player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
								}
								
								//Increase ammo count by 1
								heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
							}
						}
					}
					
					else if(gun.getReloadType() == ReloadType.KAR98K)
					{
						if(!gun.hasAmmo(heldItem))
						{
							while((hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
							{
								ItemAmmo ammo = (ItemAmmo)ammoItem.getAmmoItem();
								
								if(!player.capabilities.isCreativeMode)
								{
									player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
								}
								
								heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
							}
							
							data.setReloading(false);
							PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
						}
						
						else if(heldItem.getTagCompound().getInteger("ammo") > 0 && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
						{
							ItemAmmo ammo = (ItemAmmo)ammoItem.getAmmoItem();
							
							if(!player.capabilities.isCreativeMode)
							{
								player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
							}
							
							heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
						}
					}
					
					else
					{
						throw new IllegalArgumentException("Unknown reload type. Report this to mod author!");
					}
				}
			}
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) 
	{
		return false;
	}
	
	//Set the firemode on both client and server
	public Firemode getNextFiremode(EntityPlayer player)
	{
		switch(this.getFiremode())
		{
			case SINGLE: 
			{
				if(canGunBurstFire())
				{
					return setFiremode(Firemode.BURST);
				}
				
				else
				{
					return setFiremode(Firemode.AUTO);
				}
			}
			
			case BURST:
			{
				if(canGunAutofire())
				{
					return setFiremode(Firemode.AUTO);
				}
				
				else
				{
					return setFiremode(Firemode.SINGLE);
				}
			}
			
			case AUTO: return setFiremode(Firemode.SINGLE);
		}
		PacketHandler.INSTANCE.sendToServer(new PacketFiremode(firemode));
		return firemode;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(entityIn instanceof EntityPlayer && !worldIn.isRemote)
		{
			EntityPlayer player = (EntityPlayer)entityIn;
			if(!player.capabilities.isCreativeMode)
			{
				if(!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("isValidWeapon"))
				{
					PUBGMCUtil.createWeaponNBT(stack, 0);
					PacketHandler.INSTANCE.sendTo(new PacketCreateNBT(0), (EntityPlayerMP)player);
				}
			}
			else
			{
				if(!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("isValidWeapon"))
				{
					PUBGMCUtil.createWeaponNBT(stack, getWeaponAmmoLimit(stack));
					PacketHandler.INSTANCE.sendTo(new PacketCreateNBT(getWeaponAmmoLimit(stack)), (EntityPlayerMP)player);
				}
			}
		}
	}
	
	public Item getAmmoFromGun()
	{
		switch(getAmmoType())
		{
			case AMMO9MM: return PMCItems.AMMO_9MM;
			case AMMO45ACP: return PMCItems.AMMO_45ACP;
			case AMMO12G: return PMCItems.AMMO_SHOTGUN;
			case AMMO556: return PMCItems.AMMO_556;
			case AMMO762: return PMCItems.AMMO_762;
			case AMMO300M: return PMCItems.AMMO_300M;
			case FLARE: return PMCItems.AMMO_FLARE;
			default: return Items.AIR;
		}
	}
	
	public ItemStack getAmmoItemStack()
	{
		return new ItemStack(getAmmoFromGun());
	}
	
	public boolean hasAmmo(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getInteger("ammo") > 0;
	}
	
	public boolean hasPlayerAmmoForGun(EntityPlayer player, GunBase gun)
	{
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			
			if(stack.getItem() instanceof ItemAmmo)
			{
				if(((ItemAmmo)stack.getItem()).type == gun.getAmmoType())
				{
					ammoCount = stack.getCount();
					ammoItem = (ItemAmmo)stack.getItem();
					return true;
				}
			}
		}
		
		if(player.capabilities.isCreativeMode)
		{
			return true;
		}
		
		return false;
	}
	
	//Description stuff
	public String descAmmoType()
	{
		switch(this.getAmmoType())
		{
			case AMMO9MM: return I18n.format("ammo.9mm");
			case AMMO12G: return I18n.format("ammo.12g");
			case AMMO45ACP: return I18n.format("ammo.45acp");
			case AMMO556: return I18n.format("ammo.556mm");
			case AMMO762: return I18n.format("ammo.762mm");
			case AMMO300M: return I18n.format("ammo.300m");
			case FLARE: return I18n.format("ammo.flare");
			default: return "Unknown ammo";
		}
	}
	
	//Description stuff
	public String getFiremodeTranslation()
	{
		switch(this.getFiremode())
		{
			case SINGLE: return I18n.format("gun.firemode.single");
			case BURST: return I18n.format("gun.firemode.burst");
			case AUTO: return I18n.format("gun.firemode.auto");
			default: return "";
		}
	}
	
	@Override
	public CraftMode getCraftMode() 
	{
		return CraftMode.Gun;
	}
	
	//Here we add all info which will be displayed on the item
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		String barrel = "default";
		String grip = "none";
		String magazine = "default";
		String stock = "default";
		String scope = "ironsight";
		
		if(stack.hasTagCompound())
		{
			NBTTagCompound c = stack.getTagCompound();
			
			if(c.getInteger("barrel") == 1) barrel = "suppressor";
			if(c.getInteger("barrel") == 2) barrel = "compensator";
			if(c.getInteger("scope") == 1) scope = "red dot sight";
			if(c.getInteger("scope") == 2) scope = "holographic";
			if(c.getInteger("scope") == 3) scope = "2X";
			if(c.getInteger("scope") == 4) scope = "4X";
			if(c.getInteger("scope") == 5) scope = "8X";
			if(c.getInteger("scope") == 6) scope = "15X";
			if(c.getInteger("grip") == 1) grip = "vertical grip";
			if(c.getInteger("grip") == 2) grip = "angled grip";
			if(c.getInteger("magazine") == 1) magazine = "quickdraw";
			if(c.getInteger("magazine") == 2) magazine = "extended";
			if(c.getInteger("magazine") == 3) magazine = "extended quickdraw";
			if(c.getInteger("stock") == 2) stock = "cheekpad";
			if(c.getInteger("stock") == 1) stock = "bullet loops";
		}
		
		tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammo") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + getAmmo(stack));
		tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firemode") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + getFiremodeTranslation());
		
		if(GuiScreen.isShiftKeyDown() && stack.hasTagCompound())
		{
			DecimalFormat f = new DecimalFormat("###.##");
			DecimalFormat g = new DecimalFormat("###.###");
			
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.damage") + ": " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + this.damage);
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.reloadtime") + ": " + TextFormatting.RESET + "" + TextFormatting.GREEN + g.format(getReloadTime(stack) / 20) + " " + I18n.format("gun.reloadtime.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.velocity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(velocity * 5.5) + " " + I18n.format("gun.velocity.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.gravity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(gravity * 20) + " " + I18n.format("gun.gravity.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firerate") + ": " + TextFormatting.RESET + "" + TextFormatting.AQUA + g.format(20.00 / this.getFireRate()) + " " + I18n.format("gun.firerate.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammotype") + ": " + TextFormatting.BLUE + descAmmoType());
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.maxammo") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + getWeaponAmmoLimit(stack));
		}
		
		else if(GuiScreen.isCtrlKeyDown())
		{
			if(stack.hasTagCompound())
			{
				tooltip.add(TextFormatting.BOLD + "Attachments:");
				tooltip.add(TextFormatting.BOLD + "Scope:" + TextFormatting.RESET + " " + TextFormatting.GREEN + scope);
				tooltip.add(TextFormatting.BOLD + "Barrel:" + TextFormatting.RESET + "" + TextFormatting.GREEN + " " + barrel);
				tooltip.add(TextFormatting.BOLD + "Grip:" + TextFormatting.RESET + " " + TextFormatting.GREEN + grip);
				tooltip.add(TextFormatting.BOLD + "Magazine:" + TextFormatting.RESET + " " + TextFormatting.GREEN + magazine);
				tooltip.add(TextFormatting.BOLD + "Stock:" + TextFormatting.RESET + " " + TextFormatting.GREEN + stock);
			}
		}
		
		else 
		{
			tooltip.add(TextFormatting.YELLOW + I18n.format("gun.desc.moreinfo"));
			tooltip.add(TextFormatting.YELLOW + I18n.format("gun.desc.moreinfo2"));
		}
	}
	
	/**
	 * Method for correct rendering in loot spawner TESR
	 * This will get removed because all guns will have 3D models
	 * 
	 * @param item
	 */
	@Deprecated
	public boolean is3DModel(Item item)
	{
		return item == PMCItems.P92 || item == PMCItems.P1911 || item == PMCItems.P18C || item == PMCItems.R1895 || item == PMCItems.R45 || item == PMCItems.SAWED_OFF;
	}
	
//-------------------------------------------------
// Setters and getters
//-------------------------------------------------
	
	
	public boolean setBurstFire(boolean canBurstFire)
	{
		return burst = canBurstFire;
	}
	
	public boolean canGunBurstFire()
	{
		return burst;
	}
	
	public boolean setAutoFiremode(boolean canAutoFire)
	{
		return auto = canAutoFire;
	}
	
	public boolean canGunAutofire()
	{
		return auto;
	}
	
	public float setDamage(float damageModifier)
	{
		return damage = damageModifier;
	}
	
	public double setVelocity(double modifier)
	{
		return velocity = modifier;
	}
	
	public double setGravityModifier(double modifier)
	{
		return gravity = modifier;
	}
	
	public double setGravityStartTime(int ticks)
	{
		return gravityStart = ticks;
	}
	
	public double setReloadTime(double time)
	{
		return reloadTime = time;
	}
	
	public float setHorizontalRecoil(float recoil)
	{
		return horizontal_recoil = recoil;
	}
	
	public float setVerticalRecoil(float recoil)
	{
		return vertical_recoil = recoil;
	}
	
	public AmmoType setAmmoType(AmmoType type)
	{
		return ammotype = type;
	}
	
	public Firemode setFiremode(Firemode type) 
	{
		return firemode = type;
	}
	
	public ReloadType setReloadType(ReloadType type)
	{
		return reloadType = type;
	}
	
	public int setReloadDelay(int delay)
	{
		return reloadDelay = delay;
	}
	
	public int setFireRate(int firerate)
	{
		return rate = firerate;
	}
	
	//For testing attachment types for different guns
	public GunType setGunType(GunType type)
	{
		return gunType = type;
	}
	
	public boolean canSwitchMode(boolean firemode)
	{
		return canSwitchFiremode = firemode;
	}
	
	public SoundEvent setGunSound(SoundEvent shootSound)
	{
		return this.gun_shoot = shootSound;
	}
	
	public SoundEvent setGunSilencedSound(SoundEvent silenced)
	{
		return this.gun_silenced = silenced;
	}
	
	public float setGunSoundVolume(float volume)
	{
		return this.gun_volume = volume;
	}
	
	//set this after the first sound volume
	public float setGunSilencedSoundVolume(float volume)
	{
		return this.gun_volume_s = volume;
	}
	
	public GunBase getGun()
	{
		return this;
	}
	
	public float getDamage()
	{
		return damage;
	}
	
	public double getVelocity()
	{
		return velocity;
	}
	
	public double getGravityModifier()
	{
		return gravity;
	}
	
	public int getGravityStartTime()
	{
		return gravityStart;
	}
	
	public double getReloadTime(ItemStack stack)
	{
		if(stack.hasTagCompound() && (stack.getTagCompound().getInteger("magazine") == 1 || stack.getTagCompound().getInteger("magazine") == 3) || stack.getTagCompound().getInteger("stock") == 1)
		{
			return reloadTime * 0.7;
		}
		
		else return reloadTime;
	}
	
	public AmmoType getAmmoType()
	{
		return ammotype;
	}
	
	public SoundEvent getGunSound()
	{
		return gun_shoot;
	}
	
	public SoundEvent getGunSilencedSound()
	{
		return gun_silenced;
	}
	
	public float getGunVolume()
	{
		return gun_volume;
	}
	
	public float getGunSilencedVolume()
	{
		return gun_volume_s;
	}
	
	public Firemode getFiremode()
	{
		return firemode;
	}
	
	public boolean getCanSwitchFiremode()
	{
		return canSwitchFiremode;
	}
	
	public ReloadType getReloadType()
	{
		return reloadType;
	}
	
	public int getReloadDelay()
	{
		return reloadDelay;
	}
	
	public int getFireRate()
	{
		return rate;
	}
	
	public GunType getGunType()
	{
		return gunType;
	}
	
	public float getHorizontalRecoil(ItemStack stack)
	{
		return horizontal_recoil;
	}
	
	public float getVerticalRecoil(ItemStack stack)
	{
		return vertical_recoil;
	}
	
	public int getAmmo(ItemStack stack)
	{
		int a = 0;
		if(stack.hasTagCompound())
		{
			a = stack.getTagCompound().getInteger("ammo");
		}
		
		return a;
	}
	
	public enum Firemode
	{
		SINGLE, BURST, AUTO;
	}
	
	public enum ReloadType
	{
		MAGAZINE, SINGLE, KAR98K;
	}
	
	public enum GunType
	{
		LMG, PISTOL, SHOTGUN, SMG, AR, DMR, SR;
	}
	
	public void setHasTwoRoundBurst(boolean hasTwoRoundBurst)
	{
		this.hasTwoRoundBurst = hasTwoRoundBurst;
	}
	
	public boolean isHasTwoRoundBurst()
	{
		return hasTwoRoundBurst;
	}
	
	protected void addMagazines()
	{
		if(gunType == GunType.PISTOL)
		{
			attachments.add(PMCItems.QUICKDRAW_MAG_PISTOL);
			attachments.add(PMCItems.EXTENDED_MAG_PISTOL);
			attachments.add(PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL);
		}
		
		else if(gunType == GunType.SMG)
		{
			attachments.add(PMCItems.QUICKDRAW_MAG_SMG);
			attachments.add(PMCItems.EXTENDED_MAG_SMG);
			attachments.add(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG);
		}
		
		else if(gunType == gunType.AR)
		{
			attachments.add(PMCItems.QUICKDRAW_MAG_AR);
			attachments.add(PMCItems.EXTENDED_MAG_AR);
			attachments.add(PMCItems.EXTENDED_QUICKDRAW_MAG_AR);
		}
		
		else if(gunType == GunType.DMR || gunType == GunType.SR)
		{
			attachments.add(PMCItems.QUICKDRAW_MAG_SNIPER);
			attachments.add(PMCItems.EXTENDED_MAG_SNIPER);
			attachments.add(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER);
		}
	}
	
	protected void addGrips()
	{
		attachments.add(PMCItems.GRIP_ANGLED);
		attachments.add(PMCItems.GRIP_VERTICAL);
	}
	
	protected void addCloseRangeScopes()
	{
		attachments.add(PMCItems.RED_DOT);
		attachments.add(PMCItems.HOLOGRAPHIC);
		attachments.add(PMCItems.SCOPE2X);
		attachments.add(PMCItems.SCOPE4X);
	}
	
	protected void addScopes()
	{
		addCloseRangeScopes();
		attachments.add(PMCItems.SCOPE8X);
		attachments.add(PMCItems.SCOPE15X);
	}
	
	/**
	 * <li> silencer
	 * <li> redDot
	 * <li> magazines
	 */
	protected void addPistolAttachments()
	{
		attachments.add(PMCItems.SILENCER_PISTOL);
		attachments.add(PMCItems.RED_DOT);
		addMagazines();
	}
	
	/**
	 * <li> Bullet Loops
	 */
	protected void addShotgunAttachments()
	{
		attachments.add(PMCItems.BULLET_LOOPS_SHOTGUN);
	}
	
	/**
	 * <li> silencer, compensator
	 * <li> grips
	 * <li> magazines
	 * <li> Red Dot, Holo, 2X, 4X
	 */
	protected void addSMGAttachments()
	{
		attachments.add(PMCItems.SILENCER_SMG);
		attachments.add(PMCItems.COMPENSATOR_SMG);
		addGrips();
		addMagazines();
		addCloseRangeScopes();
	}
	
	/**
	 * <li> silencer, compensator
	 * <li> grips - only grips == true
	 * <li> magazines
	 * <li> Red Dot, Holo, 2X, 4X
	 */
	protected void addARAttachments(boolean grips)
	{
		addCloseRangeScopes();
		addMagazines();
		if(grips) addGrips();
		attachments.add(PMCItems.COMPENSATOR_AR);
		attachments.add(PMCItems.SILENCER_AR);
	}
	
	/**
	 * <li> silencer, compensator
	 * <li> magazines
	 * <li> Red Dot, Holo, 2X, 4X, 8X, 15X
	 */
	protected void addSniperAttachments()
	{
		addScopes();
		addMagazines();
		attachments.add(PMCItems.SILENCER_SNIPER);
		attachments.add(PMCItems.COMPENSATOR_SNIPER);
	}
}
