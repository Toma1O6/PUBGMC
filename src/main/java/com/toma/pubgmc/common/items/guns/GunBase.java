package com.toma.pubgmc.common.items.guns;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.ConfigPMC.WeaponCFG;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketFiremode;
import com.toma.pubgmc.common.network.sp.PacketCreateNBT;
import com.toma.pubgmc.common.network.sp.PacketReloadingSP;
import com.toma.pubgmc.common.network.sp.PacketSound;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.util.ICraftable;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Toma1O6
 * This is the core class for all guns
 */
public class GunBase extends PMCItem implements ICraftable
{
	public static final List<GunBase> GUNS = new ArrayList<GunBase>();
	
	private WeaponCFG wepStats;
	private float horizontal_recoil = 0f;
	private float vertical_recoil = 0f;
	private double reloadTime = 100;
	private int rate = 10;
	private int maxAmmo, exMaxAmmo;
	private AmmoType ammotype;
	private Firemode firemode;
	private Firemode[] validFiremodes = new Firemode[] {Firemode.SINGLE};
	private ReloadType reloadType;
	private GunType gunType;
	private boolean hasTwoRoundBurst = false;
	
	@SideOnly(Side.CLIENT)
	private ModelGun gunModel;
	
	//Attachments
	private ItemAttachment[]
			barrel = new ItemAttachment[0],
			grip = new ItemAttachment[0],
			magazine = new ItemAttachment[0],
			stock = new ItemAttachment[0],
			scope = new ItemAttachment[0];
	
	//SOUNDS
	private SoundEvent gun_shoot, gun_silenced, gun_reload;
	private float gun_volume, gun_volume_s;
	
	public List<ItemStack> craftingRecipe = new ArrayList<ItemStack>();
	
	private ItemAmmo ammoItem;
	private int ammoCount = 0;
	
	protected GunBase(String name)
	{
		super(name);
		setCreativeTab(Pubgmc.pmcitemstab);
		setMaxStackSize(1);
		GUNS.add(this);
	}
	
	public SoundEvent getWeaponReloadSound()
	{
		return this.gun_reload;
	}
	
	/**
	 * Gets maximum possible amount of bullet gun can have loaded
	 * @param stack - the gun itemstack
	 * @return the weapon ammo limit
	 */
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? exMaxAmmo : maxAmmo;
	}
	
	@Override
	public void initCraftingRecipe()
	{
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe() 
	{
		return craftingRecipe;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.GUN;
	}
	
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
        	CooldownTracker tracker = player.getCooldownTracker();
        	if(!tracker.hasCooldown(stack.getItem()))
        	{
            	if(!world.isRemote)
            	{
                    if(!gunType.equals(GunType.SHOTGUN))
                    {
                    	EntityBullet bullet = new EntityBullet(world, player, this);
                    	world.spawnEntity(bullet);
                    }
                    else
                    {
                    	for(int i = 0; i < 8; i++)
                    	{
                    		EntityBullet bullet = new EntityBullet(world, player, this);
                    		world.spawnEntity(bullet);
                    	}
                    }
                    
                    if(!player.capabilities.isCreativeMode)
                    {
                        stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                    }
                    
                    PacketHandler.sendToClientsAround(new PacketSound(playWeaponSound(stack), playWeaponSoundVolume(stack), 1f, player.posX, player.posY, player.posZ), new TargetPoint(0, player.posX, player.posY, player.posZ, 150));
            	}
            	
            	tracker.setCooldown(stack.getItem(), getFireRate());
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
					setFiremode(Firemode.BURST);
					break;
				}
				else
				{
					setFiremode(Firemode.AUTO);
					break;
				}
			}
			
			case BURST:
			{
				if(canGunAutofire())
				{
					setFiremode(Firemode.AUTO);
					break;
				}
				else
				{
					setFiremode(Firemode.SINGLE);
					break;
				}
			}
			
			case AUTO: {
				setFiremode(Firemode.SINGLE);
				break;
			}
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
		tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firemode") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + getFiremode().translatedName());
		
		if(GuiScreen.isShiftKeyDown() && stack.hasTagCompound())
		{
			DecimalFormat f = new DecimalFormat("###.##");
			DecimalFormat g = new DecimalFormat("###.###");
			
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.damage") + ": " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + this.wepStats.damage);
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.reloadtime") + ": " + TextFormatting.RESET + "" + TextFormatting.GREEN + g.format(getReloadTime(stack) / 20) + " " + I18n.format("gun.reloadtime.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.velocity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.velocity * 5.5) + " " + I18n.format("gun.velocity.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.gravity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.gravityModifier * 20) + " " + I18n.format("gun.gravity.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firerate") + ": " + TextFormatting.RESET + "" + TextFormatting.AQUA + g.format(20.00 / this.getFireRate()) + " " + I18n.format("gun.firerate.info"));
			tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammotype") + ": " + TextFormatting.BLUE + ammotype.translatedName());
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
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		return true;
	}
	
