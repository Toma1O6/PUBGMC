package com.toma.pubgmc.common.network.sp;

import com.toma.pubgmc.ConfigPMC;

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
	float p92, p1911, p18c, r45, r1895, scorpion, win94, sawedoff, s1897, s686, s12k,
		uzi, vector, bizon, tommy, ump, m16, m4, scar, qbz, g36c, aug, ak, m762, mk47,
		groza, dp28, m249, vss, mini, qbu, sks, slr, mk14, kar, m24, awm;
	
	// vehicles
		// uaz
	float uazMaxHealth, uazMaxSpeed, uazMaxAngle, uazAccerelation, uazTurning;
	
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
		buf.writeFloat(ConfigPMC.common.weaponSettings.p92);
		buf.writeFloat(ConfigPMC.common.weaponSettings.p1911);
		buf.writeFloat(ConfigPMC.common.weaponSettings.p18c);
		buf.writeFloat(ConfigPMC.common.weaponSettings.r1895);
		buf.writeFloat(ConfigPMC.common.weaponSettings.r45);
		buf.writeFloat(ConfigPMC.common.weaponSettings.scorpion);
		buf.writeFloat(ConfigPMC.common.weaponSettings.win94);
		buf.writeFloat(ConfigPMC.common.weaponSettings.sawedoff);
		buf.writeFloat(ConfigPMC.common.weaponSettings.s1897);
		buf.writeFloat(ConfigPMC.common.weaponSettings.s686);
		buf.writeFloat(ConfigPMC.common.weaponSettings.s12k);
		buf.writeFloat(ConfigPMC.common.weaponSettings.microuzi);
		buf.writeFloat(ConfigPMC.common.weaponSettings.vector);
		buf.writeFloat(ConfigPMC.common.weaponSettings.bizon);
		buf.writeFloat(ConfigPMC.common.weaponSettings.tommygun);
		buf.writeFloat(ConfigPMC.common.weaponSettings.ump9);
		buf.writeFloat(ConfigPMC.common.weaponSettings.m16a4);
		buf.writeFloat(ConfigPMC.common.weaponSettings.m416);
		buf.writeFloat(ConfigPMC.common.weaponSettings.scarl);
		buf.writeFloat(ConfigPMC.common.weaponSettings.qbz);
		buf.writeFloat(ConfigPMC.common.weaponSettings.g36c);
		buf.writeFloat(ConfigPMC.common.weaponSettings.aug);
		buf.writeFloat(ConfigPMC.common.weaponSettings.akm);
		buf.writeFloat(ConfigPMC.common.weaponSettings.m762);
		buf.writeFloat(ConfigPMC.common.weaponSettings.mk47);
		buf.writeFloat(ConfigPMC.common.weaponSettings.groza);
		buf.writeFloat(ConfigPMC.common.weaponSettings.dp28);
		buf.writeFloat(ConfigPMC.common.weaponSettings.m249);
		buf.writeFloat(ConfigPMC.common.weaponSettings.vss);
		buf.writeFloat(ConfigPMC.common.weaponSettings.mini14);
		buf.writeFloat(ConfigPMC.common.weaponSettings.qbu);
		buf.writeFloat(ConfigPMC.common.weaponSettings.sks);
		buf.writeFloat(ConfigPMC.common.weaponSettings.slr);
		buf.writeFloat(ConfigPMC.common.weaponSettings.mk14);
		buf.writeFloat(ConfigPMC.common.weaponSettings.kar98k);
		buf.writeFloat(ConfigPMC.common.weaponSettings.m24);
		buf.writeFloat(ConfigPMC.common.weaponSettings.awm);
		
		// uaz
		buf.writeFloat(ConfigPMC.common.vehicleSettings.uaz.maxHealth);
		buf.writeFloat(ConfigPMC.common.vehicleSettings.uaz.maxSpeed);
		buf.writeFloat(ConfigPMC.common.vehicleSettings.uaz.acceleration);
		buf.writeFloat(ConfigPMC.common.vehicleSettings.uaz.turningSpeed);
		buf.writeFloat(ConfigPMC.common.vehicleSettings.uaz.maxTurningAngle);
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
		uzi = buf.readFloat();
		vector = buf.readFloat();
		bizon = buf.readFloat();
		tommy = buf.readFloat();
		ump = buf.readFloat();
		m16 = buf.readFloat();
		m4 = buf.readFloat();
		scar = buf.readFloat();
		qbz = buf.readFloat();
		g36c = buf.readFloat();
		aug = buf.readFloat();
		ak = buf.readFloat();
		m762 = buf.readFloat();
		mk47 = buf.readFloat();
		groza = buf.readFloat();
		dp28 = buf.readFloat();
		m249 = buf.readFloat();
		vss = buf.readFloat();
		mini = buf.readFloat();
		qbu = buf.readFloat();
		sks = buf.readFloat();
		slr = buf.readFloat();
		mk14 = buf.readFloat();
		kar = buf.readFloat();
		m24 = buf.readFloat();
		awm = buf.readFloat();
		
		uazMaxHealth = buf.readFloat();
		uazMaxSpeed = buf.readFloat();
		uazAccerelation = buf.readFloat();
		uazTurning = buf.readFloat();
		uazMaxAngle = buf.readFloat();
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
		ConfigPMC.common.weaponSettings.ump9 = p.ump;
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
		
		ConfigPMC.common.vehicleSettings.uaz.maxHealth = p.uazMaxHealth;
		ConfigPMC.common.vehicleSettings.uaz.maxSpeed = p.uazMaxSpeed;
		ConfigPMC.common.vehicleSettings.uaz.acceleration = p.uazAccerelation;
		ConfigPMC.common.vehicleSettings.uaz.turningSpeed = p.uazTurning;
		ConfigPMC.common.vehicleSettings.uaz.maxTurningAngle = p.uazMaxAngle;
	}
}
