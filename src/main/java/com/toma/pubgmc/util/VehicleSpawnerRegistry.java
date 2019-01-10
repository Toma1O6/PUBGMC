package com.toma.pubgmc.util;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.EntityVehicle;

public class VehicleSpawnerRegistry 
{
	private static final List<EntityVehicle> VEHICLES = new ArrayList<EntityVehicle>();
	
	public static List<EntityVehicle> getVehicleRegistry()
	{
		return VEHICLES;
	}
	
	public static void registerVehicle(int id, EntityVehicle vehicle)
	{
		vehicle.setVehicleID(id);
		VEHICLES.add(vehicle);
		Pubgmc.logger.info("Registered vehicle ", vehicle.getName() + " with id ", id);
	}
}
