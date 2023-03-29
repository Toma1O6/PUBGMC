package dev.toma.pubgmc.common.entity.bot;

import net.minecraft.entity.ai.EntityAITasks;

public interface AIType {

    static AIType getDefaultInstance() {
        return t -> {
        };
    }

    void addTasks(EntityAITasks tasks);
}
