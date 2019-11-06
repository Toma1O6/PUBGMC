package com.toma.pubgmc.api.games;

import com.toma.pubgmc.common.entity.EntityAIPlayer;

public interface ILootDistributor {
    void giveLoot(EntityAIPlayer bot);
}
