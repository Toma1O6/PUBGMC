package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientCapabilitySync implements IMessage, IMessageHandler<PacketClientCapabilitySync, IMessage>
{
	private IPlayerData data;
	private boolean reload,aim,useNV,equipNV,grenadeCook;
	private float boost;
	private int reloadTime,timer,backpackLevel,cookTime,scopeType,scopeColor;
	
	public PacketClientCapabilitySync()
	{
		
	}
	
	public PacketClientCapabilitySync(IPlayerData data)
	{
		this.data = data;
		reload = data.isReloading();
		aim = data.isAiming();
		useNV = data.isUsingNV();
		equipNV = data.getEquippedNV();
		grenadeCook = data.isGrenadeCooking();
		boost = data.getBoost();
		reloadTime = data.getReloadingTime();
		timer = data.getTimer();
		backpackLevel = data.getBackpackLevel();
		cookTime = data.getCookingTime();
		scopeType = data.getScopeType();
		scopeColor = data.getScopeColor();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(reload);
		buf.writeBoolean(aim);
		buf.writeBoolean(useNV);
		buf.writeBoolean(equipNV);
		buf.writeBoolean(grenadeCook);
		buf.writeFloat(boost);
		buf.writeInt(reloadTime);
		buf.writeInt(timer);
		buf.writeInt(backpackLevel);
		buf.writeInt(cookTime);
		buf.writeInt(scopeType);
		buf.writeInt(scopeColor);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		reload = buf.readBoolean();
		aim = buf.readBoolean();
		useNV = buf.readBoolean();
		equipNV = buf.readBoolean();
		grenadeCook = buf.readBoolean();
		boost = buf.readFloat();
		reloadTime = buf.readInt();
		timer = buf.readInt();
		backpackLevel = buf.readInt();
		cookTime = buf.readInt();
		scopeType = buf.readInt();
		scopeColor = buf.readInt();
	}
	
	@Override
	public IMessage onMessage(PacketClientCapabilitySync m, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				EntityPlayerSP player = Minecraft.getMinecraft().player;
				if(player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
				{
					IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
					data.setReloading(m.reload);
					data.setAiming(m.aim);
					data.setNV(m.useNV);
					data.hasEquippedNV(m.equipNV);
					data.setGrenadeCooking(m.grenadeCook);
					data.setBoost(m.boost);
					data.setReloadingTime(m.reloadTime);
					data.setTimer(m.timer);
					data.setBackpackLevel(m.backpackLevel);
					data.setCookingTime(m.cookTime);
					data.setScopeType(m.scopeType);
					data.setScopeColor(m.scopeColor);
					Pubgmc.logger.info("Synchronized player capability");
				}
				
				else Pubgmc.logger.error("Couldn't synchronize client data with " + player.getName() + "! [No capability found]");
			});
		}
		return null;
	}
}
