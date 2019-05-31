package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.ConfigPMC.WeaponCFG;
import com.toma.pubgmc.util.VehicleConfiguration;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateConfig implements IMessage, IMessageHandler<PacketUpdateConfig, IMessage>
{
	// world settings
	int airdropLootGen, range, planeHeight, planeWait;
	boolean gunLoot, guns;
	
	// player settings
	int brightness;
	boolean tpp, invLimit, forceBrightness, nametags;
	
	// weapons
	WeaponCFG p92, p1911, p18c, r45, r1895, scorpion, win94, sawedoff, s1897, s686, s12k,
		uzi, vector, bizon, tommy, ump, m16, m4, scar, qbz, g36c, aug, ak, m762, mk47,
		groza, dp28, m249, vss, mini, qbu, sks, slr, mk14, kar, m24, awm;
	
	// vehicles
	VehicleConfiguration uaz, dacia;
	
	// from server config
	@Override
	public void toBytes(ByteBuf buf)
	{
		//world settings
		buf.writeBoolean(ConfigPMC.common.worldSettings.enableGunLoot);
		buf.writeBoolean(ConfigPMC.common.worldSettings.enableGuns);
		buf.writeInt(ConfigPMC.common.worldSettings.airdropLootGen);
		buf.writeInt(ConfigPMC.common.worldSettings.aidropRange);
		buf.writeInt(ConfigPMC.common.worldSettings.planeHeight);
		buf.writeInt(ConfigPMC.common.worldSettings.planeWaitTime);
		
		//player settings
		buf.writeInt(ConfigPMC.common.playerSettings.brightness);
		buf.writeBoolean(ConfigPMC.common.playerSettings.enableTP);
		buf.writeBoolean(ConfigPMC.common.playerSettings.enableInventoryLimit);
		buf.writeBoolean(ConfigPMC.common.playerSettings.forceBrightness);
		buf.writeBoolean(ConfigPMC.common.playerSettings.renderPlayerNameTags);
		
		// weapons
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.p92);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.p1911);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.p18c);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.r1895);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.r45);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.scorpion);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.win94);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.sawedoff);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.s1897);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.s686);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.s12k);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.microuzi);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.vector);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.bizon);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.tommygun);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.ump45);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.m16a4);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.m416);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.scarl);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.qbz);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.g36c);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.aug);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.akm);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.m762);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.mk47);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.groza);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.dp28);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.m249);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.vss);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.mini14);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.qbu);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.sks);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.slr);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.mk14);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.kar98k);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.m24);
		WeaponCFG.writeToBuf(buf, ConfigPMC.common.weaponSettings.awm);
		
		VehicleConfiguration.writeBuffer(buf, ConfigPMC.common.vehicleSettings.uaz);
		VehicleConfiguration.writeBuffer(buf, ConfigPMC.common.vehicleSettings.dacia);
	}
	
	// received on client
	@Override
	public void fromBytes(ByteBuf buf)
	{
		gunLoot = buf.readBoolean();
		guns = buf.readBoolean();
		airdropLootGen = buf.readInt();
		range = buf.readInt();
		planeHeight = buf.readInt();
		planeWait = buf.readInt();
		
		brightness = buf.readInt();
		tpp = buf.readBoolean();
		invLimit = buf.readBoolean();
		forceBrightness = buf.readBoolean();
		nametags = buf.readBoolean();
		
		p92 =		WeaponCFG.readFromBuf(buf);
		p1911 =		WeaponCFG.readFromBuf(buf);
		p18c = 		WeaponCFG.readFromBuf(buf);
		r1895 = 	WeaponCFG.readFromBuf(buf);
		r45 = 		WeaponCFG.readFromBuf(buf);
		scorpion = 	WeaponCFG.readFromBuf(buf);
		win94 = 	WeaponCFG.readFromBuf(buf);
		sawedoff = 	WeaponCFG.readFromBuf(buf);
		s1897 = 	WeaponCFG.readFromBuf(buf);
		s686 = 		WeaponCFG.readFromBuf(buf);
		s12k = 		WeaponCFG.readFromBuf(buf);
		uzi = 		WeaponCFG.readFromBuf(buf);
		vector = 	WeaponCFG.readFromBuf(buf);
		bizon = 	WeaponCFG.readFromBuf(buf);
		tommy = 	WeaponCFG.readFromBuf(buf);
		ump = 		WeaponCFG.readFromBuf(buf);
		m16 = 		WeaponCFG.readFromBuf(buf);
		m4 = 		WeaponCFG.readFromBuf(buf);
		scar = 		WeaponCFG.readFromBuf(buf);
		qbz = 		WeaponCFG.readFromBuf(buf);
		g36c = 		WeaponCFG.readFromBuf(buf);
		aug = 		WeaponCFG.readFromBuf(buf);
		ak = 		WeaponCFG.readFromBuf(buf);
		m762 = 		WeaponCFG.readFromBuf(buf);
		mk47 = 		WeaponCFG.readFromBuf(buf);
		groza = 	WeaponCFG.readFromBuf(buf);
		dp28 = 		WeaponCFG.readFromBuf(buf);
		m249 = 		WeaponCFG.readFromBuf(buf);
		vss = 		WeaponCFG.readFromBuf(buf);
		mini = 		WeaponCFG.readFromBuf(buf);
		qbu = 		WeaponCFG.readFromBuf(buf);
		sks = 		WeaponCFG.readFromBuf(buf);
		slr = 		WeaponCFG.readFromBuf(buf);
		mk14 = 		WeaponCFG.readFromBuf(buf);
		kar = 		WeaponCFG.readFromBuf(buf);
		m24 = 		WeaponCFG.readFromBuf(buf);
		awm = 		WeaponCFG.readFromBuf(buf);
		
		uaz = VehicleConfiguration.readBuffer(buf);
		dacia = VehicleConfiguration.readBuffer(buf);
	}
	
	@Override
	public IMessage onMessage(PacketUpdateConfig message, MessageContext ctx)
	{
		// make sure it's synchronized on client
		if(ctx.side.isClient())
		{
			Minecraft.getMinecraft().addScheduledTask(() -> sync(message));
		}
		return null;
	}
	
	private void sync(PacketUpdateConfig p)
	{
		ConfigPMC.common.worldSettings.enableGunLoot = p.gunLoot;
		ConfigPMC.common.worldSettings.enableGuns = p.guns;
		ConfigPMC.common.worldSettings.airdropLootGen = p.airdropLootGen;
		ConfigPMC.common.worldSettings.aidropRange = p.range;
		ConfigPMC.common.worldSettings.planeHeight = p.planeHeight;
		ConfigPMC.common.worldSettings.planeWaitTime = p.planeWait;
		
		ConfigPMC.common.playerSettings.brightness = p.brightness;
		ConfigPMC.common.playerSettings.enableTP = p.tpp;
		ConfigPMC.common.playerSettings.enableInventoryLimit = p.invLimit;
		ConfigPMC.common.playerSettings.forceBrightness = p.forceBrightness;
		ConfigPMC.common.playerSettings.renderPlayerNameTags = p.nametags;
		
		ConfigPMC.common.weaponSettings.p92 = p.p92;
		ConfigPMC.common.weaponSettings.p1911 = p.p1911;
		ConfigPMC.common.weaponSettings.p18c = p.p18c;
		ConfigPMC.common.weaponSettings.r1895 = p.r1895;
		ConfigPMC.common.weaponSettings.r45 = p.r45;
		ConfigPMC.common.weaponSettings.scorpion = p.scorpion;
		ConfigPMC.common.weaponSettings.win94 = p.win94;
		ConfigPMC.common.weaponSettings.sawedoff = p.sawedoff;
		ConfigPMC.common.weaponSettings.s1897 = p.s1897;
		ConfigPMC.common.weaponSettings.s686 = p.s686;
		ConfigPMC.common.weaponSettings.s12k = p.s12k;
		ConfigPMC.common.weaponSettings.microuzi = p.uzi;
		ConfigPMC.common.weaponSettings.vector = p.vector;
		ConfigPMC.common.weaponSettings.bizon = p.bizon;
		ConfigPMC.common.weaponSettings.tommygun = p.tommy;
		ConfigPMC.common.weaponSettings.ump45 = p.ump;
		ConfigPMC.common.weaponSettings.m16a4 = p.m16;
		ConfigPMC.common.weaponSettings.m416 = p.m4;
		ConfigPMC.common.weaponSettings.scarl = p.scar;
		ConfigPMC.common.weaponSettings.qbz = p.qbz;
		ConfigPMC.common.weaponSettings.g36c = p.g36c;
		ConfigPMC.common.weaponSettings.aug = p.aug;
		ConfigPMC.common.weaponSettings.akm = p.ak;
		ConfigPMC.common.weaponSettings.m762 = p.m762;
		ConfigPMC.common.weaponSettings.mk47 = p.mk47;
		ConfigPMC.common.weaponSettings.groza = p.groza;
		ConfigPMC.common.weaponSettings.dp28 = p.dp28;
		ConfigPMC.common.weaponSettings.m249 = p.m249;
		ConfigPMC.common.weaponSettings.vss = p.vss;
		ConfigPMC.common.weaponSettings.mini14 = p.mini;
		ConfigPMC.common.weaponSettings.qbu = p.qbu;
		ConfigPMC.common.weaponSettings.sks = p.sks;
		ConfigPMC.common.weaponSettings.slr = p.slr;
		ConfigPMC.common.weaponSettings.mk14 = p.mk14;
		ConfigPMC.common.weaponSettings.kar98k = p.kar;
		ConfigPMC.common.weaponSettings.m24 = p.m24;
		ConfigPMC.common.weaponSettings.awm = p.awm;
		
		ConfigPMC.common.vehicleSettings.uaz = p.uaz;
		ConfigPMC.common.vehicleSettings.dacia = p.dacia;
	}
}
