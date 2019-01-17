package com.toma.pubgmc.common.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityAIPlayer extends EntityMob
{
	public EntityAIPlayer(World worldIn)
	{
		super(worldIn);
		preventEntitySpawning = true;
	}
}
