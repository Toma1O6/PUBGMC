package com.toma.pubgmc.common.items.game;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameObjectiveBased;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public final class GameControlItem extends PMCItem {

    private final RClickAction action;

    public GameControlItem(String name, RClickAction action) {
        super(name);
        this.action = action;
        this.addDescription("Used for game objectives");
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        this.action.click(worldIn, null, playerIn, ClickContex.AIR);
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        this.action.click(worldIn, pos, player, ClickContex.GROUND);
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    public interface RClickAction {
        void click(World world, @Nullable BlockPos pos, EntityPlayer player, ClickContex ctx);
    }

    private enum ClickContex {
        AIR, GROUND;

        public boolean onGround() {
            return this == GROUND;
        }
    }

    public static final class Actions {
        public static final RClickAction DEBUG = ((world, pos, player, ctx) -> Game.isDebugMode = !Game.isDebugMode);
        public static final RClickAction OBJECTIVE_ADD = ((world, pos, player, ctx) -> {
            if(ctx.onGround() && !world.isRemote) {
                Game g = getGame(world);
                if(g instanceof GameObjectiveBased) {
                    GameObjectiveBased game = (GameObjectiveBased) g;
                    game.addObjective(pos, game.createNewObjective(world, pos));
                    player.sendStatusMessage(new TextComponentString("Added new objective to " + pos.toString()), true);
                }
            }
        });
        public static final RClickAction OBJECTIVE_REMOVE = ((world, pos, player, ctx) -> {
            if(ctx.onGround() && !world.isRemote) {
                Game game = getGame(world);
                if(game instanceof GameObjectiveBased) {
                    GameObjectiveBased g = (GameObjectiveBased) game;
                    boolean found = g.getObjectives().containsKey(pos);
                    if(!found) {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Nothing found here!"), true);
                        return;
                    }
                    g.getObjectives().remove(pos);
                }
            }
        });
        public static final RClickAction OBJECTIVE_EDIT = ((world, pos, player, ctx) -> {
            if(ctx.onGround() && !world.isRemote) {
                Game g = getGame(world);
                if(g instanceof GameObjectiveBased) {
                    ((GameObjectiveBased)g).onObjectiveTypeChange(pos);
                }
            }
        });

        private static Game getGame(World world) {
            return world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getCurrentGame();
        }
    }
}