//-------------------------------------------------
// Setters and getters
//-------------------------------------------------
	
	@SideOnly(Side.CLIENT)
	public void setGunModel(ModelGun model)
	{
		this.gunModel = model;
	}
	
	@SideOnly(Side.CLIENT)
	public ModelGun getWeaponModel()
	{
		return gunModel;
	}
	
	public void setCraftingRecipe(List<ItemStack> recipe)
	{
		this.craftingRecipe = recipe;
	}
	
	public void setMaxAmmo(int maxAmmo, int exMaxAmmo)
	{
		this.maxAmmo = maxAmmo;
		this.exMaxAmmo = exMaxAmmo;
	}
	
	public void setReloadSound(SoundEvent reload)
	{
		this.gun_reload = reload;
	}
	
	public void setBarrelAttachments(ItemAttachment... attachments)
	{
		this.barrel = attachments;
	}
	
	public void setGripAttachments(ItemAttachment... attachments)
	{
		this.grip = attachments;
	}
	
	public void setMagazineAttachments(ItemAttachment... attachments)
	{
		this.magazine = attachments;
	}
	
	public void setStockAttachments(ItemAttachment... attachments)
	{
		this.stock = attachments;
	}
	
	public void setScopeAttachments(ItemAttachment... attachments)
	{
		this.scope = attachments;
	}
	
	public ItemAttachment[] getBarrelAttachments()
	{
		return barrel;
	}
	
	public ItemAttachment[] getGripAttachments()
	{
		return grip;
	}
	
	public ItemAttachment[] getMagazineAttachments()
	{
		return magazine;
	}
	
	public ItemAttachment[] getStockAttachments()
	{
		return stock;
	}
	
	public ItemAttachment[] getScopeAttachments()
	{
		return scope;
	}
	
	public void setValidFiremodes(Firemode... firemodes)
	{
		this.validFiremodes = firemodes;
	}
	
	public Firemode[] getValidFiremodes()
	{
		return validFiremodes;
	}
	
	public boolean canGunBurstFire()
	{
		for(Firemode f : validFiremodes)
		{
			if(f.equals(Firemode.BURST))
				return true;
		}
		return false;
	}
	
	public boolean canGunAutofire()
	{
		for(Firemode f : validFiremodes)
			if(f.equals(Firemode.AUTO))
				return true;
		
		return false;
	}
	
	public void setStats(WeaponCFG cfgStats) {
		this.wepStats = cfgStats;
	}
	
	public void setReloadTime(double time)
	{
		this.reloadTime = time;
	}
	
	public void setHorizontalRecoil(float recoil)
	{
		this.horizontal_recoil = recoil;
	}
	
	public void setVerticalRecoil(float recoil)
	{
		this.vertical_recoil = recoil;
	}
	
	public void setAmmoType(AmmoType type)
	{
		this.ammotype = type;
	}
	
	public void setFiremode(Firemode type) 
	{
		this.firemode = type;
	}
	
	public void setReloadType(ReloadType type)
	{
		this.reloadType = type;
	}
	
	public void setFireRate(int firerate)
	{
		this.rate = firerate;
	}
	
	//For testing attachment types for different guns
	public void setGunType(GunType type)
	{
		this.gunType = type;
	}
	
	//TODO: remove
	public void canSwitchMode(boolean firemode)
	{
	}
	
	public void setGunSound(SoundEvent shootSound)
	{
		this.gun_shoot = shootSound;
	}
	
	public void setGunSilencedSound(SoundEvent silenced)
	{
		this.gun_silenced = silenced;
	}
	
	public void setGunSoundVolume(float volume)
	{
		this.gun_volume = volume;
	}
	
	//set this after the first sound volume
	public void setGunSilencedSoundVolume(float volume)
	{
		this.gun_volume_s = volume;
	}
	
	public GunBase getGun()
	{
		return this;
	}
	
	public WeaponCFG getConfigurableStats() {
		return wepStats;
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
		return validFiremodes.length > 1;
	}
	
	public ReloadType getReloadType()
	{
		return reloadType;
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
	
	public boolean containsAttachment(ItemAttachment[] group, ItemAttachment toCheck)
	{
		for(ItemAttachment a : group)
		{
			if(a == toCheck)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public enum Firemode
	{
		SINGLE("gun.firemode.single"),
		BURST("gun.firemode.burst"),
		AUTO("gun.firemode.auto");
		
		private String name;
		
		private Firemode(String name)
		{
			this.name = name;
		}
		
		@SideOnly(Side.CLIENT)
		public String translatedName()
		{
			return I18n.format(name);
		}
		
		public static Firemode[] all()
		{
			return new Firemode[] {Firemode.SINGLE, Firemode.BURST, Firemode.AUTO};
		}
		
		public static Firemode[] noBurst()
		{
			return new Firemode[] {Firemode.SINGLE, Firemode.AUTO};
		}
		
		public static Firemode[] noAuto()
		{
			return new Firemode[] {Firemode.SINGLE, Firemode.BURST};
		}
	}
	
	public enum ReloadType
	{
		MAGAZINE,
		SINGLE,
		KAR98K;
		
		public void handleReload(EntityPlayer player)
		{
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			if(ConfigPMC.common.worldSettings.enableGuns)
			{
				ItemStack heldItem = player.getHeldItemMainhand();
				
				if(heldItem.getItem() instanceof GunBase)
				{
					data.setAiming(false);
					GunBase gun = (GunBase)heldItem.getItem();
					
					if((heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem) || !gun.hasPlayerAmmoForGun(player, gun)) && data.isReloading())
					{
						data.setReloading(false);
						PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
					}
					
					if(heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
					{
						if(gun.getReloadType() == ReloadType.MAGAZINE)
						{
							while((gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
							{
								int ammoInGun = heldItem.getTagCompound().getInteger("ammo");
								int ammoToFill = gun.getWeaponAmmoLimit(heldItem) - ammoInGun;
								int ammoInInventory = gun.ammoCount;
								
								if(ammoToFill > ammoInInventory) ammoToFill = ammoInInventory;
								
								if(!player.capabilities.isCreativeMode)
								{
									ItemAmmo ammo = (ItemAmmo)gun.ammoItem.getAmmoItem();
									player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, ammoToFill, null);
								}
								
								heldItem.getTagCompound().setInteger("ammo", ammoInGun + ammoToFill);
							}
							
							data.setReloading(false);
							PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
						}
						
						else if(gun.getReloadType() == ReloadType.SINGLE)
						{
							if(gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode)
							{
								//If the gun is already full and player still atempts to reload, cancel it
								if(heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem))
								{
									data.setReloading(false);
									PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP)player);
								}
								
								if(heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
								{
									ItemAmmo ammo = (ItemAmmo)gun.ammoItem.getAmmoItem();
									
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
								while((gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem))
								{
									ItemAmmo ammo = (ItemAmmo)gun.ammoItem.getAmmoItem();
									
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
								ItemAmmo ammo = (ItemAmmo)gun.ammoItem.getAmmoItem();
								
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
}
