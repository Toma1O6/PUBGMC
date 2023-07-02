package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

// Extension of vanilla's EntityItem with added gameId parameter which allows to remove all dropped items from previous games
public class EntityGameItem extends EntityItem implements GameObject {

    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityGameItem(World worldIn) {
        super(worldIn);
    }

    public EntityGameItem(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted % 20 == 0) {
            GameHelper.validateGameEntityStillValid(this);
        }
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        setDead();
    }
}
