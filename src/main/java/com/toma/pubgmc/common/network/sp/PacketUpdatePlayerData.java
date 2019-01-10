package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatePlayerData implements IMessage, IMessageHandler<PacketUpdatePlayerData, IMessage>
{
	private boolean isReloading;
	private int reloadingTime;
	private float boost;
	private int level;
	private boolean hasNV;
	private int grenadeCookTime;
	private boolean isGrenadeCooking;
	
	public PacketUpdatePlayerData() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{	
		isReloading = buf.readBoolean();
		reloadingTime = buf.readInt();
		boost = buf.readFloat();
		level = buf.readInt();
		hasNV = buf.readBoolean();
		grenadeCookTime = buf.readInt();
		isGrenadeCooking = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{	
		buf.writeBoolean(isReloading);
		buf.writeInt(reloadingTime);
		buf.writeFloat(boost);
		buf.writeInt(level);
		buf.writeBoolean(hasNV);
		buf.writeInt(grenadeCookTime);
		buf.writeBoolean(isGrenadeCooking);
	}
	
	@Override
	public IMessage onMessage(PacketUpdatePlayerData message, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			EntityPlayerSP sp = Minecraft.getMinecraft().player;
			if(sp != null)
			{
				IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);
				data.setReloading(message.isReloading);
				data.setReloadingTime(message.reloadingTime);
				data.setBoost(message.boost);
				data.setBackpackLevel(message.level);
				data.hasEquippedNV(message.hasNV);
				data.setCookingTime(message.grenadeCookTime);
				data.setGrenadeCooking(message.isGrenadeCooking);
			}
		}
		return null;
	}
	
	public float getBoost() {
		return boost;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getReloadingTime() {
		return reloadingTime;
	}
	
	public boolean isHasNV() {
		return hasNV;
	}
	
	public boolean isReloading() {
		return isReloading;
	}
	
	public void setBoost(float boost) {
		this.boost = boost;
	}
	
	public void setHasNV(boolean hasNV) {
		this.hasNV = hasNV;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setReloading(boolean isReloading) {
		this.isReloading = isReloading;
	}
	
	public void setReloadingTime(int reloadingTime) {
		this.reloadingTime = reloadingTime;
	}

	public void setGrenadeCookTime(int grenadeCookTime) {
		this.grenadeCookTime = grenadeCookTime;
	}

	public void setGrenadeCooking(boolean isGrenadeCooking) {
		this.isGrenadeCooking = isGrenadeCooking;
	}
}
