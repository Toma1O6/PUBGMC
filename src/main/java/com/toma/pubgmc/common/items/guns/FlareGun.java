package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityFlare;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class FlareGun extends GunBase
{
	public FlareGun(String name) 
	{
		super(name);
		this.setDamage(0);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setAmmoType(AmmoType.FLARE);
		this.setGunType(GunType.PISTOL);
		this.setReloadDelay(30);
		this.setReloadTime(70);
		this.setFireRate(110);
		this.canSwitchMode(false);
		this.setVerticalRecoil(1);
		this.setHorizontalRecoil(0);
		
		this.setGunSound(PMCSounds.gun_flare);
		this.setGunSoundVolume(25f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 1;
	}
	
	@Override
	public void shoot(World world, EntityPlayer player, ItemStack stack)
	{
		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		CooldownTracker tr = player.getCooldownTracker();
        if(this.hasAmmo(stack) || player.capabilities.isCreativeMode && !data.isReloading() && !tr.hasCooldown(this))
        {
        	world.playSound(null, player.posX, player.posY, player.posZ, this.getGunSound(), SoundCategory.PLAYERS, this.getGunVolume(), 1.0f);
        	
        	if(!world.isRemote)
        	{
                EntityFlare bullet = new EntityFlare(world, player);
                world.spawnEntity(bullet);
                if(!player.capabilities.isCreativeMode)
                {
                	stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
        	}
        	
        	tr.setCooldown(this, getFireRate());
        }
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_flaregun;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Items.IRON_INGOT, 15));
		recipe.add(new ItemStack(PMCItems.STEEL_INGOT, 20));
		recipe.add(new ItemStack(Items.DYE, 10, 1));
		return recipe;
	}
}
