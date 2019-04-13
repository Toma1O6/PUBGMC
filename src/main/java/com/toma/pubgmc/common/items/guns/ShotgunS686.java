package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShotgunS686 extends GunBase
{

	public ShotgunS686(String name) 
	{
		super(name);
		this.setDamage(cfg.s686);
		this.setVelocity(5.5);
		this.setGravityModifier(0.175);
		this.setGravityStartTime(-1);
		this.setVerticalRecoil(3.5f);
		this.setHorizontalRecoil(2.0f);
		this.setGunType(GunType.SHOTGUN);
		this.setFireRate(5);
		this.setReloadTime(48);
		this.setReloadDelay(10);
		this.setAmmoType(AmmoType.AMMO12G);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_s686);
		this.setGunSoundVolume(10f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 2;
	}
	
	@Override
	public void shoot(World world, EntityPlayer player, ItemStack stack)
	{
		BlockPos pos = player.getPosition();
		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		CooldownTracker tracker = player.getCooldownTracker();
		
        if(this.hasAmmo(stack) || player.capabilities.isCreativeMode && !data.isReloading() && !tracker.hasCooldown(this))
        {
        	if(!world.isRemote)
        	{
        		world.playSound(null, player.posX, player.posY, player.posZ, getGunSound(), SoundCategory.PLAYERS, getGunVolume(), 1.0f);
        		
        		for(int i = 0; i < 8; i++)
        		{
        			EntityBullet bullet = new EntityBullet(world, player, this);
        			world.spawnEntity(bullet);
        		}
                
                if(!player.capabilities.isCreativeMode)
                {
                	stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
        	}
        	
        	tracker.setCooldown(this, getFireRate());
        }
        	
        if(stack.getTagCompound().getInteger("ammo") == 0 && !data.isReloading() && !player.capabilities.isCreativeMode)
        {	
        	world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), PMCSounds.gun_noammo, SoundCategory.PLAYERS, 0.5f, 1.0f);
        }
	}

	@Override
	public List<Item> acceptedAttachments() 
	{
		addShotgunAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_s686;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).s686;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 20));
		rec.add(new ItemStack(Items.IRON_INGOT, 20));
		rec.add(new ItemStack(Blocks.PLANKS, 10));
		return rec;
	}
}
