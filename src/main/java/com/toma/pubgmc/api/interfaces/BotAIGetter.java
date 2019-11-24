package com.toma.pubgmc.api.interfaces;

import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import net.minecraft.entity.ai.EntityAITasks;

public interface BotAIGetter {

    void apply(EntityAITasks normalTasks, EntityAITasks targetTasks, EntityAIPlayer bot);
}
