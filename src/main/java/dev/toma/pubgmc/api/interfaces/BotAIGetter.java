package dev.toma.pubgmc.api.interfaces;

import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import net.minecraft.entity.ai.EntityAITasks;

public interface BotAIGetter {

    void apply(EntityAITasks normalTasks, EntityAITasks targetTasks, EntityAIPlayer bot);
}
