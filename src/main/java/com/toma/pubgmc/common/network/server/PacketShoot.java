package com.toma.pubgmc.common.network.server;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketShoot implements IMessage, IMessageHandler<PacketShoot, IMessage>
{
	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}
	
	@Override
	public IMessage onMessage(PacketShoot message, MessageContext ctx)
	{
		World world = ctx.getServerHandler().player.world;
		EntityPlayer player = ctx.getServerHandler().player;
		
		player.getServer().addScheduledTask(() ->
		{
			ItemStack stack = player.getHeldItemMainhand();
			
			if(stack.getItem() instanceof GunBase)
			{
				GunBase gun = (GunBase)stack.getItem();
				CooldownTracker tracker = player.getCooldownTracker();
				/*if(!stack.getTagCompound().getBoolean("isValidWeapon"))
				{
					stack.getTagCompound().setBoolean("isValidWeapon", true);
					//TODO - it should be 0 and possibly handle this on client side too
					stack.getTagCompound().setInteger("ammo", gun.getMaxAmmo(stack));
				}*/
				
				if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("isValidWeapon"))
				{
					if(gun.hasAmmo(stack))
					{	
						if(!tracker.hasCooldown(gun))
						{	
							gun.shoot(world, player, stack);
							PacketHandler.INSTANCE.sendToAllAround(new PacketSound(gun.playWeaponSound(stack), gun.playWeaponSoundVolume(stack), 1f, player.posX, player.posY, player.posZ), new TargetPoint(0, player.posX, player.posY, player.posZ, 150));
							tracker.setCooldown(gun, gun.getFireRate());
						}
					}
				}
			}
		});
		
		return null;
	}
}
