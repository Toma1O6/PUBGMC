package com.toma.pubgmc.common.network.sp;

import org.apache.logging.log4j.Level;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncConfig implements IMessage, IMessageHandler<PacketSyncConfig, IMessage>
{
	//add only values which are important & server side only
	//player
	boolean enableTP, enableInventoryLimit, forceBrightness, nameTags;
	int brightnessLevel;
	
	//world
	boolean enableGunLoot, enableGuns;
	int airdropLootGen, airdropRange, planeHeight, planeWaitTime;
	
	//guns
	float p92, p1911, p18c, r1895, r45, scorpion, win94, sawedoff, s1897, s686, s12k, microuzi, ump9, vector, tommygun, bizon,
	m16a4, m416, scarl, g36c, qbz, aug, akm, m762, mk47, groza, dp28, m249, vss, mini14, qbu, sks, slr, mk14, kar98k, m24, awm;
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		enableTP = buf.readBoolean();
		enableInventoryLimit = buf.readBoolean();
		enableGunLoot = buf.readBoolean();
		enableGuns = buf.readBoolean();
		airdropLootGen = buf.readInt();
		airdropRange = buf.readInt();
		p92 = buf.readFloat();
		p1911 = buf.readFloat();
		p18c = buf.readFloat();
		r1895 = buf.readFloat();
		r45 = buf.readFloat();
		scorpion = buf.readFloat();
		win94 = buf.readFloat();
		sawedoff = buf.readFloat();
		s1897 = buf.readFloat();
		s686 = buf.readFloat();
		s12k = buf.readFloat();
		microuzi = buf.readFloat();
		ump9 = buf.readFloat();
		vector = buf.readFloat();
		tommygun = buf.readFloat();
		bizon = buf.readFloat();
		m16a4 = buf.readFloat();
		m416 = buf.readFloat();
		scarl = buf.readFloat();
		g36c = buf.readFloat();
		qbz = buf.readFloat();
		aug = buf.readFloat();
		akm = buf.readFloat();
		m762 = buf.readFloat();
		mk47 = buf.readFloat();
		groza = buf.readFloat();
		dp28 = buf.readFloat();
		m249 = buf.readFloat();
		vss = buf.readFloat();
		mini14 = buf.readFloat();
		qbu = buf.readFloat();
		sks = buf.readFloat();
		slr = buf.readFloat();
		mk14 = buf.readFloat();
		kar98k = buf.readFloat();
		m24 = buf.readFloat();
		awm = buf.readFloat();
		forceBrightness = buf.readBoolean();
		brightnessLevel = buf.readInt();
		nameTags = buf.readBoolean();
		planeHeight = buf.readInt();
		planeWaitTime = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(ConfigHandler.enableTP);
		buf.writeBoolean(ConfigHandler.enableInventoryLimit);
		buf.writeBoolean(ConfigHandler.enableGunLoot);
		buf.writeBoolean(ConfigHandler.enableGuns);
		buf.writeInt(ConfigHandler.airdropLootGen);
		buf.writeInt(ConfigHandler.aidropRange);
		buf.writeFloat(ConfigHandler.p92);
		buf.writeFloat(ConfigHandler.p1911);
		buf.writeFloat(ConfigHandler.p18c);
		buf.writeFloat(ConfigHandler.r1895);
		buf.writeFloat(ConfigHandler.r45);
		buf.writeFloat(ConfigHandler.scorpion);
		buf.writeFloat(ConfigHandler.win94);
		buf.writeFloat(ConfigHandler.sawedoff);
		buf.writeFloat(ConfigHandler.s1897);
		buf.writeFloat(ConfigHandler.s686);
		buf.writeFloat(ConfigHandler.s12k);
		buf.writeFloat(ConfigHandler.microuzi);
		buf.writeFloat(ConfigHandler.ump9);
		buf.writeFloat(ConfigHandler.vector);
		buf.writeFloat(ConfigHandler.tommygun);
		buf.writeFloat(ConfigHandler.bizon);
		buf.writeFloat(ConfigHandler.m16a4);
		buf.writeFloat(ConfigHandler.m416);
		buf.writeFloat(ConfigHandler.scarl);
		buf.writeFloat(ConfigHandler.g36c);
		buf.writeFloat(ConfigHandler.qbz);
		buf.writeFloat(ConfigHandler.aug);
		buf.writeFloat(ConfigHandler.akm);
		buf.writeFloat(ConfigHandler.m762);
		buf.writeFloat(ConfigHandler.mk47);
		buf.writeFloat(ConfigHandler.groza);
		buf.writeFloat(ConfigHandler.dp28);
		buf.writeFloat(ConfigHandler.m249);
		buf.writeFloat(ConfigHandler.vss);
		buf.writeFloat(ConfigHandler.mini14);
		buf.writeFloat(ConfigHandler.qbu);
		buf.writeFloat(ConfigHandler.sks);
		buf.writeFloat(ConfigHandler.slr);
		buf.writeFloat(ConfigHandler.mk14);
		buf.writeFloat(ConfigHandler.kar98k);
		buf.writeFloat(ConfigHandler.m24);
		buf.writeFloat(ConfigHandler.awm);
		buf.writeBoolean(ConfigHandler.forceBrightness);
		buf.writeInt(ConfigHandler.brightness);
		buf.writeBoolean(ConfigHandler.renderPlayerNameTags);
		buf.writeInt(ConfigHandler.planeHeight);
		buf.writeInt(ConfigHandler.planeWaitTime);
	}
	
	@Override
	public IMessage onMessage(PacketSyncConfig p, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			Pubgmc.logger.log(Level.INFO, "Attempting config sync...");
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				ConfigHandler.enableTP = p.enableTP;
				ConfigHandler.enableInventoryLimit = p.enableInventoryLimit;
				ConfigHandler.enableGunLoot = p.enableGunLoot;
				ConfigHandler.enableGuns = p.enableGuns;
				ConfigHandler.airdropLootGen = p.airdropLootGen;
				ConfigHandler.aidropRange = p.airdropRange;
				ConfigHandler.p92 = p.p92;
				ConfigHandler.p1911 = p.p1911;
				ConfigHandler.p18c = p.p18c;
				ConfigHandler.r1895 = p.r1895;
				ConfigHandler.r45 = p.r45;
				ConfigHandler.scorpion = p.scorpion;
				ConfigHandler.win94 = p.win94;
				ConfigHandler.sawedoff = p.sawedoff;
				ConfigHandler.s1897 = p.s1897;
				ConfigHandler.s686 = p.s686;
				ConfigHandler.s12k = p.s12k;
				ConfigHandler.microuzi = p.microuzi;
				ConfigHandler.ump9 = p.ump9;
				ConfigHandler.vector = p.vector;
				ConfigHandler.tommygun = p.tommygun;
				ConfigHandler.bizon = p.bizon;
				ConfigHandler.m16a4 = p.m16a4;
				ConfigHandler.m416 = p.m416;
				ConfigHandler.scarl = p.scarl;
				ConfigHandler.g36c = p.g36c;
				ConfigHandler.qbz = p.qbz;
				ConfigHandler.aug = p.aug;
				ConfigHandler.akm = p.akm;
				ConfigHandler.m762 = p.m762;
				ConfigHandler.mk47 = p.mk47;
				ConfigHandler.groza = p.groza;
				ConfigHandler.dp28 = p.dp28;
				ConfigHandler.m249 = p.m249;
				ConfigHandler.vss = p.vss;
				ConfigHandler.mini14 = p.mini14;
				ConfigHandler.qbu = p.qbu;
				ConfigHandler.sks = p.sks;
				ConfigHandler.slr = p.slr;
				ConfigHandler.mk14 = p.mk14;
				ConfigHandler.kar98k = p.kar98k;
				ConfigHandler.m24 = p.m24;
				ConfigHandler.awm = p.awm;
				ConfigHandler.forceBrightness = p.forceBrightness;
				ConfigHandler.brightness = p.brightnessLevel;
				ConfigHandler.renderPlayerNameTags = p.nameTags;
				ConfigHandler.planeHeight = p.planeHeight;
				ConfigHandler.planeWaitTime = p.planeWaitTime;
				
				Pubgmc.logger.log(Level.INFO, "Your config has been synced with server.");
			});
		}
		
		return null;
	}
}
