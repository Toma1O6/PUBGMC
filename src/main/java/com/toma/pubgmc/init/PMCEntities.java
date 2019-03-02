package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.entity.EntityFlare;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.entity.EntityMolotov;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.entity.EntitySmokeGrenade;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityTestVehicle;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber
public class PMCEntities
{
	static int ID = -1;
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<EntityEntry> e)
	{
		final EntityEntry[] entries = 
		{
			registerEntity("bullet", EntityBullet.class, 64, 40, true),
			registerEntity("grenade", EntityGrenade.class, 64, 20, true),
			registerEntity("smoke", EntitySmokeGrenade.class, 64, 20, true),
			registerEntity("molotov", EntityMolotov.class, 64, 20, true),
			registerEntity("flare", EntityFlare.class, 64, 20, true),
			registerEntity("parachute", EntityParachute.class, 256, 1, true),
			registerEntity("plane", EntityPlane.class, 128, 25, true),
			registerVehicle("testVehicle", EntityTestVehicle.class, 35)
		};
		
		e.getRegistry().registerAll(entries);
	}
	
	private static EntityEntry registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendVelocityUpdates)
	{
		return createEntityBuilder(name).entity(entityClass).tracker(trackingRange, updateFrequency, sendVelocityUpdates).build();
	}
	
	private static EntityEntry registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggPrimary, int eggSecondary)
	{
		return createEntityBuilder(name).entity(entityClass).tracker(trackingRange, updateFrequency, sendVelocityUpdates).egg(eggPrimary, eggSecondary).build();
	}
	
	private static EntityEntry registerVehicle(String name, Class<? extends EntityVehicle> vehicleClass)
	{
		return registerEntity(name, vehicleClass, 256, 22, true);
	}
	
	private static EntityEntry registerVehicle(String name, Class<? extends EntityVehicle> vehicle, int updateFrequency)
	{
		return registerEntity(name, vehicle, 256, updateFrequency, true);
	}
	
	private static <E extends Entity> EntityEntryBuilder<E> createEntityBuilder(String name)
	{
		EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		ResourceLocation regName = new ResourceLocation(Pubgmc.MOD_ID, name);
		return builder.id(regName, nextID()).name(regName.toString());
	}
	
	private static int nextID()
	{
		ID++;
		return ID;
	}
}
